package com.zk.android_utils.cache;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;
import com.zk.android_utils.utils.AppUtils;
import com.zk.java_utils.log.LogUtil;

import java.io.File;
import java.io.IOException;

/**
 * author: ZK.
 * date:   On 2018-07-03.
 */
public class DisklruCacheProvider {


    public enum SIZEUNIT {

        KB(1024),
        MB(1024 * 1024),
        GB(1024 * 1024 * 1024);

        public long value;

        SIZEUNIT(long value) {
            this.value = value;
        }
    }


    private static final int CACHE_VERSION = 1;

    DiskLruCache diskLruCache = null;


    public DisklruCacheProvider(String uniqueName, long maxsize,int valuecount, SIZEUNIT sizeunit) {
        try {
            File cacheDir = getDiskCacheDir(uniqueName);
            if (!cacheDir.exists()) {
                cacheDir.mkdir();
            }
            diskLruCache = DiskLruCache.open(cacheDir, CACHE_VERSION, valuecount>1 ?valuecount:  2, maxsize * sizeunit.value);
        } catch (IOException e) {
            LogUtil.e(e.getClass().getName() + "   :   " + e.getMessage());
            e.printStackTrace();
        }
    }

    public DiskLruCache getDiskLruCache() {
        return diskLruCache;

    }


    /**
     * 1、 首先判断外部硬盘SD卡没有被移除并且是否可读可写时，则缓存目录为context.getExternalCacheDir().getPath()，
     * 即存到/storage/emulated/0/Android/data/package_name/cache这个外部存储目录中。
     * 注意：外部存储可以分为两种，一种如上面这种路径(/storage/emulated/0/Android/data/package_name/cache)，当应用卸载后，存储数据也会被删除
     * 另一种是永久存储，即使应用被卸载，存储的数据依然存在，存储的路径如 ： /storage/emulated/0/mDiskCache，可以
     * 通过Environment.getExternalStorageDirectory().getAbsolutePath() + "/mDiskCache" 来获得路径。
     * <p>
     * 2、若果外部SD卡已被移除或者不可写，那么缓存目录= context.getCacheDir().getPath()，即存到 /data/data/package_name/cache这个文件系统目录中。
     */
    private static File getDiskCacheDir( String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            cachePath = AppUtils.getApp().getExternalCacheDir().getPath();
        } else {
            cachePath =  AppUtils.getApp().getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);

    }

}
