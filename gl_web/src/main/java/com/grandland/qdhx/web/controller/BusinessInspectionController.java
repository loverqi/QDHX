package com.grandland.qdhx.web.controller;

import com.grandland.qdhx.core.bean.PageResult;
import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.domain.param.BsInspectionQueryParam;
import com.grandland.qdhx.web.service.BusinessInspectionDetailService;
import com.grandland.qdhx.web.service.BusinessInspectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "巡核库查询", tags = "business")
@RequestMapping("/bs_inspect")
@RestController
public class BusinessInspectionController {
	private static final Logger LOG = LoggerFactory.getLogger(BusinessInspectionController.class);

	@Autowired
	private BusinessInspectionService inspectionService;

	@Autowired
	private BusinessInspectionDetailService inspectionDetailService;

	@ApiOperation(value = "查询", notes = "查询巡核库table, code为0是成功")
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public ResponseDate<PageResult<Map<String,String>>> queryBsInpection(
			@RequestBody BsInspectionQueryParam param) {
		LOG.debug("param:{}", param);
		try {
			ResponseDate<PageResult<Map<String,String>>> responseDate = new ResponseDate<PageResult<Map<String,String>>>();
			responseDate.setData(inspectionService.query(param));
			return responseDate;
		} catch (Exception e) {
			LOG.error("查询巡核库列表出错:{}", e);
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}


	@ApiOperation(value = "查询详细", notes = "查询巡核库详细table, code为0是成功")
	@RequestMapping(value = "/detail", method = { RequestMethod.POST })
	public ResponseDate<PageResult<Map<String,String>>> queryBsInpectionDetail(
			@RequestBody BsInspectionQueryParam param) {
		LOG.debug("param:{}", param);
		try {
			ResponseDate<PageResult<Map<String,String>>> responseDate = new ResponseDate<PageResult<Map<String,String>>>();
			responseDate.setData(inspectionDetailService.queryDetail(param));
			return responseDate;
		} catch (Exception e) {
			LOG.error("查询巡核库详细信息列表出错:{}", e);
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}
}
