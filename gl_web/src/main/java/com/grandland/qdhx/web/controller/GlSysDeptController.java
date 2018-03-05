package com.grandland.qdhx.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysDept;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.StringUtil;
import com.grandland.qdhx.web.service.GlSysDeptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 部门操作有关的Controller， 所有的返回格式均为json
 * @author loverqi
 * @date 2018年1月8日
 */
@RestController
@RequestMapping("dept")
@Api(value = "部门管理", tags = "dept")
public class GlSysDeptController {

    @Autowired
    private GlSysDeptService glSysDeptService;

    @ApiOperation(value = "根据查询条件获取需要的部门", notes = "根据查询条件获取需要的部门，code为0是成功")
    @RequestMapping(value = "/getDepts.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptName", value = "部门名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "申请的页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的个数", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<ResponsePageDate<GlSysDept>> getDepts(String deptName, Integer pageNum, Integer pageSize) {
        ResponseDate<ResponsePageDate<GlSysDept>> responseDate = new ResponseDate<ResponsePageDate<GlSysDept>>();
        if (pageNum == null || pageSize == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            Example example = new Example();

            if (StringUtil.isNotNull(deptName)) {
                example.createCriteria().andFieldLike("deptName", "%" + deptName + "%");
            }

            GlSysDept glSysDept = new GlSysDept();
            ResponsePageDate<GlSysDept> glSysDepts = glSysDeptService.selectByExampleWithRowbounds(glSysDept, example,
                    pageNum, pageSize);
            //将数据放入返回值
            responseDate.setData(glSysDepts);
        }

        return responseDate;
    }

    @ApiOperation(value = "查询所有的部门简化信息列表", notes = "查询所有的部门简化信息列表，code为0是成功")
    @RequestMapping(value = "/getSimplifyDepts.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseDate<List<Map<String, String>>> getSimplifyDepts() {
        ResponseDate<List<Map<String, String>>> responseDate = new ResponseDate<List<Map<String, String>>>();

        List<Map<String, String>> simplifyDepts = glSysDeptService.selectSimplifyDepts();

        //将数据放入返回值
        responseDate.setData(simplifyDepts);

        return responseDate;
    }

    @ApiOperation(value = "新建或者修改部门", notes = "新建或者修改部门,有id时为修改，无id时为新建，code为0是成功")
    @RequestMapping(value = "/AddOrModifyDept.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> AddOrModifyDept(@RequestBody GlSysDept dept) {

        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();

        int insert = 0;
        if (dept.getId() != null) {
            insert = glSysDeptService.updateByPrimaryKeySelective(dept);
        } else {
            dept.setEnable(true);
            insert = glSysDeptService.insertSelective(dept);
        }

        responseDate.setData(insert > 0);

        return responseDate;
    }

    @ApiOperation(value = "根据id删除部门", notes = "根据id产出用户，code为0是成功")
    @RequestMapping(value = "/deleteDept.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门的id", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<Boolean> deleteDept(Integer id) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (id == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysDept glSysDept = new GlSysDept();
            glSysDept.setId(id);
            int deleteByPrimaryKey = glSysDeptService.deleteByPrimaryKey(glSysDept);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

    @ApiOperation(value = "根据id删除部门", notes = "根据id删除部门，code为0是成功")
    @RequestMapping(value = "/deleteDepts.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> deleteDepts(@RequestBody List<Integer> ids) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (ids == null || ids.size() == 0) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysDept glSysDept = new GlSysDept();
            Example example = new Example();
            example.createCriteria().andFieldIn("id", ids);
            int deleteByPrimaryKey = glSysDeptService.deleteByExample(glSysDept, example);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

}
