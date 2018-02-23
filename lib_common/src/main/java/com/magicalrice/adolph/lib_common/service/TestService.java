package com.magicalrice.adolph.lib_common.service;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by Adolph on 2018/2/22.
 */

public interface TestService extends IProvider{
    String sayHello(String name);
}
