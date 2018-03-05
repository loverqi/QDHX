package com.grandland.qdhx.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grandland.qdhx.core.bean.ResponseDate;
import com.grandland.qdhx.core.bean.ResponseDateCode;
import com.grandland.qdhx.core.bean.ResponsePageDate;
import com.grandland.qdhx.core.domain.GlSysUser;
import com.grandland.qdhx.core.mybaties.pojo.Example;
import com.grandland.qdhx.core.utils.StringUtil;
import com.grandland.qdhx.web.security.util.SecurityUtil;
import com.grandland.qdhx.web.service.GlSysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户操作有关的Controller， 所有的返回格式均为json
 * @author loverqi
 * @date 2018年1月8日
 */
@RestController
@RequestMapping("user")
@Api(value = "用户管理", tags = "user")
public class GlSysUserController {

    @Autowired
    private GlSysUserService glSysUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /** TODO
     * 用户修改密码
     * @return ResponseDate<String>
     */
    @ApiOperation(value = "用户修改密码", notes = "用户修改密码的申请，code为0是成功成功")
    @RequestMapping(value = "/changePwd.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "query"), })
    public ResponseDate<String> changePwd(String oldPassword, String newPassword) {
        ResponseDate<String> responseDate = new ResponseDate<String>();
        GlSysUser glSysUser = new GlSysUser();
        Example example = new Example();
        example.createCriteria().andFieldEqualTo("username", SecurityUtil.getUsername());
        List<GlSysUser> glSysUsers = glSysUserService.selectByExample(glSysUser, example);
        if (glSysUsers == null || glSysUsers.size() <= 0) {
            //未找到用户
            responseDate.setCode(ResponseDateCode.NOT_FIND_USER_ERROR);
            responseDate.setMessage(ResponseDateCode.USERNAME_OR_PASSWORD_ERROR_MESSAGE);
        }

        glSysUser = glSysUsers.get(0);
        boolean matches = passwordEncoder.matches(oldPassword, glSysUser.getPassword());

        if (!matches) {
            //密码错误
            responseDate.setCode(ResponseDateCode.PASSWORD_ERROR);
            responseDate.setMessage(ResponseDateCode.PASSWORD_ERROR_MESSAGE);
        } else if (newPassword.length() < 6) { //TODO 从配置文件读
            //密码复杂度不符合条件 
            responseDate.setCode(ResponseDateCode.PASSWORD_COMPLEXITY_ERROR);
            responseDate.setMessage(ResponseDateCode.PASSWORD_COMPLEXITY_ERROR_MESSAGE);
        } else {
            //开始修改密码
            glSysUser.setPassword(passwordEncoder.encode(newPassword));
            glSysUserService.updatePasswordByPrimaryKey(glSysUser);
        }

        return responseDate;
    }

    @ApiOperation(value = "根据查询条件获取需要的用户", notes = "根据查询条件获取需要的用户，code为0是成功")
    @RequestMapping(value = "/getUsers.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "greekId", value = "希存号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "realName", value = "真实姓名", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "postId", value = "岗位编号", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "申请的页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页的个数", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<ResponsePageDate<GlSysUser>> getUsers(String greekId, String username, String realName,
            Integer postId, String phone, Integer pageNum, Integer pageSize) {
        ResponseDate<ResponsePageDate<GlSysUser>> responseDate = new ResponseDate<ResponsePageDate<GlSysUser>>();
        if (pageNum == null || pageSize == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            Example example = new Example();

            if (StringUtil.isNotNull(greekId)) {
                example.createCriteria().andFieldEqualTo("greekId", greekId);
            }
            if (StringUtil.isNotNull(username)) {
                example.createCriteria().andFieldLike("username", "%" + username + "%");
            }
            if (StringUtil.isNotNull(realName)) {
                example.createCriteria().andFieldLike("realName", "%" + realName + "%");
            }
            if (postId != null) {
                example.createCriteria().andFieldEqualTo("postId", postId);
            }
            if (StringUtil.isNotNull(phone)) {
                example.createCriteria().andFieldLike("phone", "%" + phone + "%");
            }

            GlSysUser glSysUser = new GlSysUser();
            ResponsePageDate<GlSysUser> responsePageDate = glSysUserService
                    .selectByExampleWithRowboundsAndConnectionName(glSysUser, example, pageNum, pageSize);
            //将数据放入返回值
            responseDate.setData(responsePageDate);
        }

        return responseDate;
    }

    @ApiOperation(value = "查询所有的岗位简化信息列表", notes = "查询所有的岗位简化信息列表，code为0是成功")
    @RequestMapping(value = "/getSimplifyUsers.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "岗位编号", required = false, dataType = "String", paramType = "query"), })
    public ResponseDate<List<Map<String, String>>> getSimplifyUsers(Integer postId) {
        ResponseDate<List<Map<String, String>>> responseDate = new ResponseDate<List<Map<String, String>>>();

        if (postId == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {

            List<Map<String, String>> simplifyUsers = glSysUserService.selectSimplifyUsers(postId);

            //将数据放入返回值
            responseDate.setData(simplifyUsers);
        }

        return responseDate;
    }

    @ApiOperation(value = "新建或者修改用户", notes = "新建或者修改用户,有id时为修改，无id时为新建，code为0是成功")
    @RequestMapping(value = "/AddOrModifyUser.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> AddOrModifyUser(@RequestBody GlSysUser user) {

        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();

        int insert = 0;
        if (user.getId() != null) {
            insert = glSysUserService.updateByPrimaryKeySelective(user);
            responseDate.setData(insert > 0);
        } else {
            if (StringUtil.isNull(user.getGreekId())) {
                responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
                responseDate.setMessage("希存号不能为空");
            } else if (StringUtil.isNull(user.getUsername())) {
                responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
                responseDate.setMessage("用户名不能为空");
            } else if (StringUtil.isNull(user.getPassword())) {
                responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
                responseDate.setMessage("用户密码不能为空");
                //TODO 顺便验证密码复杂度    
            } else if (user.getPassword().length() < 6) {
                //密码复杂度不符合条件 
                responseDate.setCode(ResponseDateCode.PASSWORD_COMPLEXITY_ERROR);
                responseDate.setMessage(ResponseDateCode.PASSWORD_COMPLEXITY_ERROR_MESSAGE);
            } else {
                insert = glSysUserService.insertSelective(user);
                responseDate.setData(insert > 0);
            }

        }

        return responseDate;
    }

    @ApiOperation(value = "根据id删除用户", notes = "根据id产出用户，code为0是成功")
    @RequestMapping(value = "/deleteUser.do", method = { RequestMethod.POST })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的id", required = true, dataType = "int", paramType = "query"), })
    public ResponseDate<Boolean> deleteUser(Integer id) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (id == null) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysUser glSysUser = new GlSysUser();
            glSysUser.setId(id);
            int deleteByPrimaryKey = glSysUserService.deleteByPrimaryKey(glSysUser);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

    @ApiOperation(value = "根据id删除用户", notes = "根据id产出用户，code为0是成功")
    @RequestMapping(value = "/deleteUsers.do", method = { RequestMethod.POST })
    public ResponseDate<Boolean> deleteUsers(@RequestBody List<Integer> ids) {
        ResponseDate<Boolean> responseDate = new ResponseDate<Boolean>();
        if (ids == null || ids.size() == 0) {
            responseDate.setCode(ResponseDateCode.PARAMETER_ANOMALY);
            responseDate.setMessage(ResponseDateCode.PARAMETER_ANOMALY_MESSAGE);
        } else {
            GlSysUser glSysUser = new GlSysUser();
            Example example = new Example();
            example.createCriteria().andFieldIn("id", ids);
            int deleteByPrimaryKey = glSysUserService.deleteByExample(glSysUser, example);

            //将数据放入返回值
            responseDate.setData(deleteByPrimaryKey > 0);
        }

        return responseDate;
    }

}
