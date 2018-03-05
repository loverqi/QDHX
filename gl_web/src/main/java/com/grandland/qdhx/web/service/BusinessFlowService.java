package com.grandland.qdhx.web.service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.BusinessFlow;
import com.grandland.qdhx.web.controller.param.BusinessFlowParam;
import com.grandland.qdhx.web.service.base.BaseService;

public interface BusinessFlowService extends BaseService<BusinessFlow>{
	
	ResponsePageDate<BusinessFlow> queryByexamplewithPage(BusinessFlowParam  businessFlowParam);

}
