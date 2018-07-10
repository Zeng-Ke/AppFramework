package com.zk.android_lib.cache;

/**
 * author: ZK.
 * date:   On 2018-07-10.
 */
public class ClientSetting extends BaseSpCache {


    private static final String KEY_OPEN_COUNT = "key_open_count";


    public ClientSetting() {
        super("ClientSetting");
    }


    public int getOpenCount() {
        int count = mSharePreferenceHandler.getInt(KEY_OPEN_COUNT, 0);
        mSharePreferenceHandler.set(KEY_OPEN_COUNT, count +1);
        return count;
    }


}
