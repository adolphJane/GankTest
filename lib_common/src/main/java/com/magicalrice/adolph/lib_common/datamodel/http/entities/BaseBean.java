package com.magicalrice.adolph.lib_common.datamodel.http.entities;

import java.io.Serializable;

/**
 * Created by Adolph on 2018/2/14.
 */

public class BaseBean<T> implements Serializable {
    private boolean error;
    private T data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
