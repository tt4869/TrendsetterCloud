package com.tutu.trendsettercloud.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.api.UrlConstants;
import com.tutu.trendsettercloud.base.BaseRlvFragment;
import com.tutu.trendsettercloud.bean.MiningBean;
import com.tutu.trendsettercloud.config.MyConfig;
import com.tutu.trendsettercloud.utils.okgo.BaseResponse;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 挖矿记录
 * 空数据界面
 */
public class HomeFragment extends BaseRlvFragment {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;


    //显示数据
    private List<MiningBean> miningBeanList;
    //当前请求到的数据
    private List<MiningBean> currentList;


    //一次加载10条数据
    private static final int PAGE_SIZE = 10;

    private int pageNum = 1;

    private static final int TYPE_FIRST = 1;
    private static final int TYPE_REFRESH = 2;
    private static final int TYPE_LOAD = 3;


    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initValues() {
        // TODO: 2018/7/20/020 测试数据
//        miningBeanList = new ArrayList<>();
//        currentList = new ArrayList<>();
//        for(int i=0;i<60;i++){
//            miningBeanList.add(new MiningBean(i,"2018-7-20 15:00:"+i));
//        }
//        if(miningBeanList.size()<15){
//            currentList = miningBeanList;
//        }else{
//            currentList = miningBeanList.subList(0,15);
//        }
//        mAdapter.replaceAll(currentList);
//        tvMoney.setText("93.10");
        currentList = new ArrayList<>();
        miningBeanList = new ArrayList<>();
        getData(pageNum, PAGE_SIZE, TYPE_FIRST);

    }

    private void getData(int pageNum, int pageSize, final int type) {
        OkGo.<BaseResponse<List<MiningBean>>>post(UrlConstants.GET_MININGREC_URL)
                .params(Parameter.ETHID, MyConfig.getUserInfo(mContext).getEthId())
                .params(Parameter.PAGENUM, pageNum)
                .params(Parameter.PAGESIZE, pageSize)
                .execute(new JsonCallback<BaseResponse<List<MiningBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<List<MiningBean>>> response) {
                        currentList = response.body().data;
                        if (type == TYPE_REFRESH) {
                            onLoadFinish(true);
                        } else if (type == TYPE_LOAD) {
                            onLoadFinish(false);
                        }

                        if (currentList.size() > 0) {
                            //获取到了数据,进行数据显示
                            mRecyclerView.setVisibility(View.VISIBLE);
                            llEmpty.setVisibility(View.GONE);

                            if (miningBeanList.size() == 0) {
                                miningBeanList = currentList;
                                mAdapter.replaceAll(miningBeanList);
                            } else if (type == TYPE_LOAD && currentList.get(currentList.size() - 1).getId().equals(miningBeanList.get(miningBeanList.size() - 1).getId())) {
                                // TODO: 2018/7/24/024 后台错误，页数不同，获取的数据相同
                                mRecyclerView.setNoMore(true);
                            } else {
                                //添加数据
                                miningBeanList.addAll(currentList);
                                mAdapter.replaceAll(miningBeanList);
                            }
                        } else {
                            //没有获取到数据，且之前也没有数据
                            if (miningBeanList.size() == 0) {
                                mRecyclerView.setVisibility(View.GONE);
                                llEmpty.setVisibility(View.VISIBLE);
                                tvEmpty.setText("暂无记录，点击刷新");
                            }
                            //没有获取到数据，表示已经不能再下拉
                            mRecyclerView.setNoMore(true);
                        }


                    }
                });
    }

    @Override
    protected void initUI() {
        super.initUI();

        tvMoney.setText(""+MyConfig.getUserInfo(mContext).getEthCurrency());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected int getRecyclerViewId() {
        return R.id.mining_list;
    }


    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    public RecyclerViewAdapter getAdapter() {
        return new RecyclerViewAdapter<MiningBean>(R.layout.item_mining) {
            @Override
            public void convert(MiningBean miningBean, AdapterViewHolder holder, int position) {
                holder.setText(R.id.amount, "+" + miningBean.getCurrencyValue());
                holder.setText(R.id.date, miningBean.getMiningDatetime());
            }
        };
    }


    @Override
    protected void refreshData() {
        getData(1, miningBeanList.size(), TYPE_REFRESH);
    }

    @Override
    protected void loadMoreData() {
        pageNum++;
        getData(pageNum, PAGE_SIZE, TYPE_LOAD);

//        if(miningBeanList.size()-currentList.size() > 15){
//            currentList = miningBeanList.subList(0,currentList.size()+15);
//            onLoadFinish(false);
//        }else if(miningBeanList.size() == currentList.size()){
//            mRecyclerView.setNoMore(true);
//        }else{
//            currentList = miningBeanList;
//            onLoadFinish(false);
//        }
//        mAdapter.replaceAll(currentList);

    }


    @OnClick(R.id.ll_empty)
    public void onViewClicked() {
        tvEmpty.setText("刷新中");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getData(pageNum, PAGE_SIZE, TYPE_FIRST);
            }
        },1000);
    }
}
