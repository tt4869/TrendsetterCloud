package com.tutu.trendsettercloud.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.base.BaseFragment;
import com.tutu.trendsettercloud.config.Constants;
import com.tutu.trendsettercloud.config.MyConfig;
import com.tutu.trendsettercloud.ui.activity.IntegralDetailActivity;
import com.tutu.trendsettercloud.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_head_pic)
    ImageView ivHeadPic;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.integral)
    TextView integral;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.rl_integral_detail)
    RelativeLayout rlIntegralDetail;
    @BindView(R.id.rl_contacts)
    RelativeLayout rlContacts;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    Unbinder unbinder;


    public MineFragment() {
    }


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.rl_integral_detail, R.id.rl_contacts, R.id.rl_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_integral_detail:
                startActivity(new Intent(getActivity(), IntegralDetailActivity.class));
                break;
            case R.id.rl_contacts:
                showToast("联系人");
                break;
            case R.id.rl_exit:
                loginOut();
                break;
        }
    }

    private void loginOut() {
        MyConfig.clear(mContext, Constants.CONFIG_USERINFO);
        startActivity(new Intent(mContext, LoginActivity.class));
        mActivity.finish();
    }
}
