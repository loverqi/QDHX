package com.grandland.qdhx.core.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

import com.grandland.qdhx.core.domain.ValidateEntity.Get;

@ApiModel(value = "低价补偿查询参数类")
public class BsFallQueryParam {
	@ApiModelProperty(value = "第几页",required=true)
	@NotNull(message="pageNum不允许为空" , groups = {Get.class})
	Integer pageNum;
	
	@ApiModelProperty(value = "每页大小",required=true)
	@NotNull(message="pageSize不允许为空" , groups = {Get.class})
	Integer pageSize;
	
	@ApiModelProperty(value = "开始时间",required=false)
	private  java.sql.Date begainTime;

	@ApiModelProperty(value = "结束时间",required=false)
	private java.sql.Date endTime;
	
	
	public java.sql.Date getBegainTime() {
		return begainTime;
	}

	public void setBegainTime(java.sql.Date begainTime) {
		this.begainTime = begainTime;
	}

	public java.sql.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.sql.Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
