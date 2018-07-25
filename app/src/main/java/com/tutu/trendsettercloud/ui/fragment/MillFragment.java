package com.tutu.trendsettercloud.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.base.BaseRlvFragment;
import com.tutu.trendsettercloud.bean.MillBean;
import com.tutu.trendsettercloud.bean.MiningBean;
import com.tutu.trendsettercloud.config.Constants;
import com.tutu.trendsettercloud.utils.okgo.ErrorHandler;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;
import com.tutu.trendsettercloud.utils.okgo.NoCodeJsonCallBack;

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
    protected void initUI() {
        super.initUI();
        mRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void initValues() {
        millBeanList = new ArrayList<>();
        getData();
    }


    private String jionUrl(int end){
        return String.format("http://192.168.0.%1$s:5001/api/v0/id",end);
    }

    private void getData() {
        for( int i=0;i<=255 ; i++){
            OkGo.<MillBean>get(jionUrl(i))
                    .tag(this)
                    .retryCount(0)
                    .execute(new NoCodeJsonCallBack<MillBean>(MillBean.class) {
                @Override
                public void onSuccess(Response<MillBean> response) {
                    MillBean millBean = response.body();
                    String url = response.getRawResponse().request().url().toString();
                    String IP = url.substring(7,url.length()).split(":")[0];
                    if(IP.equals("192.168.0.255")){
                        onLoadFinish(true);
                    }
                    millBean.setIP(IP);
                    millBeanList.add(millBean);
                    mAdapter.replaceAll(millBeanList);
                }

                @Override
                public void onError(Response<MillBean> response) {
                    super.onError(response);
                    String url = response.getRawCall().request().url().toString();
                    String IP = url.substring(7,url.length()).split(":")[0];
                    if(IP.equals("192.168.0.255")){
                        onLoadFinish(true);
                    }
                }
            });

        }
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
                holder.setText(R.id.tv_id,millBean.getID());
                holder.setText(R.id.tv_ip,millBean.getIP());
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void refreshData() {
        OkGo.getInstance().cancelTag(this);
        millBeanList = new ArrayList<>();
        getData();
    }

    @Override
    protected void loadMoreData() {
    }
}
