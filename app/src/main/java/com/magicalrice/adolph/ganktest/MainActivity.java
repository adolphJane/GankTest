package com.magicalrice.adolph.ganktest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.magicalrice.adolph.ganktest.databinding.ActivityMainBinding;
import com.magicalrice.adolph.lib_common.base.ARouterPath;
import com.magicalrice.adolph.lib_common.base.BaseActivity;
import com.magicalrice.adolph.lib_common.base.BaseFragment;
import com.magicalrice.adolph.lib_common.service.TestService;
import com.magicalrice.adolph.lib_common.widget.NoScrollViewPager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/2/22.
 */

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Autowired(name = "/service/test")
    TestService testService1;

    TestService testService2, testService3;

    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private FragmentAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(MainActivity.this);
//        testService1.sayHello("Autowired invoke 233");
//        testService2 = ARouter.getInstance().navigation(TestService.class);
//        testService2.sayHello("navigation invoke 233");
//        testService3 = (TestService) ARouter.getInstance().build("/service/test").navigation();
//        testService3.sayHello("build invoke 233");

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setItemClick(itemClick);

        binding.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        mPager = binding.containerPager;
        mPager.setOffscreenPageLimit(3);

        BaseFragment fragmentNews = (BaseFragment) ARouter.getInstance().build(ARouterPath.NEWS_LIST_FGT).navigation();
        BaseFragment fragmentGirls = (BaseFragment) ARouter.getInstance().build( ARouterPath.GIRLS_LIST_FGT).navigation();
        BaseFragment fragmentAbout = (BaseFragment) ARouter.getInstance().build( ARouterPath.ABOUT_FGT ).navigation();

        mFragments.add(fragmentNews);
        mFragments.add(fragmentGirls);
        mFragments.add(fragmentAbout);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        binding.setViewPaAdapter(mAdapter);
    }

    private ItemClick itemClick = id -> {
        switch (id) {
            case R.id.toGirls:
                //跳转到GirlsActivity
                ARouter.getInstance().build(ARouterPath.GIRLS_LIST_ATY)
                        .withTransition(R.anim.activity_down_in, R.anim.activity_down_out)
                        .navigation(MainActivity.this);
                break;
            case R.id.toNews:
                //跳转到NewsActivity
                ARouter.getInstance()
                        .build(ARouterPath.NEWS_LIST_ATY)
                        .withTransition(R.anim.activity_down_in, R.anim.activity_down_out)
                        .navigation(MainActivity.this, new NavigationCallback() {
                            @Override
                            public void onFound(Postcard postcard) {
                                Logger.e("ARouter onFound 找到跳转匹配路径");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Logger.e("ARouter onFound 没有匹配到跳转路径");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Logger.e("ARouter onFound 成功跳转");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Logger.e("ARouter onFound 跳转被中断");
                            }
                        });
                break;
            case R.id.toDynamic:
                //跳转到ActivityDynamicGirls (模拟动态url)
                ARouter.getInstance()
                        .build(ARouterPath.DYNA_GIRLS_LIST_ATY)
                        .withString("fullUrl", "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/1")
                        .withTransition(R.anim.activity_down_in, R.anim.activity_down_out)
                        .navigation(MainActivity.this, 3);
                break;
        }
    };

    public BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };

    public interface ItemClick {
        void onClick(int id);
    }
}
