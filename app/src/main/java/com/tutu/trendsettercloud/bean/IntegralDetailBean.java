package com.tutu.trendsettercloud.bean;

import java.io.Serializable;

public class IntegralDetailBean implements Serializable {
    private static final long serialVersionUID = 6565251095436565248L;
    private String kind;
    private int amount;
    private String date;

    public IntegralDetailBean(String kind, int amount, String date) {
        this.kind = kind;
        this.amount = amount;
        this.date = date;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
