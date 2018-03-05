package com.grandland.qdhx.web.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.BusinessFlow;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.mybaties.pojo.Example.Criteria;
import com.grandland.qdhx.web.controller.param.BusinessFlowParam;
import com.grandland.qdhx.web.service.BusinessFlowService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

@Service
public class BusinessFlowServiceImpl extends BaseServiceImpl<BusinessFlow>  implements BusinessFlowService {

	@Override
	public ResponsePageDate<BusinessFlow> queryByexamplewithPage(BusinessFlowParam businessFlowParam) {
		Example example = new Example();
   	 Criteria criteria = example.createCriteria();
   	 
     BusinessFlow businessFlow = new BusinessFlow();
   	 BeanUtils.copyProperties(businessFlowParam,businessFlow);
   	 criteria.andFieldEqualTo("isDeleted", new Integer(0));
   	 example.setOrderByClause("createTime desc ");
   	 
   	 if(businessFlowParam.getOperateState()!=null) {
   		
   		 criteria.andFieldEqualTo("operateState", businessFlowParam.getOperateState());
   	 }
   	 
   	 if(businessFlowParam.getBegainTime()!=null) {
   		 
   		 java.util.Date date =   new java.util.Date (businessFlowParam.getBegainTime().getTime());
   		 criteria.andFieldGreaterThanOrEqualTo("createTime", (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date));
   	 }
   	 
   	 if(businessFlowParam.getEndTime()!=null) {
   		 java.util.Date date =   new java.util.Date (businessFlowParam.getEndTime().getTime());
   		 criteria.andFieldLessThanOrEqualTo("createTime",  (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date));
   	 }
   	 
   	return selectByExampleWithRowbounds(businessFlow,example,businessFlowParam.getPageNum(),businessFlowParam.getPageSize());
	}

}
