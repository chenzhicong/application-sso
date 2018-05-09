package com.tech.application.sso.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppQueryParams implements Serializable {
	
	private String code;
	
	private String name;
	
	private Integer status;
}
