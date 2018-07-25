package com.tutu.trendsettercloud.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.AdapterViewHolder;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.api.UrlConstants;
import com.tutu.trendsettercloud.base.BaseRlvActivity;
import com.tutu.trendsettercloud.bean.IntegralDetailBean;
import com.tutu.trendsettercloud.config.MyConfig;
import com.tutu.trendsettercloud.utils.okgo.BaseResponse;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralDetailActivity extends BaseRlvActivity {
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    //显示数据
    private List<IntegralDetailBean> integralDetailBeanList;
    //当前请求到的数据
    private List<IntegralDetailBean> currentList;

    //一次加载10条数据
    private static final int PAGE_SIZE = 10;

    private int pageNum = 1;

    private static final int TYPE_FIRST = 1;
    private static final int TYPE_REFRESH = 2;
    private static final int TYPE_LOAD = 3;


    @Override
    protected void initUI() {
        super.initUI();
        tvRight.setVisibility(View.GONE);
        tvTitle.setText("积分明细");
    }

    @Override
    protected void initValues() {
        super.initValues();
        pageNum = 1 ;
        currentList = new ArrayList<>();
        integralDetailBeanList = new ArrayList<>();
        getData(pageNum, PAGE_SIZE, TYPE_FIRST);
    }

    private void getData(int pageNum, int pageSize, final int type) {
        OkGo.<BaseResponse<List<IntegralDetailBean>>>post(UrlConstants.GET_INTEGRAL_URL)
                .params(Parameter.USERID, MyConfig.getUserInfo(mContext).getId())
                .params(Parameter.PAGE, pageNum)
                .params(Parameter.PAGESIZE, pageSize)
                .execute(new JsonCallback<BaseResponse<List<IntegralDetailBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<List<IntegralDetailBean>>> response) {
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

                            if (integralDetailBeanList.size() == 0) {
                                integralDetailBeanList = currentList;
                                mAdapter.replaceAll(integralDetailBeanList);
                            } else if (currentList.get(currentList.size() - 1).getId().equals(integralDetailBeanList.get(integralDetailBeanList.size() - 1).getId())) {
                                // TODO: 2018/7/24/024 后台错误，页数不同，获取的数据相同
                                if(type == TYPE_LOAD){
                                    mRecyclerView.setNoMore(true);
                                }
                            } else {
                                //添加数据
                                integralDetailBeanList.addAll(currentList);
                                mAdapter.replaceAll(integralDetailBeanList);
                            }
                        } else {
                            //没有获取到数据，且之前也没有数据
                            if (integralDetailBeanList.size() == 0) {
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
                holder.setText(R.id.tv_kind, integralDetailBean.getRemark());
                holder.setText(R.id.amount, "+" + integralDetailBean.getIntegral());
                holder.setText(R.id.date, integralDetailBean.getCreateDatetime());
            }
        };
    }

    @Override
    protected void refreshData() {
        getData(1, integralDetailBeanList.size(), TYPE_REFRESH);
    }

    @Override
    protected void loadMoreData() {
        pageNum++;
        getData(pageNum, PAGE_SIZE, TYPE_LOAD);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_integral_detail;
    }

}
