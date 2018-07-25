package com.tutu.trendsettercloud.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.base.BaseRlvFragment;
import com.tutu.trendsettercloud.bean.MillBean;
import com.tutu.trendsettercloud.bean.MiningBean;

import java.util.ArrayList;
import java.util.List;


public class MillFragment extends BaseRlvFragment {

    //获取到的总数据
    private List<MillBean> millBeanList;
    //当前显示的数据
    private List<MillBean> currentList;

    public MillFragment() {
    }


    public static MillFragment newInstance() {
        MillFragment fragment = new MillFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initValues() {
        // TODO: 2018/7/20/020 测试数据
        millBeanList = new ArrayList<>();
        currentList = new ArrayList<>();
        for(int i=0;i<32;i++){
            millBeanList.add(new MillBean("192.168.0.1"+i,"CBWFQWEIOFHQWIOEFIOHQUGUGHQUIGH3UGQ3UIH3UGHQ3UHQ3U3UGUIFGQUHFEIFHUIQWEGVUADVNUIWEFHUAVAUQEHFQUFHQCQWHU"+i));
        }
        if(millBeanList.size()<10){
            currentList = millBeanList;
        }else{
            currentList = millBeanList.subList(0,10);
        }
        mAdapter.replaceAll(currentList);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mill;
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.mill_list;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(),2);
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        return new RecyclerViewAdapter<MillBean>(R.layout.item_mill) {
            @Override
            public void convert(MillBean millBean, AdapterViewHolder holder, int position) {
                holder.setText(R.id.tv_id,"ID:" + millBean.getID());
                holder.setText(R.id.tv_ip,millBean.getIP());
            }
        };
    }

    @Override
    protected void refreshData() {
        onLoadFinish(true);
    }

    @Override
    protected void loadMoreData() {
        if(millBeanList.size()-currentList.size() > 10){
            currentList = millBeanList.subList(0,currentList.size()+10);
            onLoadFinish(false);
        }else if(millBeanList.size() == currentList.size()){
            mRecyclerView.setNoMore(true);
        }else{
            currentList = millBeanList;
            onLoadFinish(false);
        }
        mAdapter.replaceAll(currentList);

    }

}
