package com.tutu.trendsettercloud.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.tutu.trendsettercloud.R;
import com.tutu.trendsettercloud.utils.AppManager;

import butterknife.ButterKnife;
import crossoverone.statuslib.StatusUtil;


public abstract class BaseActivity extends AppCompatActivity {
    private Toast mToast;
    protected Activity mContext;
    protected View ivBack;
    protected TextView tvTitle;
    protected TextView tvRight;

    //获取布局文件
    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initValues();
    }

    //初始化界面
    protected  void initUI(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //基类设置默认值,这里是非沉浸,状态栏颜色值#878787,字体颜色为黑色
        setStatusColor();
        setSystemInvadeBlack();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        ivBack = findViewById(R.id.iv_back);
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        View titleView = findViewById(R.id.tv_title);
        if (titleView != null && titleView instanceof TextView) {
            tvTitle = (TextView) titleView;
        }

        View rightView = findViewById(R.id.tv_right);
        if( rightView != null && rightView instanceof TextView ) {
            tvRight = (TextView) rightView;
        }
    }

    //初始化业务逻辑
    protected void initValues(){
        AppManager.getAppManager().addActivity(this);
        mContext = this;
    }


    protected void setStatusColor() {
        //StatusUtil.setUseStatusBarColor(this, Color.parseColor("#878787"));
        StatusUtil.setUseStatusBarColor(this, getResources().getColor(R.color.blue));
    }

    protected void setSystemInvadeBlack() {
        // 第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色
        StatusUtil.setSystemStatus(this, false, true);
    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().finishActivity(this);
    }


    public void showToast(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(mContext, txt, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    protected void showToast(int resId) {
        showToast(getString(resId));
    }


}
