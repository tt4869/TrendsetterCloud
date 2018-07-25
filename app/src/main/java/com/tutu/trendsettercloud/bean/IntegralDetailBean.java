package com.tutu.trendsettercloud.bean;

import java.io.Serializable;

public class IntegralDetailBean implements Serializable {
    private static final long serialVersionUID = 6565251095436565248L;
    private String id;
    private String userId;
    private int integral;
    private String remark;
    private String createDatetime;
    private String lastUpdateDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastUpdateDatetime() {
        return lastUpdateDatetime;
    }

    public void setLastUpdateDatetime(String lastUpdateDatetime) {
        this.lastUpdateDatetime = lastUpdateDatetime;
    }

 /*
    	"id": "1",
		"userId": "14",
		"integral": 1,
		"remark": "推荐奖励",
		"createDatetime": "2018-07-03 16:34:27",
		"lastUpdateDatetime": "2018-07-03 16:34:32"
     */
}
