package com.magicalrice.adolph.lib_common.datamodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.magicalrice.adolph.lib_common.BR;

import java.io.Serializable;

/**
 * Created by Adolph on 2018/2/22.
 */

public class User extends BaseObservable implements Serializable {
    private String userName;
    private String userId;

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }
}
