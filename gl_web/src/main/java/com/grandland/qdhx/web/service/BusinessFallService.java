package com.grandland.qdhx.web.service;

import java.util.Map;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessFall;
import com.grandland.qdhx.core.domain.param.BsFallQueryParam;
import com.grandland.qdhx.web.service.base.BaseService;

public interface BusinessFallService extends BaseService<BusinessFall> {
	
	PageResult<Map<String,String>> query(BsFallQueryParam bsFallQueryParam);
	
}