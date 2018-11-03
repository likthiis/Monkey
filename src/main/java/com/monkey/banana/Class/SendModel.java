package com.monkey.banana.Class;

public class SendModel {
    String ip;
    String status;
    String timestamp;
    String config;

    public SendModel(String ip, String status, String timestamp, String config) {
        this.ip = ip;
        this.status = status;
        this.timestamp = timestamp;
        this.config = config;
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

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
