package com.king.base.baseurlmanager.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.king.base.baseurlmanager.BaseUrlManagerActivity;
import com.king.base.baseurlmanager.bean.UrlInfo;

public class MainActivity extends AppCompatActivity {

    private static final int SET_BASE_URL_REQUEST_CODE = 0X01;

    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = getApp().getBaseUrl();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case SET_BASE_URL_REQUEST_CODE:
                    //方式1：通过刷新数据，获取baseUrl
//                    getApp().getBaseUrlManager().refreshData();
//                    mUrl = getApp().getBaseUrl();
                    //方式2：通过data直接获取baseUrl
                    UrlInfo urlInfo = data.getParcelableExtra(BaseUrlManagerActivity.KEY_URL_INFO);
                    mUrl = urlInfo.getBaseUrl();
                    Toast.makeText(this,mUrl,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public App getApp(){
        return (App)getApplication();
    }

    private void toSetBaseUrl(){
        Intent intent = new Intent(this, BaseUrlManagerActivity.class);
//        intent.putExtra(BaseUrlManagerActivity.KEY_TITLE,"BaseUrl配置");
        startActivityForResult(intent,SET_BASE_URL_REQUEST_CODE);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnSettings:
                toSetBaseUrl();
                break;
        }
    }
}
