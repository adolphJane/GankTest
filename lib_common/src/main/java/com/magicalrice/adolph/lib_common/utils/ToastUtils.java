package com.magicalrice.adolph.lib_common.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Adolph on 2018/2/22.
 */

public class ToastUtils {
    private ToastUtils() {
        throw new UnsupportedOperationException("ToastUtils can`t be initialized");
    }

    private static Toast mToast;
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static boolean isJumpWhenMore;

    /**
     * 吐司初始化
     * @param isJumpWhenMore 当连续弹出吐司时，是要弹出新吐司，还是修改文本信息
     */
    public static void init(boolean isJumpWhenMore) {
        ToastUtils.isJumpWhenMore = isJumpWhenMore;
    }

    /**
     * 安全地显示短时吐司
     * @param text 文本
     */
    public static void showShortToastSafe(final CharSequence text) {
        mHandler.post(()-> showToast(text,Toast.LENGTH_SHORT));
    }

    /**
     * 安全地显示短时吐司
     * @param resId 资源ID
     */
    public static void showShortToastSafe(final @StringRes int resId) {
        mHandler.post(()-> showToast(resId,Toast.LENGTH_SHORT));
    }

    /**
     * 安全地显示短时吐司
     * @param resId 资源ID
     * @param args 参数
     */
    public static void showShortToastSafe(final @StringRes int resId,final Object... args) {
        mHandler.post(()-> showToast(resId,Toast.LENGTH_SHORT,args));
    }

    /**
     * 安全地显示短时吐司
     * @param format 格式
     * @param args 参数
     */
    public static void showShortToastSafe(final String format,final Object... args) {
        mHandler.post(()-> showToast(format,Toast.LENGTH_SHORT,args));
    }

    /**
     * 安全地显示长时吐司
     * @param text 文本
     */
    public static void showLongToastSafe(final CharSequence text) {
        mHandler.post(()-> showToast(text,Toast.LENGTH_LONG));
    }

    /**
     * 安全地显示长时吐司
     * @param resId 资源ID
     */
    public static void showLongToastSafe(final @StringRes int resId) {
        mHandler.post(()-> showToast(resId,Toast.LENGTH_LONG));
    }

    /**
     * 安全地显示长时吐司
     * @param resId 资源ID
     * @param args 参数
     */
    public static void showLongToastSafe(final @StringRes int resId,final Object... args) {
        mHandler.post(()-> showToast(resId,Toast.LENGTH_LONG,args));
    }

    /**
     * 安全地显示长时吐司
     * @param format 格式
     * @param args 参数
     */
    public static void showLongToastSafe(final String format,final Object... args) {
        mHandler.post(()-> showToast(format,Toast.LENGTH_LONG,args));
    }

    /**
     * 显示短时吐司
     *
     * @param text 文本
     */
    public static void showShortToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     */
    public static void showShortToast(@StringRes int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示短时吐司
     *
     * @param resId 资源Id
     * @param args  参数
     */
    public static void showShortToast(@StringRes int resId, Object... args) {
        showToast(resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示短时吐司
     *
     * @param format 格式
     * @param args   参数
     */
    public static void showShortToast(String format, Object... args) {
        showToast(format, Toast.LENGTH_SHORT, args);
    }

    /**
     * 显示长时吐司
     *
     * @param text 文本
     */
    public static void showLongToast(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     * @param resId 资源ID
     */
    public static void showLongToast(@StringRes int resId) {
        showToast(resId,Toast.LENGTH_LONG);
    }

    /**
     * 显示长时吐司
     * @param resId 资源ID
     * @param args 参数
     */
    public static void showLongToast(@StringRes int resId,Object... args) {
        showToast(resId,Toast.LENGTH_LONG,args);
    }

    /**
     * 显示长时吐司
     * @param format 格式
     * @param args 参数
     */
    public static void showLongToast(String format,Object... args) {
        showToast(format,Toast.LENGTH_LONG,args);
    }

    /**
     * 显示时长
     * @param resId 资源ID
     * @param duration 显示时长
     */
    private static void showToast(@StringRes int resId,int duration) {
        showToast(AppUtils.getContext().getResources().getString(resId),duration);
    }

    /**
     * 显示吐司
     * @param resId 资源ID
     * @param duration 显示时长
     * @param args 参数
     */
    private static void showToast(@StringRes int resId,int duration,Object... args) {
        showToast(String.format(AppUtils.getContext().getResources().getString(resId),args),duration);
    }

    /**
     * 显示吐司
     * @param format 文本格式
     * @param duration 显示时长
     * @param args 参数
     */
    private static void showToast(String format,int duration,Object... args) {
        showToast(String.format(format,args),duration);
    }

    /**
     * 显示吐司
     * @param text 文本
     * @param duration 时长
     */
    private static void showToast(CharSequence text,int duration) {
        if (isJumpWhenMore) cancelToast();
        if (mToast == null) {
            mToast = Toast.makeText(AppUtils.getContext(),text,duration);
            TextView tv = mToast.getView().findViewById(android.R.id.message);
            tv.setTextSize(18);
            mToast.setGravity(Gravity.CENTER,0,0);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    /**
     * 取消吐司显示
     */
    public static void cancelToast(){
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }
}
