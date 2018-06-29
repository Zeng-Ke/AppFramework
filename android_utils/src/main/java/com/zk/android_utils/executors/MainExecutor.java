package com.zk.android_utils.executors;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * author: ZK.
 * date:   On 2018-06-29.
 */
public class MainExecutor implements Executor {


    private final Handler mHandler;
    private final Thread mMainThread;

    public MainExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
        mMainThread = Looper.getMainLooper().getThread();
    }

    @Override
    public void execute(@NonNull Runnable command) {
        if (mMainThread == Thread.currentThread())
            command.run();
        else mHandler.post(command);
    }
}
