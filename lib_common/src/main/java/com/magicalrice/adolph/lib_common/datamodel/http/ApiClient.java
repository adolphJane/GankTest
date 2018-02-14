package com.magicalrice.adolph.lib_common.datamodel.http;

import com.magicalrice.adolph.lib_common.BuildConfig;
import com.magicalrice.adolph.lib_common.datamodel.http.respository.DynamicDataRepository;
import com.magicalrice.adolph.lib_common.datamodel.http.service.DynamicApiService;
import com.magicalrice.adolph.lib_common.datamodel.http.service.GankDataService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adolph on 2018/2/14.
 */

public class ApiClient {

    private static Retrofit retrofitInstance;
    private static OkHttpClient okHttpClientInstance;

    /**
     * 获取默认Url数据
     * @return
     */
    public static GankDataService getGankDataService() {
        GankDataService gankDataService = initService(ApiConstants.GankHost,GankDataService.class);
        return gankDataService;
    }

    /**
     * 动态Url获取数据
     * @return
     */
    public static DynamicApiService getDynamicDataService() {
        DynamicApiService dynamicApiService = ApiClient.initService("",DynamicApiService.class);
        return dynamicApiService;
    }

    /**
     * 获得想要的retrofit service
     * @param baseUrl 数据请求的url
     * @param clazz 想要的service接口
     * @param <T> api service
     * @return
     */
    private static <T> T initService(String baseUrl,Class<T> clazz) {
        return getRetrofitInstance(baseUrl).create(clazz);
    }

    private static Retrofit getRetrofitInstance(String baseUrl) {
        if (retrofitInstance == null) {
            synchronized (ApiClient.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(getOkHttpClientInstance())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    private static OkHttpClient getOkHttpClientInstance() {
        if (okHttpClientInstance == null) {
            synchronized (ApiClient.class) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(interceptor);
                }
                okHttpClientInstance = builder.build();
            }
        }
        return okHttpClientInstance;
    }
}
