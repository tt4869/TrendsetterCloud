package com.tutu.trendsettercloud.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.api.UrlConstants;
import com.tutu.trendsettercloud.base.BaseActivity;
import com.tutu.trendsettercloud.ui.dialogfragment.NoConfirmDialogFragment;
import com.tutu.trendsettercloud.utils.StringUtil;
import com.tutu.trendsettercloud.utils.okgo.BaseResponse;
import com.tutu.trendsettercloud.utils.okgo.ErrorHandler;
import com.tutu.trendsettercloud.utils.okgo.JsonCallback;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;

    private String phone;
    private String verification_code;
    private String password;

    // 下发验证码计时
    public int count = 60;
    private MyHandler myHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("重置密码");
        tvRight.setVisibility(View.GONE);
    }

    @Override
    protected void initValues() {
        super.initValues();
        myHandler = new MyHandler(this);
    }

    @OnClick({R.id.btn_get_code, R.id.tv_no_code, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                if (verifyPhone()) return;
                // TODO: 2018/7/19/019 调用接口，获取验证码
                initTiming();
                break;
            case R.id.tv_no_code:
                NoConfirmDialogFragment noConfirmDialogFragment = new NoConfirmDialogFragment();
                noConfirmDialogFragment.show(getSupportFragmentManager(), "no_confirm");
                break;
            case R.id.btn_confirm:
                if (verifyPhone()) return;
                if (verifyCode()) return;
                if (verifyPassword()) return;
                // TODO: 2018/7/19/019 调用接口，修改密码，提示成功，返回主界面，重新登录
                resetPassword();
                break;
        }
    }


    private void resetPassword() {
        OkGo.<BaseResponse<Void>>post(UrlConstants.RESET_PWD_URL)
                .params(Parameter.PHONE,phone)
                .params(Parameter.PASSWORD,password)
                .params(Parameter.VERFICATIONCODE,verification_code)
                .execute(new JsonCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<Void>> response) {
                        showToast("密码修改成功");
                        onBackPressed();
                    }

                    @Override
                    public void onError(Response<BaseResponse<Void>> response) {
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
     * 校对验证码是否为空
     */
    private boolean verifyCode() {
        verification_code = etCode.getText().toString();
        if (StringUtil.isEmity(verification_code)) {
            showToast(getString(R.string.verification_code_empty));
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
     * 倒计时,60秒后重发验证码
     */
    private void initTiming() {
        count = 60;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count >= 0) {
                    SystemClock.sleep(1000);
                    myHandler.sendEmptyMessage(2);
                    count--;
                }
            }
        }).start();
    }

    static class MyHandler extends Handler {
        WeakReference<ForgetPasswordActivity> mActivity;

        MyHandler(ForgetPasswordActivity activity) {
            mActivity = new WeakReference<ForgetPasswordActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ForgetPasswordActivity forgetPasswordActivity = mActivity.get();
            switch (msg.what) {
                case 2:
                    // 更新计时器的数字显示
                    if (forgetPasswordActivity.count <= 60 && forgetPasswordActivity.count > 0) {
                        forgetPasswordActivity.btnGetCode.setEnabled(false);
                        forgetPasswordActivity.btnGetCode.setPadding(10, 0, 10, 0);
                        String text = forgetPasswordActivity.count + forgetPasswordActivity.getString(R.string.verification_code_after);
                        forgetPasswordActivity.btnGetCode.setText(text);
                    } else if (forgetPasswordActivity.count == 0) {
                        forgetPasswordActivity.btnGetCode.setEnabled(true);
                        forgetPasswordActivity.btnGetCode.setPadding(10, 0, 10, 0);
                        forgetPasswordActivity.btnGetCode.setText(forgetPasswordActivity.getString(R.string.get_verification_code));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
