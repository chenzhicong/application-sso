package com.application.sso.server.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermission implements Serializable {
    private String id;

    private String roleId;

    private String permissionId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

}