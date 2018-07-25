package com.tutu.trendsettercloud.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.tutu.trendsettercloud.bean.UserInfoBean;

public class MyConfig {

    /**
     * 清除配置文件
     *
     * @param context
     * @param name
     */
    public static void clear(Context context, String name) {
        SharedPreferences sharedata = context.getSharedPreferences(name, 0);
        SharedPreferences.Editor edit = sharedata.edit();
        edit.clear();
        edit.commit();
    }



    /**
     * 保存用户信息
     */
    public static void saveUserInfo(Context context, UserInfoBean model) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CONFIG_USERINFO, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", model.getId());
        editor.putString("phone", model.getPhone());
        editor.putString("ethId", model.getEthId());
        editor.putString("ethCurrency", model.getEthCurrency());
        editor.putString("nickName", model.getNickName());
        editor.putString("password", model.getPassword());
        editor.putString("transactionPwd", model.getTransactionPwd());
        editor.putString("referrerPhone", model.getReferrerPhone());
        editor.putFloat("integral", model.getIntegral());
        editor.putString("createDateTime", model.getCreateDateTime());
        editor.putString("lastUpdateDateTime", model.getLastUpdateDateTime());
        editor.putString("verificationCode", model.getVerificationCode());
        editor.apply();
    }

    /**
     * 用户信息
     *
     * @param context
     * @return
     */
    public static UserInfoBean getUserInfo(Context context) {
        UserInfoBean model = new UserInfoBean();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.CONFIG_USERINFO, 0);
        model.setId(sharedPreferences.getString("id", ""));
        model.setPhone(sharedPreferences.getString("phone", ""));
        model.setEthId(sharedPreferences.getString("ethId", ""));
        model.setEthCurrency(sharedPreferences.getString("ethCurrency", ""));
        model.setNickName(sharedPreferences.getString("nickName", ""));
        model.setPassword(sharedPreferences.getString("password", ""));
        model.setTransactionPwd(sharedPreferences.getString("transactionPwd", ""));
        model.setReferrerPhone(sharedPreferences.getString("referrerPhone", ""));
        model.setIntegral(sharedPreferences.getFloat("integral", 0));
        model.setCreateDateTime(sharedPreferences.getString("createDateTime", ""));
        model.setLastUpdateDateTime(sharedPreferences.getString("lastUpdateDateTime", ""));
        model.setVerificationCode(sharedPreferences.getString("verificationCode", ""));
        return model;
    }

}
