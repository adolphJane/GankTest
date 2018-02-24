package com.magicalrice.adolph.module_girls;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseFragment;
import com.magicalrice.adolph.lib_common.viewmodel.GirlsViewModel;
import com.magicalrice.adolph.module_girls.databinding.FragmentGirlsBinding;

/**
 * Created by Adolph on 2018/2/23.
 */
@Route(path = ARouterPath.GIRLS_LIST_FGT)
public class GirlsFragment extends BaseFragment {
    private GirlsAdapter adapter;
    private FragmentGirlsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_girls,container,false);
        adapter = new GirlsAdapter(callback);
        binding.setAdapter(adapter);
        GirlsViewModel model = new GirlsViewModel(getActivity().getApplication());
        subscribeToModel(model);
        return binding.getRoot();
    }

    GirlsItemClickCallback callback = (girlsItem)->Toast.makeText(getContext(),girlsItem.getDesc(),Toast.LENGTH_SHORT).show();

    private void subscribeToModel(final GirlsViewModel model) {
        model.getLiveObservableData().observe(this, (girlsDataBaseBean)->{
                model.setUiObservableData(girlsDataBaseBean);
                adapter.setGirlList(girlsDataBaseBean.getResults());
        });
    }
}
