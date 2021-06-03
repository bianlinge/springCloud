package com.dove.dao.entity;


public class CashValueApplay {
    private Integer dbseqId;

    private String userId;

    private String adminUserId;

    private String calcDate;

    private String batchNo;

    private String plcNo;

    private String isDown;

    private String checkStatus;

    public Integer getDbseqId() {
        return dbseqId;
    }

    public void setDbseqId(Integer dbseqId) {
        this.dbseqId = dbseqId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPlcNo() {
        return plcNo;
    }

    public void setPlcNo(String plcNo) {
        this.plcNo = plcNo;
    }

    public String getIsDown() {
        return isDown;
    }

    public void setIsDown(String isDown) {
        this.isDown = isDown;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public String toString() {
        return "CashValueApplay{" +
                "dbseqId=" + dbseqId +
                ", userId='" + userId + '\'' +
                ", adminUserId='" + adminUserId + '\'' +
                ", calcDate='" + calcDate + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", plcNo='" + plcNo + '\'' +
                ", isDown='" + isDown + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                '}';
    }
}
