package com.grandland.qdhx.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessInspection;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.core.mapper.BsInspectionMapper;
import com.grandland.qdhx.core.utils.PrimaryKey;
import com.grandland.qdhx.web.service.BusinessInspectionService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class BusinessInspectionServiceImpl extends BaseServiceImpl<BusinessInspection>  implements BusinessInspectionService  {
	private static final Logger LOG = LoggerFactory.getLogger(BusinessInspectionServiceImpl.class);


	@Autowired
    private BsInspectionMapper bsInspectionMapper;
	
	@Override
	public PageResult<Map<String,String>> query(BsInspectionQueryParam param) {

		//添加分页属性
		if (!ObjectUtils.isEmpty(param.getPageNum()) && !ObjectUtils.isEmpty(param.getPageSize())) {
			PageHelper.startPage(param.getPageNum(), param.getPageSize());
		}
        List<Map<String,String>> list = bsInspectionMapper.query(param);
		LOG.debug("list:{}", list);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);

        return new PageResult<Map<String,String>>(pageInfo);
		
	}


	public void handler(List<Map<String,String>> list) {
		if (list != null) {
			for (Map<String, String> map : list) {
				if (map.containsKey(PrimaryKey.BUSINESS_ID)) {
//					String businessId = map.get(PrimaryKey.BUSINESS_ID);
					//TODO 获取状态？
				}
			}
		}
	}
}
