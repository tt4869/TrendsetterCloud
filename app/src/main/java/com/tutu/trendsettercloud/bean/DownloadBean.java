package com.tutu.trendsettercloud.bean;

import java.io.Serializable;

public class DownloadBean implements Serializable {
    private static final long serialVersionUID = -140476197429492026L;
    private String fileName;
    private String date;

    public DownloadBean(String fileName, String date) {
        this.fileName = fileName;
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
