package com.isoftstone.finance.cwgsapp.bean;

public class DoneApprovalItem {
    public String status;
    public String time;
    public String username;

    public DoneApprovalItem(String paramString1, String paramString2, String paramString3) {
        this.status = paramString1;
        this.username = paramString2;
        this.time = paramString3;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTime() {
        return this.time;
    }

    public String getUsername() {
        return this.username;
    }

    public void setStatus(String paramString) {
        this.status = paramString;
    }

    public void setTime(String paramString) {
        this.time = paramString;
    }

    public void setUsername(String paramString) {
        this.username = paramString;
    }
}