package com.dove.mongodb.mongoYun.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "t_member")
public class Member implements Serializable {
    @Id
    private long id;
    private String loginName;
    private String passWord;
    private String nickName;
    private long createTime;
    private int status;
    private String lashLoginIp;
    private long lastLoginTime;

    public Member(final long id, final String loginName, final String passWord, final String nickName) {
        this.id = id;
        this.loginName = loginName;
        this.passWord = passWord;
        this.nickName = nickName;
    }

    public Member() {
    }
}
