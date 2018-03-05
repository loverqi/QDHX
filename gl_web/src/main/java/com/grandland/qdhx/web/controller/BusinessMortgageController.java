package com.grandland.qdhx.web.controller;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.BusinessFlow;
import com.grandland.qdhx.core.domain.BusinessMortgage;
import com.grandland.qdhx.core.domain.ValidateEntity.Add;
import com.grandland.qdhx.treedata.Node;
import com.grandland.qdhx.web.controller.param.BusinessFlowParam;
import com.grandland.qdhx.web.controller.param.BusinessMortgageParam;
import com.grandland.qdhx.web.security.util.SecurityUtil;
import com.grandland.qdhx.web.service.BusinessClassifyService;
import com.grandland.qdhx.web.service.BusinessFlowService;
import com.grandland.qdhx.web.service.BusinessMortgageService;

@Api(value = "贷款质物管理", tags = "business")
@RequestMapping("/business")
@RestController
public class BusinessMortgageController {

	@Autowired
	private BusinessMortgageService businessMortgageService;

	@Autowired
	private BusinessClassifyService businessClassifyService;

	@Autowired
	private BusinessFlowService businessFlowService;

	@ApiOperation(value = "查询", notes = "查询质物业务数据table, code为0是成功")
	@RequestMapping(value = "/query", method = { RequestMethod.POST })
	public ResponseDate<ResponsePageDate<BusinessMortgage>> queryBusinessMortgage(
			@RequestBody BusinessMortgageParam businessMortgageParam) {

		try {
			ResponseDate<ResponsePageDate<BusinessMortgage>> responseDate = new ResponseDate<ResponsePageDate<BusinessMortgage>>();
			// 将数据放入返回值
			responseDate.setData(businessMortgageService.selectByexamplewithPage(businessMortgageParam));
			return responseDate;
		} catch (Exception e) {
			// LOGGER.error("查询质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}

	@ApiOperation(value = "新增页面-提交", notes = "新增页面提交-质物业务数据, code为0是成功")
	@RequestMapping(value = "/create", method = { RequestMethod.POST })
	public ResponseDate<?> create(
			@ApiParam @RequestBody @Validated({ Add.class }) BusinessMortgage businessMortgage) {
		try {
			businessMortgageService.CreateBusinessData(businessMortgage);
		} catch (Exception e) {
			// LOGGER.error("新增质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
		return new ResponseDate<>();
	}
	
	@ApiOperation(value = "修改页面-提交", notes = "修改页面-提交-保存质物业务数据详情, code为0是成功")
	@RequestMapping(value = "/modifysubmit", method = { RequestMethod.POST })
	public ResponseDate<?> modifySubmit(
			@ApiParam @RequestBody @Validated({ Add.class }) BusinessMortgage businessMortgage) {

		try {
			String username = SecurityUtil.getUsername();
			businessMortgage.setState(new Integer(1));
			businessMortgage.setIsDeleted(new Integer(0));
			businessMortgage.setCreateTime(new Date());
			businessMortgage.setCreateBy(username);
			businessMortgage.setUpdateBy(username);
			businessMortgageService.updateByPrimaryKey(businessMortgage);
		} catch (Exception e) {
			// LOGGER.error("新增质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
		return new ResponseDate<>();
	}

	@ApiOperation(value = "新增页面-保存", notes = "新增页面-保存-保存质物业务数据详情, code为0是成功")
	@RequestMapping(value = "/creatsave", method = { RequestMethod.POST })
	public ResponseDate<?> creatSave(@RequestBody BusinessMortgage businessMortgage) {
		try {
			String username = SecurityUtil.getUsername();
			businessMortgage.setState(new Integer(0));
			businessMortgage.setIsDeleted(new Integer(0));
			businessMortgage.setCreateTime(new Date());
			businessMortgage.setCreateBy(username);
			businessMortgage.setUpdateBy(username);
			businessMortgageService.insertSelective(businessMortgage);
		} catch (Exception e) {
			// LOGGER.error("新增质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
		return new ResponseDate<>();
	}

	@ApiOperation(value = "修改页面-保存", notes = "修改页面-保存质物业务数据详情, code为0是成功")
	@RequestMapping(value = "/modifysave", method = { RequestMethod.POST })
	public ResponseDate<?> modifySave(@RequestBody BusinessMortgage businessMortgage) {
		try {
			String username = SecurityUtil.getUsername();
			businessMortgage.setState(new Integer(0));
			businessMortgage.setIsDeleted(new Integer(0));
			businessMortgage.setCreateTime(new Date());
			businessMortgage.setCreateBy(username);
			businessMortgage.setUpdateBy(username);
			businessMortgageService.updateByPrimaryKey(businessMortgage);
		} catch (Exception e) {
			// LOGGER.error("新增质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
		return new ResponseDate<>();

	}

	@ApiOperation(value = "查看详情", notes = "查看质物业务数据详情, code为0是成功")
	@ApiImplicitParam(name = "id", value = "业务id", required = true, dataType = "int", paramType = "query")
	@RequestMapping(value = "/get", method = { RequestMethod.POST })
	public ResponseDate<BusinessMortgage> get(@Validated Integer id) {
		try {
			BusinessMortgage bm = businessMortgageService.selectByPrimaryKey(id);
			ResponseDate<BusinessMortgage> res = new ResponseDate<BusinessMortgage>();
			res.setData(bm);
			return res;
		} catch (Exception e) {
			// LOGGER.error("查看详情质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}
	
	@ApiOperation(value = "删除", notes = "删除质物业务数据, code为0是成功")
	@ApiImplicitParam(name = "id", value = "业务id", required = true, dataType = "int", paramType = "query")
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	public ResponseDate<?> delete(@Validated Integer id) {
		try {
			BusinessMortgage bm = businessMortgageService.selectByPrimaryKey(id);
			String username = SecurityUtil.getUsername();
			bm.setIsDeleted(new Integer(1));
			bm.setCreateBy(username);
			bm.setCreateTime(new Date());
			bm.setUpdateBy(username);
			businessMortgageService.updateByPrimaryKeySelective(bm);
		} catch (Exception e) {
			// LOGGER.error("复制质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
		return new ResponseDate<>();
	}

	@ApiOperation(value = "复制", notes = "复制质物业务数据, code为0是成功")
	@ApiImplicitParam(name = "id", value = "业务id", required = true, dataType = "int", paramType = "query")
	@RequestMapping(value = "/copy", method = { RequestMethod.POST })
	public ResponseDate<BusinessMortgage> copy(@Validated Integer id) {
		try {
			BusinessMortgage bm = businessMortgageService.selectByPrimaryKey(id);
			bm.setId(null);
			bm.setApproveReason(null);
			bm.setCheckReason(null);
			String username = SecurityUtil.getUsername();
			bm.setState(new Integer(0));
			bm.setIsDeleted(new Integer(0));
			bm.setCreateTime(new Date());
			bm.setCreateBy(username);
			bm.setUpdateBy(username);
			businessMortgageService.insertSelective(bm);
			ResponseDate<BusinessMortgage> res = new ResponseDate<BusinessMortgage>();
			res.setData(bm);
			return res;
		} catch (Exception e) {
			// LOGGER.error("复制质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}
	
	@ApiOperation(value = "复核/审批", notes = "复核/审批业务数据, code为0是成功")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "id", value = "业务id", required = true, dataType = "int", paramType = "query"),
	@ApiImplicitParam(name = "flag", value = "审核状态", required = true, dataType = "int", paramType = "query"),
	@ApiImplicitParam(name = "reason", value = "理由", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "approver", value = "审批人", required = false, dataType = "String", paramType = "query")
	 })
	@RequestMapping(value = "/check", method = { RequestMethod.POST })
	public ResponseDate<?> check(@Validated Integer id, @Validated Integer flag, @Validated String reason,String approver) {
		try {
			BusinessMortgage bm = businessMortgageService.selectByPrimaryKey(id);
			String username = SecurityUtil.getUsername();
			bm.setChecker(username);
			bm.setState(flag);
			bm.setCheckReason(reason);
			bm.setApprover(approver);
			businessMortgageService.updateByPrimaryKeySelective(bm);

			// 业务流程记录
			BusinessFlow businessFlow = new BusinessFlow();
			businessFlow.setBumorId(id);
			businessFlow.setOperateState(flag);
			businessFlow.setReason(reason);
			businessFlow.setCreateBy(username);
			businessFlow.setCreateTime(new Date());
			businessFlow.setUpdateBy(username);
			businessFlowService.insertSelective(businessFlow);

			return new ResponseDate<>();

		} catch (Exception e) {
			// LOGGER.error("复制质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}

	}

	@ApiOperation(value = "业务产品种类", notes = "查询业务产品种类，树形菜单json数据")
	@RequestMapping(value = "/getbuclassfy", method = { RequestMethod.POST })
	public ResponseDate<List<Node>> getbuclassfy() {

		try {
			return businessClassifyService.getBusinessClassify();
		} catch (Exception e) {
			
			
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}

	}
	
	@ApiOperation(value = "查询业务流程", notes = "查询, code为0是成功")
	@RequestMapping(value = "/flow", method = { RequestMethod.POST })
	public ResponseDate<ResponsePageDate<BusinessFlow>> queryBusinessflow(
			@RequestBody BusinessFlowParam businessFlowParam) {

		try {
			ResponseDate<ResponsePageDate<BusinessFlow>> responseDate = new ResponseDate<ResponsePageDate<BusinessFlow>>();
			// 将数据放入返回值
			responseDate.setData(businessFlowService.queryByexamplewithPage(businessFlowParam));
			return responseDate;
		} catch (Exception e) {
			// LOGGER.error("查询质物业务数据异常",e);
			e.printStackTrace();
			return new ResponseDate<>(ResponseDateCode.SYSTEM_ABNORMALITY,
					ResponseDateCode.SYSTEM_ABNORMALITY_MESSAGE);
		}
	}
}
