package com.magicalrice.adolph.module_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseFragment;

/**
 * Created by Adolph on 2018/2/23.
 */
@Route(path = ARouterPath.NEWS_LIST_FGT)
public class NewsFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,container,false);
    }
}
