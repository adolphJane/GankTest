package com.magicalrice.adolph.lib_common.datamodel.http.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Adolph on 2018/2/14.
 */

public class BaseBean<T> implements Serializable {
    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
