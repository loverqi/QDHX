package com.grandland.qdhx.web.service;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessInspectionDetail;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.web.service.base.BaseService;

import java.util.Map;

/**
 * 巡核库详情
 */
public interface BusinessInspectionDetailService extends BaseService<BusinessInspectionDetail> {
	
	PageResult<Map<String,String>> queryDetail(BsInspectionQueryParam param);
	
}