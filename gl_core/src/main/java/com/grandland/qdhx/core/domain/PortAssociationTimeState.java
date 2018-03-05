package com.grandland.qdhx.core.domain;

import java.util.Date;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 港口关联时间状态图
 * @author loverqi
 * @date 2018年2月12日
 */
@TableId(idName = "timeStateId")
public class PortAssociationTimeState extends MyBatisPojo{
    private static final long serialVersionUID = 9142553436579928685L;

    /** 主键id*/
    private Integer id;

    /** 融资单实际id*/
    private String cargoId;

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

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId == null ? null : cargoId.trim();
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

    public String getTimeStateId() {
        return timeStateId;
    }

    public void setTimeStateId(String timeStateId) {
        this.timeStateId = timeStateId == null ? null : timeStateId.trim();
    }
}