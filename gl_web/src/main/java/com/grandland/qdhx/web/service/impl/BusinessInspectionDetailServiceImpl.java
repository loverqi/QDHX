package com.grandland.qdhx.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.domain.BusinessInspectionDetail;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.core.mapper.BsInspectionDetailMapper;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.PrimaryKey;
import com.grandland.qdhx.web.service.BusinessInspectionDetailService;
import com.grandland.qdhx.web.service.GlSysUserService;
import com.grandland.qdhx.web.service.base.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class BusinessInspectionDetailServiceImpl extends BaseServiceImpl<BusinessInspectionDetail>  implements BusinessInspectionDetailService {
	private static final Logger LOG = LoggerFactory.getLogger(BusinessInspectionServiceImpl.class);

	@Autowired
    private BsInspectionDetailMapper bsInspectionDetailMapper;

	@Autowired
	private GlSysUserService userService;
	
	@Override
	public PageResult<Map<String,String>> queryDetail(BsInspectionQueryParam param) {
	
		//添加分页属性
		if (!ObjectUtils.isEmpty(param.getPageNum()) && !ObjectUtils.isEmpty(param.getPageSize())) {
			PageHelper.startPage(param.getPageNum(), param.getPageSize());
		}
        List<Map<String,String>> list = bsInspectionDetailMapper.queryDetail(param);
		LOG.debug("list:{}", list);
		handler(list);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(list);

        return new PageResult<Map<String,String>>(pageInfo);
		
	}

	public void handler(List<Map<String,String>> list) {
		if (list != null) {
			GlSysUser glSysUser = new GlSysUser();
			for (Map<String, String> map : list) {
				if (map.containsKey(PrimaryKey.USER_ID)) {
					Example example = new Example();
					example.createCriteria().andFieldEqualTo(PrimaryKey.GREEK_ID, map.get(PrimaryKey.USER_ID));
					List<GlSysUser> userList = userService.selectByExample(glSysUser, example);
					if (!ObjectUtils.isEmpty(userList)) {
						String realName = userList.get(0).getRealName();
						map.put(PrimaryKey.REAL_NAME, realName);
					}

					//TODO 岗位
				}
			}
		}
	}
}
