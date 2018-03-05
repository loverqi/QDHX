package com.grandland.qdhx.web.service;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessInspection;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.web.service.base.BaseService;

import java.util.Map;

/**
 * 巡核库列表
 */
public interface BusinessInspectionService extends BaseService<BusinessInspection> {
	
	PageResult<Map<String,String>> query(BsInspectionQueryParam param);
	
}