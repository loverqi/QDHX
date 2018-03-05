package com.grandland.qdhx.core.domain;

import java.util.Date;

import com.grandland.qdhx.core.annotation.TableId;
import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 港口关联库存记录
 * @author loverqi
 * @date 2018年2月12日
 */
@TableId(idName = "recordNumberId")
public class PortRelatedInventoryRecords extends MyBatisPojo {
    private static final long serialVersionUID = 5524053204389003235L;

    private Integer id;

    /** 融资单实际id*/
    private String cargoId;

    /** 库存记录临时id*/
    private String recordNumberId;

    /** 日报日期*/
    private Date dailyDate;

    /** 垛位*/
    private String stackPosition;

    /** 本日入库*/
    private Double todayStorage;

    /** 本日出库*/
    private Double todayTreasury;

    /** 本日库存*/
    private Double currentStock;

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

    public String getRecordNumberId() {
        return recordNumberId;
    }

    public void setRecordNumberId(String recordNumberId) {
        this.recordNumberId = recordNumberId == null ? null : recordNumberId.trim();
    }

    public Date getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(Date dailyDate) {
        this.dailyDate = dailyDate;
    }

    public String getStackPosition() {
        return stackPosition;
    }

    public void setStackPosition(String stackPosition) {
        this.stackPosition = stackPosition == null ? null : stackPosition.trim();
    }

    public Double getTodayStorage() {
        return todayStorage;
    }

    public void setTodayStorage(Double todayStorage) {
        this.todayStorage = todayStorage;
    }

    public Double getTodayTreasury() {
        return todayTreasury;
    }

    public void setTodayTreasury(Double todayTreasury) {
        this.todayTreasury = todayTreasury;
    }

    public Double getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Double currentStock) {
        this.currentStock = currentStock;
    }
}