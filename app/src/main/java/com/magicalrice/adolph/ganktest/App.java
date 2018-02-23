package com.magicalrice.adolph.ganktest;


import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.magicalrice.adolph.lib_common.base.BaseApplication;
import com.magicalrice.adolph.lib_common.utils.AppUtils;

/**
 * Created by Adolph on 2018/2/22.
 */

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if (AppUtils.isAppDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
}
