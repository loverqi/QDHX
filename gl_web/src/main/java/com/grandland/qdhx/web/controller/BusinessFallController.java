package com.grandland.qdhx.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.domain.param.BsFallQueryParam;
import com.grandland.qdhx.web.service.BusinessFallService;

@Api(value = "低价补偿查询", tags = "business")
@RequestMapping("/bsfall")
@RestController
public class BusinessFallController {

	@Autowired
	private BusinessFallService businessFallService;

	@ApiOperation(value = "查询", notes = "查询低价补偿数据table, code为0是成功")
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public ResponseDate<PageResult<Map<String,String>>> queryBusinessMortgage(
			@RequestBody  BsFallQueryParam bsFallQueryParam) {

		try {
			ResponseDate<PageResult<Map<String,String>>> responseDate = new ResponseDate<PageResult<Map<String,String>>>();
			// 将数据放入返回值
			responseDate.setData(businessFallService.query(bsFallQueryParam));
			return responseDate;
		} catch (Exception e) {
			// LOGGER.error("查询质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}

}
