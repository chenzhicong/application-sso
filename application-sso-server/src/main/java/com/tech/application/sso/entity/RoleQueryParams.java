package com.tech.application.sso.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleQueryParams implements Serializable {
	
	private String appCode;
	
	private String appId;
	
	private String name;
	
	private Integer status;	
	
}
