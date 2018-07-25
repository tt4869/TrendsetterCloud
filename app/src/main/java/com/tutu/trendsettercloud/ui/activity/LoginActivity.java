package com.tutu.trendsettercloud.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.api.UrlConstants;
import com.tutu.trendsettercloud.base.BaseActivity;
import com.tutu.trendsettercloud.bean.UserInfoBean;
import com.tutu.trendsettercloud.config.MyConfig;
import com.tutu.trendsettercloud.utils.StringUtil;
import com.tutu.trendsettercloud.utils.okgo.BaseResponse;
import com.tutu.trendsettercloud.utils.okgo.ErrorHandler;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;

    private String phone;
    private String password;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("用户登录");
        ivBack.setVisibility(View.GONE);
        tvRight.setVisibility(View.GONE);

    }

    @Override
    protected void initValues() {
        super.initValues();
        test();
        if(!StringUtil.isEmity(MyConfig.getUserInfo(mContext).getPhone())){
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forget_password:
                // TODO: 2018/7/19/019 忘记密码
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            case R.id.tv_register:
                // TODO: 2018/7/19/019 注册
                startActivity( new Intent(this,RegisterFirstActivity.class));
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        if(verifyPhone()) return;
        if(verifyPassword()) return;

        OkGo.<BaseResponse<UserInfoBean>>post(UrlConstants.LOGIN_URL)
                .params(Parameter.PHONE,phone)
                .params(Parameter.PASSWORD,password)
                .execute(new JsonCallback<BaseResponse<UserInfoBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<UserInfoBean>> response) {
                        // 保存登录信息
                        UserInfoBean userInfoBean = response.body().data;
                        MyConfig.saveUserInfo(mContext,userInfoBean);
                        startActivity(new Intent(mContext, MainActivity.class));
                    }

                    @Override
                    public void onError(Response<BaseResponse<UserInfoBean>> response) {
                        ErrorHandler.getInstance(mContext,response).handle();
                    }
                });
    }


    /**
     * 校对手机号是否为空，格式是否有误
     */
    private boolean verifyPhone() {
        phone = etPhone.getText().toString();
        if (StringUtil.isEmity(phone)) {
            showToast(getString(R.string.phone_empty));
            return true;
        }
        if (!StringUtil.isMobileNO(phone)) {
            showToast(getString(R.string.no_format_phone));
            return true;
        }
        return false;
    }

    /**
     * 校对密码是否为空
     */
    private boolean verifyPassword() {
        password = etPassword.getText().toString();
        if (StringUtil.isEmity(password)) {
            showToast(getString(R.string.password_empty));
            return true;
        }
        return false;
    }

    /**
     * 添加测试数据
     */
    private void test() {
        etPhone.setText("13000000000");
        etPassword.setText("jin123");
    }
}
