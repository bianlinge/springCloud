package com.dove.activemq.provider;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 2504467948968634865L;
    private String userName;
    private String password;

    public User() {
    }

    public User(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static long getSerialVersionUID() {
        return User.serialVersionUID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
