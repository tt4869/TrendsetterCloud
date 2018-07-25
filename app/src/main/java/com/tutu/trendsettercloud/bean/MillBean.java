package com.tutu.trendsettercloud.bean;

import java.io.Serializable;

public class MillBean implements Serializable{
    private static final long serialVersionUID = 893872017218970664L;
    private String IP;
    private String ID;

    public MillBean(String IP, String ID) {
        this.IP = IP;
        this.ID = ID;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
