package com.grandland.qdhx.web.service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.web.controller.param.BusinessMortgageParam;
import com.grandland.qdhx.web.service.base.BaseService;

public interface BusinessMortgageService extends BaseService<BusinessMortgage>  {

	BusinessMortgage selectByPrimaryKey(Integer id);
	
	ResponsePageDate<BusinessMortgage> selectByexamplewithPage(BusinessMortgageParam businessMortgageParam);
	
	void CreateBusinessData(BusinessMortgage businessMortgage);

}
