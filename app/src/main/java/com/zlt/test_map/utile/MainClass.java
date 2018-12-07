package com.zlt.test_map.utile;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



/**
 * Created by Smile on 17/5/10.
 */
public class MainClass extends Application {

    private static final String TAG = "MainClass";
    private static MainClass instance;
    private static final List<Activity> activitys = Collections.synchronizedList(new LinkedList<Activity>());


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainClass getInstance() {
        return instance;
    }


    /**
     * @param activity 作用说明 ：添加一个activity到管理里
     */
    public void pushActivity(Activity activity) {
        activitys.add(activity);
        Log.d(TAG, "activityList:size:" + activitys.size());
    }

    /**
     * @param activity 作用说明 ：删除一个activity在管理里
     */
    public void popActivity(Activity activity) {
        activitys.remove(activity);
        Log.d(TAG, "activityList:size:" + activitys.size());
    }


    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        if (activitys == null || activitys.isEmpty()) {
            return null;
        }
        return activitys.get(activitys.size() - 1);
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        if (activitys == null || activitys.isEmpty()) {
            return;
        }
        Activity activity = activitys.get(activitys.size() - 1);
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activitys == null || activitys.isEmpty()) {
            return;
        }
        if (activity != null) {
            activitys.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity之外的其他Activity
     */
    public static void finishActivityPassThis(Class<?> cls) {
        if (activitys == null || activitys.isEmpty()) {
            return;
        }
        for (Activity activity : activitys) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        if (activitys == null || activitys.isEmpty()) {
            return;
        }
        for (Activity activity : activitys) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 按照指定类名找到activity
     *
     * @param cls
     * @return
     */
    public static Activity findActivity(Class<?> cls) {
        Activity targetActivity = null;
        if (activitys != null) {
            for (Activity activity : activitys) {
                if (activity.getClass().equals(cls)) {
                    targetActivity = activity;
                    break;
                }
            }
        }
        return targetActivity;
    }

    /**
     * @return 作用说明 ：获取当前最顶部activity的实例
     */
    public Activity getTopActivity() {
        Activity mBaseActivity;
        synchronized (activitys) {
            final int size = activitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = activitys.get(size);
        }
        return mBaseActivity;

    }

    /**
     * @return 作用说明 ：获取当前最顶部的acitivity 名字
     */
    public String getTopActivityName() {
        Activity mBaseActivity = null;
        synchronized (activitys) {
            final int size = activitys.size() - 1;
            if (size < 0) {
                return null;
            }
            mBaseActivity = activitys.get(size);
        }
        return mBaseActivity.getClass().getName();
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activitys == null) {
            return;
        }
        for (Activity activity : activitys) {
            activity.finish();
        }
        activitys.clear();
    }

    private void registerActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                /**
                 *  监听到 Activity创建事件 将该 Activity 加入list
                 */
                pushActivity(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if (null == activitys || activitys.isEmpty()) {
                    return;
                }
                if (activitys.contains(activity)) {
                    /**
                     *  监听到 Activity销毁事件 将该Activity 从list中移除
                     */
                    popActivity(activity);
                }
            }
        });
    }


}