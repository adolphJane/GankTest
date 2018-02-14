package com.magicalrice.adolph.lib_common.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;

/**
 * Created by Adolph on 2018/2/14.
 */

public class DynamicGirlsViewModel extends BaseViewModel<GirlsData> {

    public DynamicGirlsViewModel(@NonNull Application application, String fullUrl) {
        super(application, fullUrl);
    }
}
