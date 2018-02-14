package com.magicalrice.adolph.lib_common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * Created by Adolph on 2018/2/14.
 */

public class JsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static <T> T str2JsonBean(String json,Class<T> clazz) {
        T bean = null;
        if (null != gson) {
            try {
                bean = gson.fromJson(json,clazz);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    public static String jsonBean2Str(Object object) {
        String str = null;
        if (null != gson) {
            try {
                str = gson.toJson(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String jsonList2Str(List list) {
        String str = null;
        if (null != gson && list.size() > 0) {
            str = "[";
            for (int i = 0;i < list.size();i++) {
                if (i != list.size() - 1) {
                    str += gson.toJson(list.get(i)) + ",";
                } else if (i == list.size() - 1) {
                    str += gson.toJson(list.get(i)) + "]";
                }
            }
        }
        return str;
    }
}
