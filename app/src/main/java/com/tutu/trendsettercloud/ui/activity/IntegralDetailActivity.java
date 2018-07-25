package com.tutu.trendsettercloud.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.base.BaseRlvActivity;
import com.tutu.trendsettercloud.bean.IntegralDetailBean;

import java.util.ArrayList;
import java.util.List;

public class IntegralDetailActivity extends BaseRlvActivity {

    private List<IntegralDetailBean> integralDetailBeanList;
    private List<IntegralDetailBean> currentList;

    @Override
    protected void initUI() {
        super.initUI();
        tvRight.setVisibility(View.GONE);
        tvTitle.setText("积分明细");
    }

    @Override
    protected void initValues() {
        super.initValues();
        integralDetailBeanList = new ArrayList<>();
        currentList = new ArrayList<>();
        for(int i=0;i<60;i++){
            integralDetailBeanList.add(new IntegralDetailBean("推荐奖励",i,"2018-7-20 15:00:"+i));
        }
        if(integralDetailBeanList.size()<15){
            currentList = integralDetailBeanList;
        }else{
            currentList = integralDetailBeanList.subList(0,15);
        }
        mAdapter.replaceAll(currentList);
    }

    @Override
    protected int getRecyclerViewId() {
        return R.id.integral_detail_list;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        return new RecyclerViewAdapter<IntegralDetailBean>(R.layout.item_integral) {
            @Override
            public void convert(IntegralDetailBean integralDetailBean, AdapterViewHolder holder, int position) {
                holder.setText(R.id.tv_kind,integralDetailBean.getKind());
                holder.setText(R.id.amount,"+" + integralDetailBean.getAmount());
                holder.setText(R.id.date,integralDetailBean.getDate());
            }
        };
    }

    @Override
    protected void refreshData() {
        onLoadFinish(true);
    }

    @Override
    protected void loadMoreData() {
        if(integralDetailBeanList.size()-currentList.size() > 15){
            currentList = integralDetailBeanList.subList(0,currentList.size()+15);
            onLoadFinish(false);
        }else if(integralDetailBeanList.size() == currentList.size()){
            mRecyclerView.setNoMore(true);
        }else{
            currentList = integralDetailBeanList;
            onLoadFinish(false);
        }
        mAdapter.replaceAll(currentList);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_detail;
    }
}
