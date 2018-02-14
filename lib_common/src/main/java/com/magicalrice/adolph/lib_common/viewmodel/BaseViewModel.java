package com.magicalrice.adolph.lib_common.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Html;

import com.magicalrice.adolph.lib_common.datamodel.http.respository.DynamicDataRepository;
import com.magicalrice.adolph.lib_common.utils.SwitchSchedulers;

import java.lang.reflect.ParameterizedType;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Adolph on 2018/2/14.
 */

public class BaseViewModel<T> extends AndroidViewModel{

    //生命周期观察的数据
    private MutableLiveData<T> liveObservableData = new MutableLiveData<>();
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<T> uiObservableData = new ObservableField<>();
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }


    public BaseViewModel(@NonNull Application application,String fullUrl) {
        super(application);

        DynamicDataRepository.getDynamicData(fullUrl,getTClass())
                .compose(SwitchSchedulers.applySchedulers())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(T o) {
                        if (o != null) {
                            liveObservableData.setValue(o);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    /**
     * LiveData支持了lifecycle生命周期检测
     * @return
     */
    public LiveData<T> getLiveObservableData() {
        return liveObservableData;
    }

    /**
     * 当主动改变数据时重新设置被观察的数据
     * @param uiObservableData
     */
    public void setUiObservableData(ObservableField<T> uiObservableData) {
        this.uiObservableData = uiObservableData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
