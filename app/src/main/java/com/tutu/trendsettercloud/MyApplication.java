package com.tutu.trendsettercloud;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.tutu.trendsettercloud.config.Constants;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

//        //初始化内存泄漏检测工具
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        //初始化网络请求框架
        initOkGo();

    }

    private void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);

        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间 15s
        builder.readTimeout(Constants.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间 15s
        builder.writeTimeout(Constants.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间 15s
        builder.connectTimeout(Constants.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);

        OkGo.getInstance().init(this).setOkHttpClient(builder.build());
    }

    public static Context getContext(){
        return context;
    }
}
