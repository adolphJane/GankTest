package com.magicalrice.adolph.lib_common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Adolph on 2018/2/22.
 */

public class CloseUtils {
    private CloseUtils() {
        throw new UnsupportedOperationException("CloseUtils can`t be initialized");
    }

    /**
     * 关闭IO
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静关闭IO
     * @param closeables
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
