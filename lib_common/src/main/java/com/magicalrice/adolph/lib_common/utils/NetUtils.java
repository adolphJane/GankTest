package com.magicalrice.adolph.lib_common.utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by Adolph on 2018/2/14.
 */

public class NetUtils {
    public static final int DISCONNECTED = 0;
    public static final int WIFI_CONNECTED = 1;
    public static final int ETHERNET_CONNECTED = 2;

    public static int getNetConnStatus(Context context) {
        if (context == null) {
            return DISCONNECTED;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        int status = DISCONNECTED;
        if (info != null && info.isAvailable() && info.isConnected()) {
            status = WIFI_CONNECTED;
        } else {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                status = ETHERNET_CONNECTED;
            }
        }
        return status;
    }

    /**
     * 当前网络是否已经连接
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo[] infos = manager.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo info : infos) {
                        if (info != null && info.isConnected()) {
                            if (info.getState() == NetworkInfo.State.CONNECTED) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static LiveData<Boolean> netConnected(Context context) {
        MutableLiveData<Boolean> isNetConnect = new MutableLiveData<>();
        if (context == null) {
            isNetConnect.setValue(false);
            return isNetConnect;
        }
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] infos = connectivity.getAllNetworkInfo();
                if (infos != null) {
                    for (NetworkInfo info : infos) {
                        if (info != null && info.isConnected()) {
                            if (info.getState() == NetworkInfo.State.CONNECTED) {
                                return isNetConnect;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetConnect;
    }
}
