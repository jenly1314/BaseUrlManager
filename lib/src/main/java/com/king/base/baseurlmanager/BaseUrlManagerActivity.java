package com.king.base.baseurlmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.baseurlmanager.adapter.UrlInfoAdapter;
import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.List;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BaseUrlManagerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private EditText etUrl;

    private IBaseUrlManager mBaseUrlManager;

    private UrlInfoAdapter mAdapter;

    private List<UrlInfo> listData;

    private String mRegex = BaseUrlManager.HTTP_URL_REGEX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_url_manager_activity);
        initUI();
    }

    private void initUI(){

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String title = bundle.getString(BaseUrlManager.KEY_TITLE);
            if(!TextUtils.isEmpty(title)){
                TextView tvTitle = findViewById(R.id.tvTitle);
                tvTitle.setText(title);
            }
            mRegex = bundle.getString(BaseUrlManager.KEY_REGEX);
        }


        recyclerView = findViewById(R.id.recyclerView);
        etUrl = findViewById(R.id.etUrl);

        mBaseUrlManager = BaseUrlManager.getInstance();

        listData = mBaseUrlManager.getUrlInfos();

        mAdapter = new UrlInfoAdapter(listData);

        mAdapter.setSelected(mBaseUrlManager.getUrlInfo());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(mAdapter);

    }

    /**
     * 保存选中的Url
     */
    private void saveSelected(){
        UrlInfo urlInfo = mAdapter.getSelectedItem();
        if(urlInfo!=null){
            mBaseUrlManager.setUrlInfo(urlInfo);
            mBaseUrlManager.refreshData();
            Intent intent = new Intent();
            intent.putExtra(BaseUrlManager.KEY_URL_INFO,urlInfo);
            setResult(RESULT_OK,intent);
            onBackPressed();
        }
    }


    /**
     * 添加Url
     */
    private void addUrl(){
        if(TextUtils.isEmpty(etUrl.getText())){
            etUrl.startAnimation(AnimationUtils.loadAnimation(this,R.anim.base_url_shake));
            return;
        }

        String url = etUrl.getText().toString().trim();

        if((!TextUtils.isEmpty(mRegex)) && !Pattern.matches(mRegex,url)){
            etUrl.startAnimation(AnimationUtils.loadAnimation(this,R.anim.base_url_shake));
            return;
        }


        UrlInfo urlInfo = new UrlInfo(url);

        mBaseUrlManager.addUrlInfo(urlInfo);

        mAdapter.notifyDataSetChanged();

        etUrl.clearFocus();

        //隐藏输入法
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etUrl.getWindowToken(),0);

        //滑动到最后一个
        int count = mAdapter.getItemCount();
        if(count>0){
            recyclerView.smoothScrollToPosition(count-1);
        }

    }


    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.ivBack) {
            onBackPressed();
        } else if (id == R.id.ivSave) {
            saveSelected();
        }else if(id == R.id.btnAdd){
            addUrl();
        }
    }
}