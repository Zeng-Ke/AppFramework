package com.zk.android_utils.executors;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * author: ZK.
 * date:   On 2018-06-29.
 */
public class ThreadExecutor {


    private static Executor mainExecutor;
    private static Executor asyncExecutor;

    static {
        mainExecutor = new MainExecutor();
        asyncExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                return new Thread(r, "AsyncExecutor Runnable");
            }
        });
    }

    public static void runInMain(@NonNull Runnable runnable) {
        mainExecutor.execute(runnable);
    }

    public static void runInAsync(@NonNull Runnable runnable) {
        asyncExecutor.execute(runnable);
    }
}
