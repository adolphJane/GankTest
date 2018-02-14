package com.magicalrice.adolph.lib_common.datamodel.http.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Adolph on 2018/2/14.
 */

public interface DynamicApiService<T> {

    @GET
    Observable<ResponseBody> getDynamicData(@Url String fullUrl);
}
