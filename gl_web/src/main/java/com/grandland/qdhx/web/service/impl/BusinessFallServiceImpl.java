package com.grandland.qdhx.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessFall;
import com.grandland.qdhx.core.domain.param.BsFallQueryParam;
import com.grandland.qdhx.core.mapper.BsFallQueryMapper;
import com.grandland.qdhx.web.service.BusinessFallService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

@Service
public class BusinessFallServiceImpl extends BaseServiceImpl<BusinessFall>  implements BusinessFallService  {

	@Autowired
    private BsFallQueryMapper bsFallQueryMapper;
	
	@Override
	public PageResult<Map<String,String>> query(BsFallQueryParam bsFallQueryParam) {
	    
	
		//添加分页属性
		PageHelper.startPage(bsFallQueryParam.getPageNum(), bsFallQueryParam.getPageSize());
        List<Map<String,String>> list = bsFallQueryMapper.query(bsFallQueryParam);

        //用PageInfo对结果进行包装
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);

        return new PageResult<Map<String,String>>(pageInfo);
		
	}
}
