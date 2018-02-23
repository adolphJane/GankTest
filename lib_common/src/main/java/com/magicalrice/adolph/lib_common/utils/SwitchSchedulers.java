package com.magicalrice.adolph.lib_common.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/2/14.
 */

public class SwitchSchedulers {
    public static void unsubscribe(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> MaybeTransformer<T, T> applyMaybeSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T,T> applySingleSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T,T> applyFlowableSchedulers() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
