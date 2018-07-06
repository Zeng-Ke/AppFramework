package com.zk.android_utils.cache;

import com.jakewharton.disklrucache.DiskLruCache;
import com.zk.android_utils.utils.GsonUtils;
import com.zk.java_utils.StringUtils;
import com.zk.java_utils.TimeUtils;
import com.zk.java_utils.log.LogUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * author: ZK.
 * date:   On 2018-07-03.
 */
public class DiskCacheHandler {


    private final DiskLruCache mDiskLruCache;

    public static final long NO_EXPIRED = -1; //不会过期

    private static final int INDEX_VALUE = 0;
    private static final int INDEX_TIME = 1;

    public DiskCacheHandler(DisklruCacheProvider provider) {
        if (provider == null && provider.getDiskLruCache() == null)
            throw new NullPointerException(" DisklruCacheProvider or DiskLruCache  is null");
        mDiskLruCache = provider.getDiskLruCache();
    }


    public <T> void doSave(String key, T value) {
        doSave(key, value, NO_EXPIRED, TimeUnit.SECONDS);
    }


    public synchronized <T> void doSave(String key, T value, long expiredTime, TimeUnit unit) {
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
            editor.set(INDEX_VALUE, value == null ? StringUtils.EMPTY : GsonUtils.stringify(value));
            editor.set(INDEX_TIME, String.valueOf(expiredTime > 0 ? unit.toMillis(expiredTime + System.currentTimeMillis()) : NO_EXPIRED));
            editor.commit();
        } catch (IOException e) {
            LogUtil.e(e.getClass().getSimpleName() + "  +  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public synchronized <T> T doGet(String key, Class<T> clz) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                String timeStr = snapshot.getString(INDEX_TIME);
                if (StringUtils.isEmpty(timeStr))
                    return null;
                long expiredTime = Long.parseLong(timeStr);
                if (expiredTime == NO_EXPIRED || TimeUtils.getTimeSpanByNow(expiredTime, TimeUtils.UNIT.SECOND) > 0) {  //不会过期|| 未过期
                    String cacheStr = snapshot.getString(INDEX_VALUE);
                    LogUtil.json("cache", cacheStr);
                    return GsonUtils.parse(clz, cacheStr);
                }
            }
        } catch (IOException e) {
            LogUtil.e(e.getClass().getSimpleName() + "  +  " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * T内部包含泛型时使用
     */
    public synchronized <T> T doGet(String key, Type type) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {
                String timeStr = snapshot.getString(INDEX_TIME);
                if (StringUtils.isEmpty(timeStr))
                    return null;
                long expiredTime = Long.parseLong(timeStr);
                if (expiredTime == NO_EXPIRED || TimeUtils.getTimeSpanByNow(expiredTime, TimeUtils.UNIT.SECOND) > 0) {  //不会过期|| 未过期
                    String cacheStr = snapshot.getString(INDEX_VALUE);
                    LogUtil.i("cache", cacheStr);
                    return GsonUtils.parse(type, cacheStr);
                }
            }
        } catch (IOException e) {
            LogUtil.e(e.getClass().getSimpleName() + "  +  " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    public synchronized void delete(String key) {
        try {
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            LogUtil.e(e.getClass().getSimpleName() + "  +  " + e.getMessage());
            e.printStackTrace();
        }
    }



}
