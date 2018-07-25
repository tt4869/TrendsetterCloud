package com.tutu.trendsettercloud.utils;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jzht on 2018/1/5.
 */

public class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true: 空 false: 不为空
     */
    public static boolean isEmity(String str) {
        boolean isEmity = true;
        if (str != null && !"".equals(str) && !"null".equals(str)) {
            isEmity = false;
        }
        return isEmity;
    }

    /**
     * 判断列表是否为空
     *
     * @param list 列表
     * @return true: 空 false: 不为空
     */
    public static boolean isEmity(List list){
        boolean isEmity = true;
        if(list != null && list.size()>0){
            isEmity = false;
        }
        return isEmity;
    }

    /**
     * 验证手机号码格式是否正确
     *
     * @param mobiles 手机号
     * @return 是否为手机号
     */
    public static boolean isMobileNO(String mobiles) {
//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Pattern p = Pattern.compile("\\b(1)[3-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String filterParams(String params){

        String str = params.replace("[","");
        return str.replace("]","");
    }

    /**
     * 隐藏电话号中间四位，如果长度不为11，返回原数据
     *
     * @param phone 电话号码
     * @return
     */
    public static String hidePhone(String phone) {
        if (phone == null) {
            return "";
        }
        if (phone.length() != 11) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
    }


    //把多个String拼接成一个由逗号分隔各个String的字符串
    public static String mergeByComma(Collection<String> stringList){
        if(stringList != null) {
            StringBuilder sb = new StringBuilder();
            for (String p : stringList) {
                if(p != null && p.length() > 0) {
                    sb.append(p);
                    sb.append(",");
                }
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }else {
                return "";
            }
        }
        return null;
    }
}
