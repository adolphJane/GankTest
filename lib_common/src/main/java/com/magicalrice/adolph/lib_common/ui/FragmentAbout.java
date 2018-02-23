package com.magicalrice.adolph.lib_common.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.magicalrice.adolph.lib_common.R;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseFragment;
import com.magicalrice.adolph.lib_common.databinding.FragmentAboutBinding;
import com.magicalrice.adolph.lib_common.datamodel.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/2/22.
 * desc: 关于页面
 */

@Route(path = ARouterPath.ABOUT_FGT)
public class FragmentAbout extends BaseFragment{
    private FragmentAboutBinding binding;
    private User user1,user2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about,container,false);
        binding.setUserList(initUser());
        binding.executePendingBindings();
        binding.btnChange.setOnClickListener(v-> changeName());
        return binding.getRoot();
    }

    private void changeName(){
        user1.setUserName("HeiHeiHei");
        user2.setUserName("LaLaLa");
    }

    private List<User> initUser() {
        List<User> userList = new ArrayList<>();
        user1 = new User();
        user1.setUserName("kawayi");
        user1.setUserId("112");

        user2 = new User();
        user2.setUserName("hawayi");
        user2.setUserId("113");

        userList.add(user1);
        userList.add(user2);
        return userList;
    }
}
