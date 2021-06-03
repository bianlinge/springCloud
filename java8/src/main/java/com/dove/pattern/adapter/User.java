package com.dove.pattern.adapter;

public class User {
    private String userId;
    private String password;

    private String openId;
    private String phone;
    private String vifcode;

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getVifcode() {
        return this.vifcode;
    }

    public void setVifcode(final String vifcode) {
        this.vifcode = vifcode;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(final String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
