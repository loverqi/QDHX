package com.grandland.qdhx.webservice.bean;

import java.io.Serializable;

/**
 * 操作返回结果
 */
public class OperationResult implements Serializable {
    private static final long serialVersionUID = -5939599230753662529L;

    private boolean succeed;
    private String msg;

    public OperationResult() {
    }

    public OperationResult(boolean succeed, String msg) {
        this.succeed = succeed;
        this.msg = msg;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "OperationResult{" +
                "succeed=" + succeed +
                ", msg='" + msg + '\'' +
                '}';
    }
}
