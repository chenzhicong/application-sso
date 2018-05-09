package com.tech.application.sso.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource implements Serializable {
    private String id;

    private String appId;

    private String parentId;

    private String name;

    private String description;

    private Integer resourceType;

    private String url;
    
    private Integer status;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

}