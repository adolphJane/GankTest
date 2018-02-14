package com.magicalrice.adolph.lib_common.datamodel.http.service;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Adolph on 2018/2/14.
 */

public interface GankDataService {
    @GET("api/data/福利/{size}/{index}")
    Observable<BaseBean<GirlsData>> getFuliData(@Path("size") String size,@Path("index") String index);

    @GET("api/data/Android/{size}/{index}")
    Observable<BaseBean<NewsData>> getAndroidData(@Path("size") String size,@Path("index") String index);
}
