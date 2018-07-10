package com.zk.android_utils.cache.sp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.zk.android_utils.utils.GsonUtils;
import com.zk.java_utils.StringUtils;

/**
 * author: ZK.
 * date:   On 2018-07-10.
 */
public class SharePreferenceHandler {

    private final SharedPreferences.Editor mEditor;
    private final SharedPreferences mSharedPreferences;

    public SharePreferenceHandler(Application context, String fileName) {
        mSharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public String getString(String key, String defaultValue) {
        String data = get(key, String.class);
        return StringUtils.isEmpty(defaultValue) ? defaultValue : data;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        Boolean data = get(key, Boolean.class);
        return data == null ? defaultValue : data;
    }


    public int getInt(String key, int defaultValue) {
        Integer data = get(key, Integer.class);
        return data == null ? defaultValue : data;
    }

    public long getLong(String key, long defaultValue) {
        Long data = get(key, Long.class);
        return data == null ? defaultValue : data;
    }


    public <T> T get(String key, Class<T> tClass) {
        String data = mSharedPreferences.getString(key, StringUtils.EMPTY);
        return StringUtils.isEmpty(data) ? null : GsonUtils.parse(tClass, data);
    }


    public <T> void set(String key, T data) {
        if (data == null)
            return;
        mEditor.putString(key, GsonUtils.stringify(data)).apply();
    }

    public void remove(String key) {
        mEditor.remove(key);
        mEditor.apply();
    }

    public void clear() {
        mEditor.clear();
        mEditor.apply();
    }

}
