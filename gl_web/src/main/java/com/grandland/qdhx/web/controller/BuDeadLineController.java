package com.grandland.qdhx.web.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.domain.param.BuDeadLineQuParam;
import com.grandland.qdhx.web.service.BsDeadLineService;

@Api(value = "查询业务到期", tags = "business")
@RequestMapping("/bsdeadline")
@RestController
public class BuDeadLineController {
	
	@Autowired
	private BsDeadLineService bsDeadLineService;

	@ApiOperation(value = "查询", notes = "查询业务到期table, code为0是成功")
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public ResponseDate<PageResult<Map<String,String>>> queryBusinessMortgage(
			@RequestBody  BuDeadLineQuParam buDeadLineQuParam) {

		try {
			ResponseDate<PageResult<Map<String,String>>> responseDate = new ResponseDate<PageResult<Map<String,String>>>();
			// 将数据放入返回值
			responseDate.setData(bsDeadLineService.query(buDeadLineQuParam));
			return responseDate;
		} catch (Exception e) {
			// LOGGER.error("查询质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}
}
