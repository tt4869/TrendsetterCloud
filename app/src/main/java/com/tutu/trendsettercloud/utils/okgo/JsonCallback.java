package com.tutu.trendsettercloud.utils.okgo;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.tutu.trendsettercloud.api.Parameter;
import com.tutu.trendsettercloud.utils.StringUtil;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Response;

/**
 * Created by jzht on 2018/1/5.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        //"str":"card_id=3526489650&key=yudianedutest","sign":"332684f0d4275f1a797e5734a37e94a0"
        HttpParams params = request.getParams();
//        params.put(Parameter.KEY, Key.key);
//        String param = StringUtil.filterParams(toStringWithoutFile(params));
//        String sign = MD5Util.MD5_32bit(param);//验证的时候去掉PIC
//        params.remove(Parameter.KEY);
//        params.put(Parameter.SIGN, sign);
    }



    @Override
    public T convertResponse(Response response) throws Throwable {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
       // JsonReader jsonReader = new JsonReader(response.body().charStream());
        boolean isSimpleResponse = type.toString().equals(SimpleResponse.class.toString());
        if (!(type instanceof ParameterizedType) && !isSimpleResponse)
            throw new IllegalStateException("没有填写泛型参数");


        String responseStr = response.body().string();
        JSONObject jsonObject = new JSONObject(responseStr);
        String code = jsonObject.getString("code");
        String msg = jsonObject.getString("msg");
        response.close();

        if(!isSimpleResponse) {
            Type rawType = ((ParameterizedType) type).getRawType();
            Type typeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
            //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
            if (typeArgument == Void.class && code.equals("SUCCESS")) {
                //无数据类型,表示没有data数据的情况（以  new DialogCallback<LzyResponse<Void>>(this)  以这种形式传递的泛型)
                SimpleResponse simpleResponse = Convert.fromJson(responseStr, SimpleResponse.class);
                //noinspection unchecked
                return (T) simpleResponse.toBaseResponse();
            } else if (rawType != BaseResponse.class){
                throw new IllegalStateException("基类错误无法解析!");
            }
        }
        if(code.equals("SUCCESS")){
            return (T) Convert.fromJson(responseStr, type);
        }else if(!StringUtil.isEmity(msg)){
            throw new IllegalStateException(msg);
        }else if (code.equals("SYSTEM_EXCEPTION")){
            throw new IllegalStateException("系统异常");
        }else if(code.equals("NO_LOGIN")){
            throw new IllegalStateException("未登录");
        }else if(code.equals("NO_AUTHEN")){
            throw new IllegalStateException("没有权限");
        }else{
            throw new IllegalStateException("未定义的错误");
        }
    }
}
