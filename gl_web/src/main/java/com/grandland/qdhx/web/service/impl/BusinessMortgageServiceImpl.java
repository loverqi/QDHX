package com.grandland.qdhx.web.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.mybaties.pojo.Example.Criteria;
import com.grandland.qdhx.core.utils.StringUtil;
import com.grandland.qdhx.web.controller.param.BusinessMortgageParam;
import com.grandland.qdhx.web.security.util.SecurityUtil;
import com.grandland.qdhx.web.service.BusinessMortgageService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

@Service
public class BusinessMortgageServiceImpl extends BaseServiceImpl<BusinessMortgage>  implements BusinessMortgageService {
	
	@Override
	public BusinessMortgage selectByPrimaryKey(Integer id) {
    	BusinessMortgage businessMortgage = new BusinessMortgage();
    	businessMortgage.setId(id);
    	businessMortgage = selectByPrimaryKey(businessMortgage);
        return businessMortgage;
    }
    
    @Override
    public ResponsePageDate<BusinessMortgage>  selectByexamplewithPage(BusinessMortgageParam businessMortgageParam) {
    	
    	 Example example = new Example();
    	 Criteria criteria = example.createCriteria();
    	 
    	 BusinessMortgage businessMortgage = new BusinessMortgage();
    	 BeanUtils.copyProperties(businessMortgageParam,businessMortgage);
    	 criteria.andFieldEqualTo("isDeleted", new Integer(0));
    	 example.setOrderByClause("createTime desc ");
    	 
    	 if(businessMortgageParam.getState()!=null) {
    		
    		 criteria.andFieldEqualTo("state", businessMortgageParam.getState());
    	 }
    	 
    	 if(businessMortgageParam.getBegainTime()!=null) {
    		 
    		 java.util.Date date =   new java.util.Date (businessMortgageParam.getBegainTime().getTime());
    		 criteria.andFieldGreaterThanOrEqualTo("createTime", (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date));
    	 }
    	 
    	 if(businessMortgageParam.getEndTime()!=null) {
    		 java.util.Date date =   new java.util.Date (businessMortgageParam.getEndTime().getTime());
    		 criteria.andFieldLessThanOrEqualTo("createTime",  (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date));
    	 }
    	 
    	 if(StringUtil.isNotNull(businessMortgageParam.getBusinessConum())) {
    		 String businessConum = "%" + businessMortgageParam.getBusinessConum() + "%"; 
    		 criteria.andFieldLike("businessConum", businessConum);
    	 }
    	 
    	 if(StringUtil.isNotNull(businessMortgageParam.getBorrowerName())) {
    		 String borrowerName = "%" + businessMortgageParam.getBorrowerName() + "%"; 
    		 criteria.andFieldLike("borrowerName", borrowerName);
    	 }
    	 
    	 if(StringUtil.isNotNull(businessMortgageParam.getSuperDep())) {
    		 criteria.andFieldEqualTo("superDep", businessMortgageParam.getSuperDep());
    	 }
    	 
    	 if(StringUtil.isNotNull(businessMortgageParam.getMortgageName())) {
    		 criteria.andFieldEqualTo("mortgageName", businessMortgageParam.getMortgageName());
    	 }   	
    	return selectByExampleWithRowbounds(businessMortgage,example,businessMortgageParam.getPageNum(),businessMortgageParam.getPageSize());
    }
    
    @Override
	public void CreateBusinessData(BusinessMortgage businessMortgage) {
		
		String username = SecurityUtil.getUsername();
		businessMortgage.setState(new Integer(1));
		businessMortgage.setIsDeleted(new Integer(0));
		businessMortgage.setCreateTime(new Date());
		businessMortgage.setCreateBy(username);
		businessMortgage.setUpdateBy(username);
		insertSelective(businessMortgage);
	}

}