package com.tutu.trendsettercloud.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.api.UrlConstants;
import com.tutu.trendsettercloud.base.BaseActivity;
import com.tutu.trendsettercloud.utils.StringUtil;
import com.tutu.trendsettercloud.utils.okgo.BaseResponse;
import com.tutu.trendsettercloud.utils.okgo.ErrorHandler;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSecondActivity extends BaseActivity {
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_referrer)
    EditText etReferrer;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    private String password;
    private String nickName;
    private String referrer;

    private String phone;
    private String code;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_second;
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("注册");
        tvRight.setVisibility(View.GONE);
    }

    @Override
    protected void initValues() {
        super.initValues();
        //获取手机号和验证码
        if (getIntent().hasExtra("phone")) {
            phone = getIntent().getStringExtra("phone");
            tvPhoneNumber.setText(phone);
        }
        if (getIntent().hasExtra("code")) {
            code = getIntent().getStringExtra("code");
        }

    }


    // TODO: 2018/7/19/019 调用注册接口，完成双注册，跳转至主界面
    @OnClick(R.id.btn_complete)
    public void onViewClicked() {
        if (verifyPassword()) return;
        if (verifyNickName()) return;

        HttpParams httpParams = new HttpParams();
        httpParams.put(Parameter.PHONE, phone);
        httpParams.put(Parameter.NICKNAME, nickName);
        httpParams.put(Parameter.PASSWORD, password);
        httpParams.put(Parameter.VERFICATIONCODE, code);
        referrer = etReferrer.getText().toString();
        if(!StringUtil.isEmity(referrer)){
            httpParams.put(Parameter.ETHID, referrer);
        }

        OkGo.<BaseResponse<Void>>post(UrlConstants.REGISTER_URL)
                .params(httpParams)
                .execute(new JsonCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Void>> response) {
                        showToast("注册成功");
                    }

                    @Override
                    public void onError(Response<BaseResponse<Void>> response) {
                        super.onError(response);
                        ErrorHandler.getInstance(mContext,response).handle();
                    }
                });
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
     * 校对昵称是否为空
     */
    private boolean verifyNickName() {
        nickName = etNickname.getText().toString();
        if (StringUtil.isEmity(nickName)) {
            showToast(getString(R.string.nickname_empty));
            return true;
        }
        return false;
    }

}
