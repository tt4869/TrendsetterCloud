package com.tutu.trendsettercloud.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tutu.trendsettercloud.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

public abstract class BaseFragment extends Fragment {

    public View rootView;
    public Activity mActivity;
    public Context mContext;
    private Toast mToast;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        mContext = getContext();
        initUI();
        initValues();
    }

    //初始化界面
    protected abstract void initUI();

    //初始化成员变量
    protected abstract void initValues();

    //获取布局文件
    protected abstract int getLayoutId();


    public void showToast(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    protected void showToast(int resId) {
        showToast(getString(resId));
    }

}
