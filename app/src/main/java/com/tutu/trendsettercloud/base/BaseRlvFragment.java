package com.tutu.trendsettercloud.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.adapter.RecyclerViewAdapter;
import com.tutu.trendsettercloud.utils.SpacesItemDecoration;


public abstract class BaseRlvFragment extends BaseFragment {

    protected XRecyclerView mRecyclerView;
    protected abstract int getRecyclerViewId();
    protected abstract RecyclerView.LayoutManager getLayoutManager();
    public abstract RecyclerViewAdapter getAdapter();

    protected RecyclerViewAdapter mAdapter;
    protected boolean mRefresh;


    @Override
    protected void initUI() {
        initRecyclerView();
    }

    protected void initRecyclerView() {
        View view = rootView.findViewById(getRecyclerViewId());
        if (view == null || !(view instanceof XRecyclerView)) {
            return;
        }
        mRecyclerView = (XRecyclerView) view;
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLayoutManager(getLayoutManager());
        //设置item之间的间隔
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider);
        mRecyclerView.addItemDecoration(mRecyclerView.new DividerItemDecoration(dividerDrawable));
        mRecyclerView.setItemAnimator(null);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                //refresh data here
                onViewRefresh();
            }

            @Override
            public void onLoadMore() {
                //load more data here
                onViewLoadMore();
            }
        });


//        getAdapter().setHasStableIds(true);
        mAdapter = getAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                onItemClickListener(view, position);
            }
        });
    }







    public void onItemClickListener(View view, int position) {
    }


    /**
     * 刷新
     */
    public void onViewRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshData();
            }
        },1000);
    }



    /**
     * 加载更多
     */
    public void onViewLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreData();
            }
        },1000);
    }

    protected abstract void refreshData();


    protected abstract void loadMoreData();



    public void onLoadFinish(boolean mRefresh) {
        if (mRecyclerView != null) {
            if (mRefresh) {
                refreshingCompleted();
            } else {
                loadingMoreCompleted();
            }
        }
    }

    public void loadingMoreCompleted() {
        mRecyclerView.loadMoreComplete();
    }

    public void refreshingCompleted() {
        mRecyclerView.refreshComplete();
    }

}
