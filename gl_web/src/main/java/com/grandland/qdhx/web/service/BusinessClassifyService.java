package com.grandland.qdhx.web.service;

import java.util.List;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.domain.BusinessClassify;
import com.grandland.qdhx.treedata.Node;
import com.grandland.qdhx.web.service.base.BaseService;

public interface BusinessClassifyService extends BaseService<BusinessClassify> {
	
	ResponseDate<List<Node>> getBusinessClassify();
}
