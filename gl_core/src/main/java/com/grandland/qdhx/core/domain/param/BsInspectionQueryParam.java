package com.grandland.qdhx.core.domain.param;

import com.grandland.qdhx.core.domain.ValidateEntity.Get;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@ApiModel(value = "巡核库查询参数类")
public class BsInspectionQueryParam {
	@ApiModelProperty(value = "第几页",required=true)
	@NotNull(message="pageNum不允许为空" , groups = {Get.class})
	Integer pageNum;
	
	@ApiModelProperty(value = "每页大小",required=true)
	@NotNull(message="pageSize不允许为空" , groups = {Get.class})
	Integer pageSize;
	
	@ApiModelProperty(value = "开始时间",required=false)
	private  java.sql.Date beginTime;

	@ApiModelProperty(value = "结束时间",required=false)
	private java.sql.Date endTime;

	@ApiModelProperty(value = "借款人名称",required=false)
	private String borrowerName;

	@ApiModelProperty(value = "监管单位",required=false)
	private String superDep;

	@ApiModelProperty(value = "合作协议号",required=false)
	private String cooNum;

	@ApiModelProperty(value = "借款人融资风险敞口",required=false)
	private String riskExposure;

	@ApiModelProperty(value = "业务编号",required=false)
	private String businessId;


	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
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

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public String getSuperDep() {
		return superDep;
	}

	public void setSuperDep(String superDep) {
		this.superDep = superDep;
	}

	public String getCooNum() {
		return cooNum;
	}

	public void setCooNum(String cooNum) {
		this.cooNum = cooNum;
	}

	public String getRiskExposure() {
		return riskExposure;
	}

	public void setRiskExposure(String riskExposure) {
		this.riskExposure = riskExposure;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}


	@Override
	public String toString() {
		return "BsInspectionQueryParam{" +
				"pageNum=" + pageNum +
				", pageSize=" + pageSize +
				", beginTime=" + beginTime +
				", endTime=" + endTime +
				", borrowerName='" + borrowerName + '\'' +
				", superDep='" + superDep + '\'' +
				", cooNum='" + cooNum + '\'' +
				", riskExposure='" + riskExposure + '\'' +
				", businessId='" + businessId + '\'' +
				'}';
	}
}
