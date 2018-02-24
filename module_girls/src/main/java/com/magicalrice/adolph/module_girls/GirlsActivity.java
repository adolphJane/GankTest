package com.magicalrice.adolph.module_girls;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseActivity;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.lib_common.viewmodel.GirlsViewModel;
import com.magicalrice.adolph.module_girls.databinding.ActivityGirlsBinding;

import retrofit2.http.PUT;

/**
 * Created by Adolph on 2018/2/22.
 */
@Route(path = ARouterPath.GIRLS_LIST_ATY)
public class GirlsActivity extends BaseActivity {
    private ActivityGirlsBinding binding;
    private GirlsAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_GirlsActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_girls);
        GirlsViewModel model = new GirlsViewModel(GirlsActivity.this.getApplication());
        adapter = new GirlsAdapter(callback);
        binding.setAdapter(adapter);
        subscribeToModel(model);
    }

    GirlsItemClickCallback callback = new GirlsItemClickCallback() {
        @Override
        public void onClick(GirlsData girlsItem) {
            Toast.makeText(GirlsActivity.this,girlsItem.getDesc(),Toast.LENGTH_SHORT).show();
        }
    };

    private void subscribeToModel(final GirlsViewModel model) {
        model.getLiveObservableData().observe(this, new Observer<BaseBean<GirlsData>>() {
            @Override
            public void onChanged(@Nullable BaseBean<GirlsData> girlsDataBaseBean) {
                model.setUiObservableData(girlsDataBaseBean);
                adapter.setGirlList(girlsDataBaseBean.getResults());
            }
        });
    }
}
