package com.king.base.baseurlmanager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.king.base.baseurlmanager.bean.UrlInfo;
import com.king.base.baseurlmanager.util.BaseUrlUtil;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BaseUrlManager implements IBaseUrlManager {

    private Context context;

    private List<UrlInfo> urlInfos;

    private UrlInfo urlInfo;

    private String baseUrl;


    public BaseUrlManager(@NonNull Context context){
        this.context = context;
        refreshData();
    }


    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public UrlInfo getUrlInfo() {
        return urlInfo;
    }

    @Override
    public void setUrlInfo(@NonNull UrlInfo urlInfo) {
        this.urlInfo = urlInfo;
        this.baseUrl = urlInfo.getBaseUrl();
        if(urlInfos!=null){
            if(urlInfos.contains(urlInfo)){
                urlInfos.remove(urlInfo);
            }
            urlInfos.add(urlInfo);
            BaseUrlUtil.put(context,urlInfo,true);
        }
    }

    @Override
    public void addUrlInfo(@NonNull UrlInfo urlInfo) {
        if(urlInfos!=null){
            if(urlInfos.contains(urlInfo)){
                urlInfos.remove(urlInfo);
            }
            urlInfos.add(urlInfo);
            BaseUrlUtil.put(context,urlInfo);

        }
    }

    @Override
    public void addUrlInfo(@NonNull Collection<UrlInfo> list) {
        if(urlInfos!=null){
            urlInfos.addAll(list);
            BaseUrlUtil.put(context,list);
        }
    }

    @Override
    public void remove(@NonNull UrlInfo urlInfo) {
        if(urlInfos!=null){
            urlInfos.remove(urlInfo);
            BaseUrlUtil.remove(context,urlInfo.getBaseUrl());
        }
    }

    @Override
    public void clear() {
        if(urlInfos!=null){
            urlInfos.clear();
        }
        urlInfo = null;
        baseUrl = null;
        BaseUrlUtil.clear(context);
    }

    @Override
    public List<UrlInfo> getUrlInfos() {
        return urlInfos;
    }


    @Override
    public int getCount() {
        return urlInfos != null ? urlInfos.size() : 0;
    }

    @Override
    public void refreshData() {
        baseUrl = BaseUrlUtil.getBaseUrl(context);
        urlInfo = BaseUrlUtil.getUrlInfo(context,baseUrl);
        urlInfos = BaseUrlUtil.getUrlInfos(context);
    }
}
