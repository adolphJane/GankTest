package com.magicalrice.adolph.lib_common.datamodel.http.respository;

import com.magicalrice.adolph.lib_common.datamodel.http.ApiClient;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/2/14.
 */

public class GankDataRepository {
    public static Observable<BaseBean<GirlsData>> getFuliDataRepository(String size,String index) {
        Observable<BaseBean<GirlsData>> observable = ApiClient.getGankDataService().getFuliData(size,index);
        return observable;
    }

    public static Observable<BaseBean<NewsData>> getAndroidDataRepository(String size,String index) {
        Observable<BaseBean<NewsData>> observable = ApiClient.getGankDataService().getAndroidData(size,index);
        return observable;
    }
}
