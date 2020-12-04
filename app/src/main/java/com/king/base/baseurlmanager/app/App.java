package com.king.base.baseurlmanager.app;

import android.app.Application;

import com.king.base.baseurlmanager.BaseUrlManager;
import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class App extends Application {

    private String[] mUrls = {
            "http://192.168.100.100:8888",
            "http://192.168.100.108:9090",
            "http://192.168.100.160",
    };

    @Override
    public void onCreate() {
        super.onCreate();
        initBaseUrlManager();
    }

    /**
     *
     */
    private void initBaseUrlManager(){
        //初始化BaseUrlManager
        BaseUrlManager baseUrlManager = BaseUrlManager.getInstance();

        //-------------------------- 下面这一段可以完全不需要，主要用来演示
        if(baseUrlManager.getCount()==0){
            List<UrlInfo> list = new ArrayList<>();
            int length = mUrls.length;
            for (int i = 0; i < length; i++){
                list.add(new UrlInfo(mUrls[i]));
            }
            //你也可以提前把可能需要的环境提前加到BaseUrlManager
            baseUrlManager.addUrlInfo(list);
            //设置baseUrl
            //baseUrlManager.setUrlInfo();
        }
        //--------------------------

    }


}
