package com.dove.dao.entity;

import java.util.List;

public class CashValue {
    private String userId;

    private String plcNo;

    private List<String> batchNoList;

    private String startDate;

    private String endDate;

    private String iPageSize;

    private String iStartNo;

    private String checkStatus;

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlcNo() {
        return plcNo;
    }

    public void setPlcNo(String plcNo) {
        this.plcNo = plcNo;
    }

    public List<String> getBatchNoList() {
        return batchNoList;
    }

    public void setBatchNoList(List<String> batchNoList) {
        this.batchNoList = batchNoList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getiPageSize() {
        return iPageSize;
    }

    public void setiPageSize(String iPageSize) {
        this.iPageSize = iPageSize;
    }

    public String getiStartNo() {
        return iStartNo;
    }

    public void setiStartNo(String iStartNo) {
        this.iStartNo = iStartNo;
    }
}
