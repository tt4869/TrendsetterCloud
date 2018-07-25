package com.tutu.trendsettercloud.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.base.BaseActivity;
import com.tutu.trendsettercloud.ui.dialogfragment.NoConfirmDialogFragment;
import com.tutu.trendsettercloud.utils.StringUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFirstActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.btn_next)
    Button btnNext;

    // 下发验证码计时
    public int count = 60;
    // 手机号
    private String phone;
    // 验证码
    private String verification_code;

    private MyHandler myHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_first;
    }


    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("注册");
        tvRight.setVisibility(View.GONE);
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void initValues() {
        super.initValues();
        myHandler = new MyHandler(this);
        test();
    }

    @OnClick({R.id.btn_get_code, R.id.tv_no_code, R.id.btn_next})
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
            case R.id.btn_next:
                if (verifyPhone()) return;
                if (verifyCode()) return;
                // TODO: 2018/7/19/019 点击下一步，调用注册接口，验证后注册在JAVA服务器端注册账号，跳转进入下一注册流程
                //传递验证码到下一个界面
                Bundle bundle = new Bundle();
                bundle.putString("phone",phone);
                bundle.putString("code",verification_code);
                Intent intent = new Intent(this,RegisterSecondActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
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
        WeakReference<RegisterFirstActivity> mActivity;

        MyHandler(RegisterFirstActivity activity) {
            mActivity = new WeakReference<RegisterFirstActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            RegisterFirstActivity registerFirstActity = mActivity.get();
            switch (msg.what) {
                case 2:
                    // 更新计时器的数字显示
                    if (registerFirstActity.count <= 60 && registerFirstActity.count > 0) {
                        registerFirstActity.btnGetCode.setEnabled(false);
                        registerFirstActity.btnGetCode.setPadding(10, 0, 10, 0);
                        String text = registerFirstActity.count + registerFirstActity.getString(R.string.verification_code_after);
                        registerFirstActity.btnGetCode.setText(text);
                    } else if (registerFirstActity.count == 0) {
                        registerFirstActity.btnGetCode.setEnabled(true);
                        registerFirstActity.btnGetCode.setPadding(10, 0, 10, 0);
                        registerFirstActity.btnGetCode.setText(registerFirstActity.getString(R.string.get_verification_code));

                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void test() {
        etPhone.setText("18144261393");
    }
}
