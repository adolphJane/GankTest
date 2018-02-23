package com.magicalrice.adolph.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by Adolph on 2018/2/14.
 */

public class AppUtils {
    private static Context context;

    private AppUtils(){
        throw new UnsupportedOperationException("AppUtils can`t be initialized");
    }

    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        AppUtils.context = context.getApplicationContext();
    }
    /**
     * 全局获取Activity的工具
     * @return
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("AppUtils should be init first");
    }

    /**
     * View获取Activity的工具
     * @param view
     * @return
     */
    @NonNull
    public static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        throw new IllegalStateException("View " + view + "is not attached to an Activity");
    }

    /**
     * 全局获取String 的方法
     * @param id 资源Id
     * @return
     */
    public static String getString(@StringRes int id) {
        return context.getResources().getString(id);
    }

    /**
     * 判断是否时Debug版本
     * @return
     */
    public static boolean isAppDebug() {
        try {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(),0);
            return ai != null && (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment,int framentId) {
        checkNotNull(fragment);
        checkNotNull(fragmentManager);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(framentId,fragment);
        transaction.commit();
    }

    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
