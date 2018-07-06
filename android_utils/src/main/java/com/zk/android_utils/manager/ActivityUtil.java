package com.zk.android_utils.manager;

import android.app.Activity;

import com.zk.android_utils.base.BaseActivity;
import com.zk.java_utils.StringUtils;
import com.zk.java_utils.log.LogUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class ActivityUtil {


    private static LinkedList<Activity> mActivityList = new LinkedList<>();
    private static BaseActivity mLastResumeActivity;

    public static BaseActivity getLastResumeActivity() {
        return mLastResumeActivity;
    }

    public static void setLastResumeActivity(BaseActivity lastResumeActivity) {
        mLastResumeActivity = lastResumeActivity;
    }

    public static int size() {
        return mActivityList.size();
    }

    public static Activity getTop() {
        synchronized (mActivityList) {
            return mActivityList.peek();
        }
    }

    public  static  boolean isExistActivity(Class activityClz){
        synchronized (mActivityList) {
            for (Activity activity : mActivityList) {
                if (activity != null && StringUtils.equals(activity.getClass().getName(),activityClz.getName()))
                    return  true;
            }
            return false;
        }
    }

    public static void finish(Activity activity) {
        if (activity != null){
            activity.finish();
            LogUtil.i(activity.getClass().getName() + "has finished");
        }
    }


    public static void add(Activity activity) {
        mActivityList.remove(activity);
        synchronized (mActivityList) {
            mActivityList.addFirst(activity);
        }
    }

    public static void removeActivity(Activity activity) {
        synchronized (mActivityList) {
            mActivityList.remove(activity);
        }
    }

    public static void finishAll() {
        synchronized (mActivityList) {
            Activity activity;
            do {
                activity = mActivityList.poll();
                finish(activity);
            } while (activity != null);
        }
    }

    public static void finishAllButThis(Activity activity) {
        synchronized (mActivityList) {
            if (activity != null)
                for (Activity a : mActivityList) {
                    if (a != activity)
                        finish(a);
                }
        }
    }


    public static void finishAllButThis(Class clz) {
        List<Activity> activities = new ArrayList<>();
        synchronized (mActivityList) {
            if (clz != null)
                for (Activity activity : mActivityList) {
                    if (activity != null && !StringUtils.equals(activity.getClass().getName(), clz.getName()))
                        activities.add(activity);
                }
        }
        for (Activity activity : activities)
           finish(activity);


    }


}
