package com.magicalrice.adolph.lib_common.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Adolph on 2018/2/22.
 */

public class BindingUtils {

    @BindingAdapter("image")
    public static void loadImage(SimpleDraweeView image, String uri) {
        if (image != null) {
            image.setImageURI(uri);
        }
    }

    @BindingAdapter("image")
    public static void loadImage(SimpleDraweeView image, int id) {
        if (image != null) {
            image.setImageResource(id);
        }
    }

    @BindingAdapter("onNavigationItemSelectedListener")
    public static void setOnNavigationItemSelectedListener(BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter({"adapter"})
    public static void setRecyclerAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("viewPagerAdapter")
    public static void setViewPagerAdapter(ViewPager view, FragmentStatePagerAdapter adapter) {
        view.setAdapter(adapter);
    }
}
