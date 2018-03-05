package com.grandland.qdhx.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.grandland.qdhx.core.annotation.FieldIgnore;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户表的实体类
 * @author loverqi
 * @date 2018年1月5日
 */
/**
 * 
 * @author loverqi
 * @date 2018年2月5日
 */
@ApiModel(value = "用户对象类")
public class GlSysUser extends MyBatisPojo implements UserDetails {
    private static final long serialVersionUID = 1076127455501347608L;

    /** 主键*/
    @ApiModelProperty(value = "主键id")
    private Integer id;

    /** 用户名*/
    @ApiModelProperty(value = "用户名")
    private String username;
    
    /** 希存号*/
    @ApiModelProperty(value = "希存号")
    private String greekId;

    /** 密码*/
    @ApiModelProperty(value = "用户密码")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    /** 真实姓名*/
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;

    /** 岗位编号*/
    @ApiModelProperty(value = "用户岗位编号")
    private Integer postId;

    @FieldIgnore
    @ApiModelProperty(value = "用户岗位名称", hidden = true)
    private String postName;
   
    @FieldIgnore
    @ApiModelProperty(value = "用户部门名称", hidden = true)
    private String deptName;

    /** 手机号*/
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    /** 用户所拥有的权限*/
    @FieldIgnore
    private List<GrantedAuthority> authorities;

    /** 账号是否未锁定*/
    @ApiModelProperty(value = "账号是否锁定")
    private Boolean isUnlock;

    /** 添加或更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateUser;

    /** 上次调整授权时间*/
    @ApiModelProperty(value = "调整授权时间")
    private Date updatePrivTime;

    /** 上次调整授权人*/
    @ApiModelProperty(value = "调整授权人")
    private String updatePrivUser;

    /** 用户是否可用*/
    private Boolean enable;

    /** 添加或更新时间*/
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /** 修改密码时间*/
    @ApiModelProperty(value = "修改密码时间")
    private Date updatePwdTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUsername(String userName) {
        this.username = userName == null ? null : userName.trim().toLowerCase();
    }

    public void setPassword(String passward) {
        this.password = passward == null ? null : passward.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @JsonIgnore
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @JsonIgnore
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @JsonIgnore
    public Date getUpdatePwdTime() {
        return updatePwdTime;
    }

    public void setUpdatePwdTime(Date updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    @JsonIgnore
    public Boolean getIsUnlock() {
        return isUnlock;
    }

    public void setIsUnlock(Boolean isUnlock) {
        this.isUnlock = isUnlock;
    }

    @JsonIgnore
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @JsonIgnore
    public Date getUpdatePrivTime() {
        return updatePrivTime;
    }

    public void setUpdatePrivTime(Date updatePrivTime) {
        this.updatePrivTime = updatePrivTime;
    }

    @JsonIgnore
    public String getUpdatePrivUser() {
        return updatePrivUser;
    }

    public void setUpdatePrivUser(String updatePrivUser) {
        this.updatePrivUser = updatePrivUser;
    }

    /**
     * 获取用户名
     * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 获取用户密码
     * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 用户拥有的权限
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? new ArrayList<GrantedAuthority>() : authorities;
    }

    /**
     * 设置权限组
     */
    public void addAuthoritiesGlSysPrivs(List<String> glSysPrivs) {
        for (String string : glSysPrivs) {
            addAuthoritiesGlSysPriv(string);
        }
    }

    /**
     * 设置单个权限
     */
    public void addAuthoritiesGlSysPriv(String authoritieName) {
        addAuthoritiesGlSysPriv(new SimpleGrantedAuthority("ROLE_" + authoritieName));
    }

    /**
     * 设置单个权限
     */
    private void addAuthoritiesGlSysPriv(GrantedAuthority authoritie) {
        if (authorities == null) {
            synchronized (GlSysUser.class) {
                if (authorities == null) {
                    authorities = new ArrayList<GrantedAuthority>();
                }
            }
        }

        authorities.add(authoritie);
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 账户是否未过期
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        // TODO 自动生成的方法存根
        return true;
    }

    /**
     * 帐户是否未锁定
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {

        return isUnlock == null ? false : isUnlock;
    }

    /**
     * 用户密码是否过期
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO 自动生成的方法存根
        return true;
    }

    /**
     * 用户是否可用
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enable == null ? false : enable;
    }

    //限制用户登录次数需要实现的

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GlSysUser other = (GlSysUser) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getGreekId() {
        return greekId;
    }

    public void setGreekId(String greekId) {
        this.greekId = greekId;
    }
    

}