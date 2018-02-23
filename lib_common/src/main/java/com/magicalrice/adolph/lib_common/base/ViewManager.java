package com.magicalrice.adolph.lib_common.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Adolph on 2018/2/22.
 * Activity 和 Fragment 管理
 */

public class ViewManager {

    private static Stack<Activity> activityStack;
    private static List<BaseFragment> fragmentList;
    private static ViewManager mInstance;

    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if (mInstance == null) {
            mInstance = new ViewManager();
        }
        return mInstance;
    }

    /**
     * 将Fragment添加入列表
     * @param index
     * @param fragment
     */
    public void addFragment(int index, BaseFragment fragment) {
        if (fragmentList != null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.add(index, fragment);
    }

    /**
     * 获取指定位置Fragment
     * @param index
     * @return
     */
    public BaseFragment getFragment(int index) {
        if (fragmentList != null) {
            return fragmentList.get(index);
        }
        return null;
    }

    /**
     * 获取所有Fragment
     * @return
     */
    public List<BaseFragment> getAllFragment() {
        if (fragmentList != null) {
            return fragmentList;
        }
        return null;
    }

    /**
     * 添加指定Activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定Class的Activity
     * @param clazz
     */
    public void finishActivity(Class<?> clazz) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                finishActivity(activity);
                return;
            }
        }
    }

    /**
     * 结束全部的Activity
     */
    public void finishAllActivity() {
        for (int i = 0,size = activityStack.size();i < size;i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void exitApp(Context context) {
        try {
            finishAllActivity();
            //杀死后台进程需要在AndroidManifest中声明android.permission.KILL_BACKGROUND_PROCESSES；
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
        } catch (Exception e) {

        }
    }
}
