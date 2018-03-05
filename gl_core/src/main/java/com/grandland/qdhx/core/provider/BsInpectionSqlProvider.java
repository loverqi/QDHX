package com.grandland.qdhx.core.provider;

import com.grandland.qdhx.core.domain.BusinessInspection;
import com.grandland.qdhx.core.domain.BusinessInspectionDetail;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.core.utils.DateUtil;
import com.grandland.qdhx.core.utils.TableName;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class BsInpectionSqlProvider {

	/**
	 * 巡核库列表信息
	 * @param param
	 * @return
     */
	 public String query(final BsInspectionQueryParam param) {
		 return new SQL() {
			 {
				 SELECT("*");
				 FROM(TableName.BS_INSPECTION + " i");
				 LEFT_OUTER_JOIN(TableName.BS_MORTGATE + " m on m.id=i.business_id");
				 //借款人名称
				 if (!StringUtils.isEmpty(param.getBorrowerName())) {
					 WHERE("m.borrower_name like '%" + param.getBorrowerName() + "%'");
				 }
				 //监管单位
				 if (!StringUtils.isEmpty(param.getSuperDep())) {
					 WHERE("m.super_dep=#{param.superDep}");
				 }
				 //合作协议号
				 if (!StringUtils.isEmpty(param.getCooNum())) {
					 WHERE("m.coo_num like '%" + param.getCooNum() + "%' ");
				 }
				 //借款人融资风险敞口
				 if (!StringUtils.isEmpty(param.getRiskExposure())) {
					 WHERE("m.risk_exposure like '%" + param.getRiskExposure() + "%' ");
				 }
				 if (!ObjectUtils.isEmpty(param.getBeginTime())) {
					 WHERE("DATE_FORMAT(i.create_time,'%Y-%m-%d') >= " + DateUtil.toSQLDateDay(param.getBeginTime().toString()));
				 }
				 if (!ObjectUtils.isEmpty(param.getEndTime())) {
					 WHERE("DATE_FORMAT(i.create_time,'%Y-%m-%d') <= " + DateUtil.toSQLDateDay(param.getEndTime().toString()));
				 }
				 WHERE("i.is_deleted='0'");
			 }
		 }.toString();
	 }

    public String insert(final BusinessInspection inspection) {
        return new SQL() {
            {
                INSERT_INTO(TableName.BS_INSPECTION);
                VALUES("business_id", "#{inspection.businessId}");
                if (!ObjectUtils.isEmpty(inspection.getDeleted())) {
                    VALUES("is_deleted", "#{inspection.isDeleted}");
                }
                if (!ObjectUtils.isEmpty(inspection.getCreateTime())) {
                    VALUES("create_time", "#{inspection.createTime}");
                }
                if (!ObjectUtils.isEmpty(inspection.getUpdateTime())) {
                    VALUES("update_time", "#{inspection.updateTime}");
                }
            }
        }.toString();
    }


    public String update(final BusinessInspection inspection) {
        return new SQL() {
            {
                UPDATE(TableName.BS_INSPECTION);
                if (!StringUtils.isEmpty(inspection.getBusinessId())) {
                    SET("business_id=#{inspection.businessId}");
                }
                if (!ObjectUtils.isEmpty(inspection.getDeleted())) {
                    SET("is_deleted=#{inspection.isDeleted}");
                }
                if (!ObjectUtils.isEmpty(inspection.getCreateTime())) {
                    SET("create_time=#{inspection.createTime}");
                }
                if (!ObjectUtils.isEmpty(inspection.getUpdateTime())) {
                    SET("update_time=#{inspection.updateTime}");
                }
                WHERE("id=#{inspection.id}");
            }
        }.toString();
    }


    //==================================================================

	/**
	 * 巡核库巡核详情列表
	 * @param param
	 * @return
     */
	public String queryDetail(final BsInspectionQueryParam param) {
		return new SQL() {
			{
				SELECT("*");
				FROM(TableName.BS_INSPECTION_DETAIL + " i");
				if (!StringUtils.isEmpty(param.getBusinessId())) {
					WHERE("i.business_id=#{param.businessId}");
				}
			}
		}.toString();
	}


	public String insertDetail(final BusinessInspectionDetail inspectionDetail) {
		return new SQL() {
            {
                INSERT_INTO(TableName.BS_INSPECTION_DETAIL);
                if (!ObjectUtils.isEmpty(inspectionDetail.getBusinessId())) {
                    VALUES("business_id", "#{inspectionDetail.businessId}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getUserId())) {
                    VALUES("user_id", "#{inspectionDetail.userId}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.isChecked())) {
                    VALUES("is_checked", "#{inspectionDetail.isChecked}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getCheckTime())) {
                    VALUES("check_time", "#{inspectionDetail.checkTime}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getRealCheckTime())) {
                    VALUES("real_check_time", "#{inspectionDetail.realCheckTime}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getImgs())) {
                    VALUES("imgs", "#{inspectionDetail.imgs}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getCorporate())) {
                    VALUES("corporate", "#{inspectionDetail.corporate}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getAddress())) {
                    VALUES("address", "#{inspectionDetail.address}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getLocation())) {
                    VALUES("location", "#{inspectionDetail.location}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getExtra())) {
                    VALUES("extra", "#{inspectionDetail.extra}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.isAccurate())) {
                    VALUES("is_accurate", "#{inspectionDetail.isAccurate}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getCreateTime())) {
                    VALUES("create_time", "#{inspectionDetail.createTime}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getUpdateTime())) {
                    VALUES("update_time", "#{inspectionDetail.updateTime}");
                }
            }
        }.toString();
	}


    public String updateDetail(final BusinessInspectionDetail inspectionDetail) {
        return new SQL() {
            {
                UPDATE(TableName.BS_INSPECTION_DETAIL);
                if (!StringUtils.isEmpty(inspectionDetail.getBusinessId())) {
                    SET("business_id=#{inspectionDetail.businessId}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getUserId())) {
                    SET("user_id=#{inspectionDetail.userId}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.isChecked())) {
                    SET("is_checked=#{inspectionDetail.isChecked}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getCheckTime())) {
                    SET("check_time=#{inspectionDetail.checkTime}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getRealCheckTime())) {
                    SET("real_check_time=#{inspectionDetail.realCheckTime}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getImgs())) {
                    SET("imgs=#{inspectionDetail.imgs}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getCorporate())) {
                    SET("corporate=#{inspectionDetail.corporate}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getAddress())) {
                    SET("address=#{inspectionDetail.address}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getLocation())) {
                    SET("location=#{inspectionDetail.location}");
                }
                if (!StringUtils.isEmpty(inspectionDetail.getExtra())) {
                    SET("extra=#{inspectionDetail.extra}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.isAccurate())) {
                    SET("is_accurate=#{inspectionDetail.isAccurate}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getCreateTime())) {
                    SET("create_time=#{inspectionDetail.createTime}");
                }
                if (!ObjectUtils.isEmpty(inspectionDetail.getUpdateTime())) {
                    SET("update_time=#{inspectionDetail.updateTime}");
                }
                WHERE("id=#{inspectionDetail.id}");
            }
        }.toString();
    }
}
