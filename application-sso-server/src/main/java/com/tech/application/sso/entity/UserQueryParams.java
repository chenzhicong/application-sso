package com.tech.application.sso.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQueryParams implements Serializable {
	
	private String appCode;
	
	private String account;
	
	private String phone;
	
	private String name;
	
	private String nickName;
	
	private Integer sex;
	
	private Integer status;
	
}
