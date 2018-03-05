package com.grandland.qdhx.core.domain;

import com.grandland.qdhx.core.mybaties.pojo.MyBatisPojo;

/**
 * 定时任务
 */
public class GlCronTask extends MyBatisPojo {

	private static final long serialVersionUID = -3062164353695634552L;

	/** 编号 */
    private Integer id;

    /** 任务 */
    private String taskName;

    /** 任务描述 */
    private String taskDesc;

    /** 类型 */
    private String taskType;

    /** 组 */
    private String groupName;

    /** cron表达式 */
    private String cronExp;

    /** class类 */
    private String clazz;

    /** 是否有效 */
    private boolean enable;

    /** 现在立即执行 */
    private boolean runNow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCronExp() {
        return cronExp;
    }

    public void setCronExp(String cronExp) {
        this.cronExp = cronExp;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isRunNow() {
        return runNow;
    }

    public void setRunNow(boolean runNow) {
        this.runNow = runNow;
    }

    @Override
    public String toString() {
        return "GlCronTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskType='" + taskType + '\'' +
                ", groupName='" + groupName + '\'' +
                ", cronExp='" + cronExp + '\'' +
                ", clazz='" + clazz + '\'' +
                ", enable=" + enable +
                ", runNow=" + runNow +
                '}';
    }
}
