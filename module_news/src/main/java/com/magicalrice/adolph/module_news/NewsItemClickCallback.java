package com.magicalrice.adolph.module_news;

import com.magicalrice.adolph.lib_common.datamodel.http.entities.NewsData;

/**
 * Created by Adolph on 2018/2/23.
 */

public interface NewsItemClickCallback {
    void onClick(NewsData newsItem);
}
