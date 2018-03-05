package com.grandland.qdhx.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BudeadLine;
import com.grandland.qdhx.core.domain.param.BuDeadLineQuParam;
import com.grandland.qdhx.core.mapper.BuDeadLineQueryMapper;
import com.grandland.qdhx.web.service.BsDeadLineService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;

@Service
public class BsDeadLineServiceImpl extends BaseServiceImpl<BudeadLine>  implements BsDeadLineService {

	@Autowired
    private BuDeadLineQueryMapper buDeadLineQueryMapper;
	
	@Override
	public PageResult<Map<String, String>> query(BuDeadLineQuParam buDeadLineQuParam) {

		//添加分页属性
		PageHelper.startPage(buDeadLineQuParam.getPageNum(), buDeadLineQuParam.getPageSize());
        List<Map<String,String>> list = buDeadLineQueryMapper.query(buDeadLineQuParam);

        //用PageInfo对结果进行包装
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);

        return new PageResult<Map<String,String>>(pageInfo);
	}

}
