package com.magicalrice.adolph.ganktest;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.magicalrice.adolph.lib_common.base.BaseFragment;

import java.util.List;

/**
 * Created by Adolph on 2018/2/23.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter{

    private List<BaseFragment> mFragments;

    public FragmentAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
