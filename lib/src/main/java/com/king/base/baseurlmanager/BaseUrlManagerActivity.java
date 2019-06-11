package com.king.base.baseurlmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.king.base.baseurlmanager.adapter.UrlInfoAdapter;
import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.List;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BaseUrlManagerActivity extends AppCompatActivity {

    public static final String KEY_TITLE = "key_title";

    private RecyclerView recyclerView;

    private EditText etUrl;

    private Button btnAdd;

    private IBaseUrlManager mBaseUrlManager;

    private UrlInfoAdapter mAdapter;

    private List<UrlInfo> listData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_url_manager_activity);
        initUI();
    }

    private void initUI(){

        String title = getIntent().getStringExtra(KEY_TITLE);
        if(!TextUtils.isEmpty(title)){
            TextView tvTitle = findViewById(R.id.tvTitle);
            tvTitle.setText(title);
        }

        recyclerView = findViewById(R.id.recyclerView);
        etUrl = findViewById(R.id.etUrl);
        btnAdd = findViewById(R.id.btnAdd);

        mBaseUrlManager = new BaseUrlManager(this);

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
            setResult(RESULT_OK);
            onBackPressed();
        }
    }


    /**
     * 添加Url
     */
    private void addUrl(){
        if(TextUtils.isEmpty(etUrl.getText())){
            return;
        }

        String url = etUrl.getText().toString().trim();

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
