package com.magicalrice.adolph.module_news;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseActivity;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;
import com.magicalrice.adolph.lib_common.viewmodel.NewsViewModel;
import com.magicalrice.adolph.module_news.databinding.ActivityNewsBinding;

/**
 * Created by Adolph on 2018/2/23.
 */
@Route(path = ARouterPath.NEWS_LIST_ATY)
public class NewsActivity extends BaseActivity {
    private NewsAdapter adapter;
    private ActivityNewsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_NewsActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        NewsViewModel model = new NewsViewModel(getApplication());
        adapter = new NewsAdapter(callback);
        binding.setAdapter(adapter);
        subscribeToModel(model);
    }

    NewsItemClickCallback callback = new NewsItemClickCallback() {
        @Override
        public void onClick(NewsData newsItem) {
            Toast.makeText(NewsActivity.this,newsItem.getDesc(),Toast.LENGTH_SHORT).show();
        }
    };

    private void subscribeToModel(final NewsViewModel model) {
        model.getLiveObservableData().observe(this, new Observer<BaseBean<NewsData>>() {
            @Override
            public void onChanged(@Nullable BaseBean<NewsData> newsDataBaseBean) {
                model.setUiObservableData(newsDataBaseBean);
                adapter.setNewsList(newsDataBaseBean.getResults());
            }
        });
    }
}
