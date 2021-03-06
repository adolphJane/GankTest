package com.magicalrice.adolph.lib_common.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;
import com.magicalrice.adolph.lib_common.datamodel.http.respository.GankDataRepository;
import com.magicalrice.adolph.lib_common.utils.NetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/2/14.
 */

public class NewsViewModel extends AndroidViewModel {
    //生命周期观察的数据
    private LiveData<BaseBean<NewsData>> liveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<BaseBean<NewsData>> uiObservableData = new ObservableField<>();
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        ABSENT.setValue(null);
    }

    public NewsViewModel(@NonNull Application application) {
        super(application);

        liveObservableData = Transformations.switchMap(NetUtils.netConnected(application), input -> {
            if (!input) {
                return ABSENT;  //网络未连接返回空
            }
            MutableLiveData<BaseBean<NewsData>> applyData = new MutableLiveData<>();
            GankDataRepository.getAndroidDataRepository("20","1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseBean<NewsData>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDisposable.add(d);
                        }

                        @Override
                        public void onNext(BaseBean<NewsData> girlsDataBaseBean) {
                            applyData.setValue(girlsDataBaseBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            return applyData;
        });
    }

    /**
     * LiveData支持了lifecycle生命周期检测
     * @return
     */
    public LiveData<BaseBean<NewsData>> getLiveObservableData() {
        return liveObservableData;
    }

    /**
     * 当主动改变数据时重新设置被观察的数据
     * @param uiObservableData
     */
    public void setUiObservableData(BaseBean<NewsData> uiObservableData) {
        this.uiObservableData.set(uiObservableData);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
