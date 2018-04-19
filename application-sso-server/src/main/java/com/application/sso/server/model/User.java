package com.application.sso.server.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
    private String id;
    
    private String roleId;

    private String account;

    private String phone;

    private String password;

    private String name;

    private String nickName;

    private Integer sex;

    private String headImgUrl;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Integer loginCount;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;


}