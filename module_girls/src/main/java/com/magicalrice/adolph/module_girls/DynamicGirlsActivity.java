package com.magicalrice.adolph.module_girls;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseActivity;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.BaseBean;
import com.magicalrice.adolph.lib_common.datamodel.http.entities.GirlsData;
import com.magicalrice.adolph.lib_common.viewmodel.BaseViewModel;
import com.magicalrice.adolph.lib_common.viewmodel.DynamicGirlsViewModel;
import com.magicalrice.adolph.module_girls.databinding.ActivityGirlsBinding;

/**
 * Created by Adolph on 2018/2/23.
 */
@Route(path = ARouterPath.DYNA_GIRLS_LIST_ATY)
public class DynamicGirlsActivity extends BaseActivity {
    @Autowired(name = "fullUrl")
    public String fullUrl;
    private GirlsAdapter adapter;
    private ActivityGirlsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_DynamicGirlsActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_girls);
        ARouter.getInstance().inject(this);
        if (TextUtils.isEmpty(fullUrl))
            return;
        DynamicGirlsViewModel model = new DynamicGirlsViewModel(getApplication(),fullUrl);
        adapter = new GirlsAdapter(callback);
        binding.setAdapter(adapter);
        subscribeToModel(model);
    }

    GirlsItemClickCallback callback = (girlsItem)-> Toast.makeText(DynamicGirlsActivity.this,girlsItem.getDesc(),Toast.LENGTH_SHORT).show();

    private void subscribeToModel(final BaseViewModel model) {
        model.getLiveObservableData().observe(this, (girlsDataBaseBean)->{
            BaseBean<GirlsData> bean = (BaseBean<GirlsData>) girlsDataBaseBean;
            model.setUiObservableData(bean);
            adapter.setGirlList(bean.getResults());
        });
    }
}
