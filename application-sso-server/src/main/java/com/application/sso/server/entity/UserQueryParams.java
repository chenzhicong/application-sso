package com.application.sso.server.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryParams implements Serializable {
	
	private String appCode;
	
	private String roleId;
	
	private String account;
	
	private String phone;
	
	private String name;
	
	private Integer status;
	
}