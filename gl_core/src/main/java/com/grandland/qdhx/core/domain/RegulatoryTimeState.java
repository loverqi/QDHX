package com.grandland.qdhx.core.domain;

import java.util.Date;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 融资监管时间状态图
 * @author loverqi
 * @date 2018年2月2日
 */
@TableId(idName = "timeStateId")
public class RegulatoryTimeState extends MyBatisPojo {
    private static final long serialVersionUID = -1628356961696448538L;

    /** 主键id*/
    private Integer id;

    private String cargoId;

    /** 阶段类型*/
    private String stageType;

    /** 申请人*/
    private String applicant;

    /** 事件状态*/
    private String eventState;

    /** 事件时间*/
    private Date eventDate;

    /** 根据时间顺序生成的id*/
    private String timeStateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType == null ? null : stageType.trim();
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public String getEventState() {
        return eventState;
    }

    public void setEventState(String eventState) {
        this.eventState = eventState == null ? null : eventState.trim();
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getTimeStateId() {
        return timeStateId;
    }

    public void setTimeStateId(String timeStateId) {
        this.timeStateId = timeStateId;
    }

}