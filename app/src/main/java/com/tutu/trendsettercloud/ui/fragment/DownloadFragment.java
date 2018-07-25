package com.tutu.trendsettercloud.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.base.BaseRlvFragment;
import com.tutu.trendsettercloud.bean.DownloadBean;
import com.tutu.trendsettercloud.bean.MillBean;
import com.tutu.trendsettercloud.bean.MiningBean;

import java.util.ArrayList;
import java.util.List;


public class DownloadFragment extends BaseRlvFragment {

    //获取到的总数据
    private List<DownloadBean> downloadBeanList;
    //当前显示的数据
    private List<DownloadBean> currentList;

    public DownloadFragment() {
    }


    public static DownloadFragment newInstance() {
        DownloadFragment fragment = new DownloadFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected int getRecyclerViewId() {
        return R.id.file_list;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        return new RecyclerViewAdapter<DownloadBean>(R.layout.item_download) {
            @Override
            public void convert(DownloadBean downloadBean, AdapterViewHolder holder, int position) {
                holder.setText(R.id.tv_fileName,downloadBean.getFileName());
                holder.setText(R.id.tv_date,downloadBean.getDate());
            }
        };
    }

    @Override
    protected void refreshData() {
        onLoadFinish(true);
    }

    @Override
    protected void loadMoreData() {
        if(downloadBeanList.size()-currentList.size() > 15){
            currentList = downloadBeanList.subList(0,currentList.size()+15);
            onLoadFinish(false);
        }else if(downloadBeanList.size() == currentList.size()){
            mRecyclerView.setNoMore(true);
        }else{
            currentList = downloadBeanList;
            onLoadFinish(false);
        }
        mAdapter.replaceAll(currentList);
    }


    @Override
    protected void initValues() {
        downloadBeanList = new ArrayList<>();
        currentList = new ArrayList<>();
        for(int i=0;i<60;i++){
            downloadBeanList.add(new DownloadBean("机密文件"+i,"2018-7-20 15:00:"+i));
        }
        if(downloadBeanList.size()<15){
            currentList = downloadBeanList;
        }else{
            currentList = downloadBeanList.subList(0,15);
        }
        mAdapter.replaceAll(currentList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_download;
    }
}
