package com.tutu.trendsettercloud.utils.okgo;

import java.io.Serializable;

/**
 * Created by Allen on 2016/12/13.
 */

public class BaseResponse<T> implements Serializable {

private static final long serialVersionUID = 2028989298961031336L;
    /**
     * SUCCESS: 操作成功
     * SYSTEM_EXCEPTION:系统异常
     * NO_LOGIN:未登录
     * NO_AUTHEN:没有权限
     */
    public String code;

    //结果说明
    public String msg;

    //所需数据或者异常信息
    public T data;
}
