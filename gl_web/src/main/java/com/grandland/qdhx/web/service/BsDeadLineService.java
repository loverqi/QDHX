package com.grandland.qdhx.web.service;

import java.util.Map;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BudeadLine;
import com.grandland.qdhx.core.domain.param.BuDeadLineQuParam;
import com.grandland.qdhx.web.service.base.BaseService;

public interface BsDeadLineService extends BaseService<BudeadLine>{
	
	PageResult<Map<String,String>> query(BuDeadLineQuParam buDeadLineQuParam);

}
