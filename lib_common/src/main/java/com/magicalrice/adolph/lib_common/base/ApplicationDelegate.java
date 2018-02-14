package com.magicalrice.adolph.lib_common.base;

/**
 * Created by Adolph on 2018/2/14.
 * desc: 让各个模块都可以接收到application的生命周期
 */

public interface ApplicationDelegate {
    void onCreate();
    void onTerminate();
    void onLowMemory();
    void onTrimMemory(int level);
}
