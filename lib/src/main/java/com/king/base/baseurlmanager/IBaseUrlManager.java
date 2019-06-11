package com.king.base.baseurlmanager;

import android.support.annotation.NonNull;

import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public interface IBaseUrlManager {

    /**
     * 获取BaseUrl
     * @return
     */
    String getBaseUrl();

    /**
     * 获取UrlInfo
     * @return
     */
    UrlInfo getUrlInfo();

    /**
     * 设置UrlInfo
     * @param urlInfo
     */
    void setUrlInfo(@NonNull UrlInfo urlInfo);

    /**
     * 添加UrlInfo
     * @param urlInfo
     */
    void addUrlInfo(@NonNull UrlInfo urlInfo);

    /**
     * 添加UrlInfo集合
     * @param list
     */
    void addUrlInfo(@NonNull Collection<UrlInfo> list);
    

    /**
     * 移除UrlInfo
     * @param urlInfo
     */
    void remove(@NonNull UrlInfo urlInfo);

    /**
     * 清除
     */
    void clear();

    /**
     * 获取UrlInfo集合
     * @return
     */
    List<UrlInfo> getUrlInfos();

    /**
     * 获取url数
     * @return
     */
    int getCount();

    /**
     * 刷新数据
     */
    void refreshData();
}
