package com.king.base.baseurlmanager.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.king.base.baseurlmanager.BaseUrlManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class MainActivity extends AppCompatActivity {

    private static final int SET_BASE_URL_REQUEST_CODE = 0X01;

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = BaseUrlManager.getInstance().getBaseUrl();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case SET_BASE_URL_REQUEST_CODE:
                    //方式1：通过BaseUrlManager获取baseUrl
                    mUrl = BaseUrlManager.getInstance().getBaseUrl();
                    //方式2：通过data直接获取baseUrl
//                    UrlInfo urlInfo = BaseUrlManager.parseActivityResult(data);
//                    mUrl = urlInfo.getBaseUrl();
                    Toast.makeText(this,mUrl,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    private void toSetBaseUrl(){

        BaseUrlManager.getInstance().startBaseUrlManager(this,SET_BASE_URL_REQUEST_CODE);

        //v1.0.x以前的写法
//        Intent intent = new Intent(this, BaseUrlManagerActivity.class);
////        intent.putExtra(BaseUrlManagerActivity.KEY_TITLE,"BaseUrl配置");
//        //可设置正则校验，可选项
////        intent.putExtra(BaseUrlManagerActivity.KEY_REGEX,BaseUrlManagerActivity.HTTP_URL_REGEX);
//        startActivityForResult(intent,SET_BASE_URL_REQUEST_CODE);


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSettings:
                toSetBaseUrl();
                break;
        }
    }
}
