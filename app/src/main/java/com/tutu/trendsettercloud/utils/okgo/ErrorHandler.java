package com.tutu.trendsettercloud.utils.okgo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonSyntaxException;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Response;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;


/**
 * Created by jzht on 2018/1/9.
 */

public class ErrorHandler {

    private Response mResponse;
    private Context mContext;
    private volatile static ErrorHandler instance;


    private ErrorHandler(Response response , Context context){
        mResponse = response;
        mContext = context;
    }

    public static ErrorHandler getInstance(Context context,Response response){
        if(instance == null){
            synchronized(ErrorHandler.class){
                return new ErrorHandler(response,context);
            }
        }

        instance.mResponse = response;
        instance.mContext = context ;
        return instance;
    }




    public void handle(){
        Throwable exception = mResponse.getException();
        if(exception!=null) exception.printStackTrace();
        if(exception instanceof UnknownHostException || exception instanceof ConnectException){
            showToast("网络连接失败，请连接网络");
        }else if(exception instanceof SocketTimeoutException){
            showToast("网络请求超时");
        }else if(exception instanceof HttpException){
            showToast("服务器异常");
        }else if(exception instanceof StorageException){
            showToast("sd不存在或者没有权限");
        }else if(exception instanceof IllegalAccessException){

        }else if(exception instanceof IllegalStateException){
            //自定义异常
            String message = exception.getMessage();
            showToast(message);
        }else if(exception instanceof JsonSyntaxException){
            showToast("数据为空");
        }

    }

    public void showToast(final String txt) {
      Toast.makeText(mContext,txt,Toast.LENGTH_SHORT).show();
    }

}
