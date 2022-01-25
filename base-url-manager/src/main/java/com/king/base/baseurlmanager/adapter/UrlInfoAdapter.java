package com.king.base.baseurlmanager.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.king.base.baseurlmanager.R;
import com.king.base.baseurlmanager.bean.UrlInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class UrlInfoAdapter extends RecyclerView.Adapter<UrlInfoAdapter.UrlViewHolder> {


    private List<UrlInfo> listData;

    private UrlInfo urlInfo;

    public UrlInfoAdapter() {
        listData = new ArrayList<>();
    }

    public UrlInfoAdapter(List<UrlInfo> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public UrlViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_url_rv_url_info_item, viewGroup, false);
        return new UrlViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UrlViewHolder viewHolder, int position) {
        convert(viewHolder, listData.get(position), position);
    }

    private void convert(UrlViewHolder holder, UrlInfo item, final int position) {
        holder.setText(R.id.tvUrl, item.getBaseUrl());

        holder.setSelected(R.id.ivSelect, item.equals(urlInfo));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }


    public void setSelected(int position) {
        if (position >= 0 && position < getItemCount()) {
            urlInfo = listData.get(position);
        } else {
            urlInfo = null;
        }
        notifyDataSetChanged();
    }

    public void noSelected() {
        setSelected(-1);
    }

    public void setSelected(@NonNull UrlInfo urlInfo) {
        if (listData != null) {
            for (int i = 0; i < listData.size(); i++) {
                if (urlInfo.equals(listData.get(i))) {
                    setSelected(i);
                    return;
                }
            }
            noSelected();
        }
    }

    public UrlInfo getSelectedItem() {
        return urlInfo;
    }


    public void replace(Collection<UrlInfo> urlInfoList) {
        if (listData != urlInfoList) {
            if (listData == null) {
                listData = new ArrayList<>();
            } else {
                listData.clear();
            }

            if (urlInfoList != null) {
                listData.addAll(urlInfoList);
            }
        }

        notifyDataSetChanged();
    }

    public void add(Collection<UrlInfo> urlInfoList) {
        if (listData != urlInfoList) {
            if (listData == null) {
                listData = new ArrayList<>();
            }
            if (urlInfoList != null) {
                listData.addAll(urlInfoList);
            }
        }

        notifyDataSetChanged();
    }

    public void add(UrlInfo urlInfo) {
        if (listData == null) {
            listData = new ArrayList<>();
        }

        listData.add(urlInfo);

        notifyDataSetChanged();
    }

    protected static class UrlViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> views;

        public UrlViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        public <T extends View> T getView(@IdRes int resId) {
            View view = views.get(resId);
            if (view == null) {
                view = itemView.findViewById(resId);
                views.put(resId, view);
            }

            return (T) view;
        }

        public void setText(@IdRes int resId, CharSequence text) {
            TextView textView = getView(resId);
            textView.setText(text);
        }

        public void setSelected(@IdRes int resId, boolean isSelected) {
            getView(resId).setSelected(isSelected);
        }


    }
}
