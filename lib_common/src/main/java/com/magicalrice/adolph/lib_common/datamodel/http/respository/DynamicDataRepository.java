package com.magicalrice.adolph.lib_common.datamodel.http.respository;


import com.magicalrice.adolph.lib_common.datamodel.http.ApiClient;
import com.magicalrice.adolph.lib_common.utils.JsonUtils;
import com.magicalrice.adolph.lib_common.utils.SwitchSchedulers;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Adolph on 2018/2/14.
 */

public class DynamicDataRepository {
    public static <T> Observable getDynamicData(String fullUrl,final Class<T> clazz) {
        return ApiClient.getDynamicDataService()
                .getDynamicData(fullUrl)
                .compose(SwitchSchedulers.applySchedulers())
                .map(o -> JsonUtils.str2JsonBean(o.toString(),clazz));
    }
}
