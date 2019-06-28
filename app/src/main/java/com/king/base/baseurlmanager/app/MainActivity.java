package com.king.base.baseurlmanager.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.king.base.baseurlmanager.BaseUrlManagerActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
                    getApp().getBaseUrlManager().refreshData();
                    mUrl = getApp().getBaseUrl();
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
