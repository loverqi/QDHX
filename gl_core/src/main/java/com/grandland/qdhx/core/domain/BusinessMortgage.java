package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.domain.ValidateEntity.Add;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author
 */
@ApiModel(value = "贷款质物业务类")
public class BusinessMortgage extends MyBatisPojo {

	/**
	 * 主键
	 */
	@ApiModelProperty(hidden = true)
	private Integer id;

	/**
	 * 状态
	 */
	@ApiModelProperty(hidden = true)
	private Integer state;

	/**
	 * 融资合同编号
	 */
	@ApiModelProperty(value = "融资合同编号", required = true)
	@NotBlank(message = "businessConum不允许为空", groups = { Add.class })
	private String businessConum;

    @ApiModelProperty(value = "融资编号", required = true)
    @NotBlank(message = "financingId不允许为空", groups = { Add.class })
    private String financingId;

	/**
	 * 融资金额
	 */
	@ApiModelProperty(value = "融资金额", required = true)
	@NotNull(message = "morValue不允许为空", groups = { Add.class })
	private BigDecimal morValue;

	/**
	 * 放款时间
	 */
	@ApiModelProperty(value = "放款时间", required = true)
	@NotNull(message = "morTime不允许为空", groups = { Add.class })
	private java.sql.Date morTime;

	/**
	 * 贷款种类
	 */
	@ApiModelProperty(value = "贷款种类", required = true)
	@NotBlank(message = "morClassify不允许为空", groups = { Add.class })
	private String morClassify;

	/**
	 * 借款人名称
	 */
	@ApiModelProperty(value = "借款人名称", required = true)
	@NotBlank(message = "borrowerName不允许为空", groups = { Add.class })
	private String borrowerName;

	/**
	 * 监管机构
	 */
	@ApiModelProperty(value = "监管机构", required = true)
	@NotBlank(message = "superDep不允许为空", groups = { Add.class })
	private String superDep;

	/**
	 * 合作协议号
	 */
	@ApiModelProperty(value = "合作协议号", required = true)
	@NotBlank(message = "cooNum不允许为空", groups = { Add.class })
	private String cooNum;

	/**
	 * 质物名称
	 */
	@ApiModelProperty(value = "质物名称", required = true)
	@NotBlank(message = "mortgageName不允许为空", groups = { Add.class })
	private String mortgageName;

	/**
	 * 地区
	 */
	@ApiModelProperty(hidden = true)
	private String area;

	/**
	 * 省份
	 */

	@ApiModelProperty(hidden = true)
	private String province;

	/**
	 * 市场
	 */
	@ApiModelProperty(value = "市场", required = false)
	private String marketCity;

	/**
	 * 材质
	 */
	@ApiModelProperty(value = "材质", required = false)
	private String material;

	/**
	 * 生产厂家
	 */
	@ApiModelProperty(value = "生产厂家", required = false)
	private String producer;

	/**
	 * 规格型号
	 */
	@ApiModelProperty(value = "规格型号", required = false)
	private String specType;

	/**
	 * 单位价格
	 */
	@ApiModelProperty(value = "单位价格", required = true)
	@NotNull(message = "unitPrice不允许为空", groups = { Add.class })
	private BigDecimal unitPrice;

	/**
	 * 价格单位
	 */
	@ApiModelProperty(value = "价格单位", required = false)
	private String priceUnit;

	/**
	 * 计量单位
	 */
	@ApiModelProperty(value = "计量单位", required = true)
	@NotBlank(message = "measureUnit不允许为空", groups = { Add.class })
	private String measureUnit;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量", required = true)
	@NotNull(message = "count不允许为空", groups = { Add.class })
	private BigDecimal count;

	/**
	 * 质物价值
	 */
	@ApiModelProperty(value = "质物价值", required = true)
	@NotNull(message = "totalValue不允许为空", groups = { Add.class })
	private BigDecimal totalValue;
	/**
	 * 质押率
	 */
	@ApiModelProperty(value = "质押率", required = true)
	@NotBlank(message = "mortageRate不允许为空", groups = { Add.class })
	private String mortageRate;

	/**
	 * 借款人用信风险敞口
	 */
	@ApiModelProperty(value = "借款人用信风险敞口", required = true)
	@NotNull(message = "riskExposure不允许为空", groups = { Add.class })
	private BigDecimal riskExposure;

	/**
	 * 客户经理人员
	 */
	@ApiModelProperty(value = "客户经理人员", required = true)
	@NotBlank(message = "customerManager不允许为空", groups = { Add.class })
	private String customerManager;

	/**
	 * 复核岗
	 */
	@ApiModelProperty(value = "复核岗", required = true)
	@NotBlank(message = "checker不允许为空", groups = { Add.class })
	private String checker;

	/**
	 * 复核理由
	 */
	@ApiModelProperty(hidden = true)
	private String checkReason;

	/**
	 * 审批人
	 */
	@ApiModelProperty(hidden = true)
	private String approver;

	/**
	 * 审批理由
	 */
	@ApiModelProperty(hidden = true)
	private String approveReason;

	/**
	 * 现场检查岗人员
	 */
	@ApiModelProperty(value = "现场检查岗人员", required = true)
	@NotBlank(message = "wareSuper不允许为空", groups = { Add.class })
	private String wareSuper;

	/**
	 * 业务到期时间
	 */
	@ApiModelProperty(value = "业务到期时间", required = true)
	@NotNull(message = "deadLine不允许为空", groups = { Add.class })
	private java.sql.Date deadLine;

	/**
	 * 是否删除 1为删除
	 */
	@ApiModelProperty(hidden = true)
	private Integer isDeleted;

	/**
	 * 创建人
	 */
	@ApiModelProperty(hidden = true)
	private String createBy;

	/**
	 * 更新人
	 */
	@ApiModelProperty(hidden = true)
	private String updateBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(hidden = true)
	private Date updateTime;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessConum() {
		return businessConum;
	}

	public void setBusinessConum(String businessConum) {
		this.businessConum = businessConum;
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

	public String getMortgageName() {
		return mortgageName;
	}

	public void setMortgageName(String mortgageName) {
		this.mortgageName = mortgageName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMarketCity() {
		return marketCity;
	}

	public void setMarketCity(String marketCity) {
		this.marketCity = marketCity;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getSpecType() {
		return specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getMortageRate() {
		return mortageRate;
	}

	public void setMortageRate(String mortageRate) {
		this.mortageRate = mortageRate;
	}

	public BigDecimal getRiskExposure() {
		return riskExposure;
	}

	public void setRiskExposure(BigDecimal riskExposure) {
		this.riskExposure = riskExposure;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproveReason() {
		return approveReason;
	}

	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	public String getWareSuper() {
		return wareSuper;
	}

	public void setWareSuper(String wareSuper) {
		this.wareSuper = wareSuper;
	}

	public java.sql.Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(java.sql.Date deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public BigDecimal getMorValue() {
		return morValue;
	}

	public void setMorValue(BigDecimal morValue) {
		this.morValue = morValue;
	}

	public java.sql.Date getMorTime() {
		return morTime;
	}

	public void setMorTime(java.sql.Date morTime) {
		this.morTime = morTime;
	}

	public String getMorClassify() {
		return morClassify;
	}

	public void setMorClassify(String morClassify) {
		this.morClassify = morClassify;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

    public String getFinancingId() {
        return financingId;
    }

    public void setFinancingId(String financingId) {
        this.financingId = financingId;
    }

    @Override
    public String toString() {
        return "BusinessMortgage{" +
                "id=" + id +
                ", state=" + state +
                ", businessConum='" + businessConum + '\'' +
                ", financingId='" + financingId + '\'' +
                ", morValue=" + morValue +
                ", morTime=" + morTime +
                ", morClassify='" + morClassify + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", superDep='" + superDep + '\'' +
                ", cooNum='" + cooNum + '\'' +
                ", mortgageName='" + mortgageName + '\'' +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", marketCity='" + marketCity + '\'' +
                ", material='" + material + '\'' +
                ", producer='" + producer + '\'' +
                ", specType='" + specType + '\'' +
                ", unitPrice=" + unitPrice +
                ", priceUnit='" + priceUnit + '\'' +
                ", measureUnit='" + measureUnit + '\'' +
                ", count=" + count +
                ", totalValue=" + totalValue +
                ", mortageRate='" + mortageRate + '\'' +
                ", riskExposure=" + riskExposure +
                ", customerManager='" + customerManager + '\'' +
                ", checker='" + checker + '\'' +
                ", checkReason='" + checkReason + '\'' +
                ", approver='" + approver + '\'' +
                ", approveReason='" + approveReason + '\'' +
                ", wareSuper='" + wareSuper + '\'' +
                ", deadLine=" + deadLine +
                ", isDeleted=" + isDeleted +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}