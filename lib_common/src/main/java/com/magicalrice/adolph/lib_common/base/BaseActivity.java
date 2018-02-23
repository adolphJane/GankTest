package com.magicalrice.adolph.lib_common.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.magicalrice.adolph.lib_common.R;
import com.magicalrice.adolph.lib_common.utils.AppUtils;

/**
 * Created by Adolph on 2018/2/22.
 */

public class BaseActivity extends AppCompatActivity {

    /**
     * 封装的findViewById方法
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().finishActivity(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * SetUpToolbar
     * @param toolbar
     * @param hideTitle 是否隐藏toolbar
     */
    protected void setupToolBar(Toolbar toolbar,boolean hideTitle) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.setHomeAsUpIndicator();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            if (hideTitle) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    /**
     * 添加fragment
     * @param fragment
     * @param fragId
     */
    protected void addFragment(BaseFragment fragment,@IdRes int fragId){
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(fragId,fragment,fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 替换fragment
     * @param fragment
     * @param fragId
     */
    protected void replaceFragment(BaseFragment fragment,@IdRes int fragId){
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(fragId,fragment,fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 隐藏Fragment
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 显示Fragment
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 移除Fragment
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();
    }

    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.activity_down_in,R.anim.activity_down_out);
    }
}
