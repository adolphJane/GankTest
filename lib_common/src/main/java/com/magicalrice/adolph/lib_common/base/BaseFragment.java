package com.magicalrice.adolph.lib_common.base;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.magicalrice.adolph.lib_common.utils.AppUtils;

/**
 * Created by Adolph on 2018/2/22.
 */

public class BaseFragment extends Fragment {
    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    /**
     * 获取宿主Activity
     * @return baseActivity
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    /**
     * 添加Fragment
     * @param fragment
     * @param resId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int resId) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().addFragment(fragment,resId);
    }

    /**
     * 替换Fragment
     * @param fragment
     * @param resId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int resId) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().replaceFragment(fragment,resId);
    }

    /**
     * 隐藏Fragment
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().hideFragment(fragment);
    }

    /**
     * 显示Fragment
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().showFragment(fragment);
    }

    /**
     * 移除fragment
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        AppUtils.checkNotNull(fragment);
        getHoldingActivity().removeFragment(fragment);
    }

    /**
     * 弹出栈顶的Fragment
     */
    protected void popFragment() {
        getHoldingActivity().popFragment();
    }
}
