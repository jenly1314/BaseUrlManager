package com.king.base.baseurlmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public final class BaseUrlUtil {

    private static final String DEFAULT_PREF_NAME = "base_url_manager";
    private static final String CURRENT_URL_PREF_NAME = "cur_base_url";
    private static final String KEY_BASE_URL = "key_base_url";

    private BaseUrlUtil(){
        throw new AssertionError();
    }

    /**
     * 缓存String
     * @param context
     * @param key
     * @param value
     */
    public static void put(@NonNull Context context, @NonNull String key, Long value){
        getSharedPreferences(context).edit()
                .putLong(key,value)
                .commit();
    }

    /**
     * 缓存Map<String,Long>
     * @param context
     * @param map
     */
    public static void put(@NonNull Context context, Map<String,Long> map){
        if(map!=null){
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();

            for(Map.Entry<String, Long> entry: map.entrySet()){
                if(!TextUtils.isEmpty(entry.getKey()))
                    editor.putLong(entry.getKey(), entry.getValue());
            }

            editor.commit();

        }
    }

    /**
     * 缓存UrlInfo
     * @param context
     * @param urlInfo
     */
    public static void put(@NonNull Context context,@NonNull UrlInfo urlInfo){
        put(context,urlInfo,false);
    }


    /**
     * 缓存一个UrlInfo
     * @param context
     * @param urlInfo
     * @param isCurBaseUrl 是否设置为当前baseUrl
     */
    public static void put(@NonNull Context context,@NonNull UrlInfo urlInfo,boolean isCurBaseUrl){
        put(context,urlInfo.getBaseUrl(),urlInfo.getTime());
        if(isCurBaseUrl){
            getSharedPreferences(context,CURRENT_URL_PREF_NAME)
                    .edit()
                    .putString(KEY_BASE_URL,urlInfo.getBaseUrl())
                    .commit();
        }
    }

    /**
     * 缓存UrlInfo结合
     * @param context
     * @param urlInfos
     */
    public static void put(@NonNull Context context, Collection<UrlInfo> urlInfos){
        if(urlInfos!=null && !urlInfos.isEmpty()){
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();

            for(UrlInfo urlInfo : urlInfos){
                editor.putLong(urlInfo.getBaseUrl(),urlInfo.getTime());
            }

            editor.commit();

        }
    }

    /**
     * 移除指定的key
     * @param context
     * @param key
     */
    public static void remove(@NonNull Context context,String key){
        getSharedPreferences(context).edit()
                .remove(key)
                .commit();
    }

    /**
     * 清除缓存
     * @param context
     */
    public static void clear(@NonNull Context context){
        getSharedPreferences(context).edit().clear().commit();
        getSharedPreferences(context,CURRENT_URL_PREF_NAME).edit().clear().commit();
    }

    /**
     * 获取Long
     * @param context
     * @param key
     * @return
     */
    public static long getLong(Context context,@NonNull String key){
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getLong(key,0);
    }

    /**
     * 获取UrlInfo
     * @param context
     * @param url
     * @return
     */
    public static UrlInfo getUrlInfo(Context context,@NonNull String url){
        long time = getLong(context,url);
        if(time>0){
            return new UrlInfo(url,time);
        }
        return new UrlInfo(url);
    }

    /**
     * 获取BaseUrl
     * @param context
     * @return
     */
    public static String getBaseUrl(Context context){
        return getSharedPreferences(context,CURRENT_URL_PREF_NAME).getString(KEY_BASE_URL,"");
    }


    /**
     * 获取当前的UrlInfo
     * @param context
     * @return
     */
    public static UrlInfo getBseUrlInfo(Context context){
        String baseUrl = getBaseUrl(context);

        UrlInfo urlInfo = null;
        if(!TextUtils.isEmpty(baseUrl)){
            urlInfo = getUrlInfo(context,baseUrl);
        }
        return urlInfo;
    }

    /**
     * 获取UrlInfo集合
     * @param context
     * @return
     */
    @NonNull
    public static List<UrlInfo> getUrlInfoList(@NonNull Context context){
        SharedPreferences sp = getSharedPreferences(context);
        List<UrlInfo> list = new ArrayList<>();
        try{
            Map<String,Long> map = (Map<String,Long>)sp.getAll();
            if(map!=null){
                for(Map.Entry<String,Long> entry : map.entrySet()){
                    list.add(new UrlInfo(entry.getKey(),entry.getValue()));
                }
            }
            Collections.sort(list);
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 获取数据存储
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(@NonNull Context context){
        return context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取数据存储
     * @param context
     * @param prefName
     * @return
     */
    private static SharedPreferences getSharedPreferences(@NonNull Context context,String prefName){
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }
}
