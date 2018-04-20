package com.application.sso.server.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceQueryParams implements Serializable {
	
	private String appCode;
	
	private String roleId;
	
	private String name;
	
	private Integer type;
	
	private Integer status; 
}
