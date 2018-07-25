package com.tutu.trendsettercloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 用户bean 暂时只用到phone,ethCurrency,integral
 */
public class UserInfoBean implements Serializable {
    private static final long serialVersionUID = -4571235760482781283L;
    private String id;
    private String phone;
    private String ethId;
    private String ethCurrency;
    private String nickName;
    private String password;
    private String transactionPwd;
    private String referrerPhone;
    private float integral;
    private String createDateTime;
    private String lastUpdateDateTime;
    private Role role;
    private String verificationCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEthId() {
        return ethId;
    }

    public void setEthId(String ethId) {
        this.ethId = ethId;
    }

    public String getEthCurrency() {
        return ethCurrency;
    }

    public void setEthCurrency(String ethCurrency) {
        this.ethCurrency = ethCurrency;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTransactionPwd() {
        return transactionPwd;
    }

    public void setTransactionPwd(String transactionPwd) {
        this.transactionPwd = transactionPwd;
    }

    public String getReferrerPhone() {
        return referrerPhone;
    }

    public void setReferrerPhone(String referrerPhone) {
        this.referrerPhone = referrerPhone;
    }

    public float getIntegral() {
        return integral;
    }

    public void setIntegral(float integral) {
        this.integral = integral;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(String lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    private class Role {
        private String id;
        private String name;
        private String description;
        private boolean available;
        private List<Permissones> permissiones;

        private class Permissones {
            private String id;
            private String name;
            private String resourceType;
            private String url;
            private String permission;
            private String parentId;
            private String parentIds;
            private boolean available;
            private String subSysPermissiones;
        }
    }
}

/*
{
	"code": "SUCCESS",
	"msg": "操作成功",
	"data": {
		"id": "14",
		"phone": "13000000000",
		"ethId": "12345",
		"ethCurrency": "15.332564",
		"nickName": "13000000000",
		"password": null,
		"transactionPwd": null,
		"referrerPhone": "",
		"integral": 500.0,
		"createDateTime": "2018-06-21 15:36:12",
		"lastUpdateDateTime": "2018-06-21 15:36:12",
		"role": {
			"id": "1",
			"name": "运营",
			"description": "运营",
			"available": false,
			"permissiones": [{
				"id": "2",
				"name": "ajax注册",
				"resourceType": "menu",
				"url": "/sysuser/ajaxRegs",
				"permission": "anon",
				"parentId": "1",
				"parentIds": "0|1|2",
				"available": false,
				"subSysPermissiones": null
			}]
		},
		"verificationCode": null
	}
}
 */