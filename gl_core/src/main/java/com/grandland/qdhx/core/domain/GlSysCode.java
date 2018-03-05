package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 字典表
 */
public class GlSysCode extends MyBatisPojo{

    /** */
    private static final long serialVersionUID = 9156109066074287290L;

    /** 自增主键 */
    private Integer id;

    /** 类别 */
    private String type;

    /** 代码 */
    private String code;

    /** 代码说明 */
    private String comment;

    /** 备注 */
    private String remark;

    /** 是否可用 */
    private boolean enable;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "GlSysCode{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", comment='" + comment + '\'' +
                ", remark='" + remark + '\'' +
                ", enable=" + enable +
                '}';
    }
}
