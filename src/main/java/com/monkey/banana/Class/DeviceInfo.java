package com.monkey.banana.Class;

public class DeviceInfo {
    String ip;
    String status;
    String timestamp;
    // 时间戳到服务层再更改


    public DeviceInfo(String ip, String status, String timestamp) {
        this.ip = ip;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
