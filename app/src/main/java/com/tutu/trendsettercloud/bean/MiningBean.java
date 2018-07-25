package com.tutu.trendsettercloud.bean;

import java.io.Serializable;

/**
 * 挖矿记录bean 暂时只用到currencyValue和miningDatetime
 */
public class MiningBean implements Serializable {
    private static final long serialVersionUID = -3575455212702334159L;
    private String createDatetime;//创建时间
    private int currencyValue;//币数量
    private String ethId;
    private String id;
    private String lastUpdateDatetime; //最后更新时间
    private String mineMachineId; //矿机Id
    private String miningDatetime; //币获取时间

    public MiningBean(int currencyValue, String miningDatetime) {
        this.currencyValue = currencyValue;
        this.miningDatetime = miningDatetime;
    }

    public int getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(int currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getMiningDatetime() {
        return miningDatetime;
    }

    public void setMiningDatetime(String miningDatetime) {
        this.miningDatetime = miningDatetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


/*
 返回数据参考
{"code":"SUCCESS","msg":"操作成功",
        "data":[{
        createDatetime:"2018-06-19 17:36:19",
        currencyValue:2,
        ethId:"12345",   /
        id:"2",
        lastUpdateDatetime:"2018-06-19 17:36:19",
        mineMachineId:"12345",
        miningDatetime:"2018-06-19 17:29:00"
        }]
 */