package com.magicalrice.adolph.lib_common.base;

import android.app.Application;

import com.magicalrice.adolph.lib_common.utils.ClassUtils;

import java.util.List;

/**
 * Created by Adolph on 2018/2/14.
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；`
 */

public class BaseApplication extends Application {
    public static final String ROOT_PACKAGE = "com.magicalrice.module";
    private static BaseApplication sInstance;
    private List<ApplicationDelegate> mAppDelegateList;

    public static BaseApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        mAppDelegateList = ClassUtils.getObjectsWithInterface(sInstance,)
    }
}
