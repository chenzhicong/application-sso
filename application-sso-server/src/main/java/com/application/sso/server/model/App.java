package com.application.sso.server.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class App implements Serializable{
	
	@ApiModelProperty("应用id")
    private String id;
	
	@ApiModelProperty("应用编码")
    private String code;
	
	@ApiModelProperty("应用名称")
    private String name;
	
	@ApiModelProperty("应用描述")
    private String description;

	@ApiModelProperty("状态 0:禁用 1:正常")
    private Integer status;
	
	@ApiModelProperty("排序")
    private Integer sort;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间 yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间 yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("是否已删除 0:未删除 1:已删除")
    private Integer deleted;
}