package com.monkey.banana.Class;

public class DeviceModel {
    private String ip; //Key
    private String status;
    private String command;
    private String config;
    private String timestamp;

    public DeviceModel(String ip, String status, String command, String config, String timestamp) {
        this.ip = ip;
        this.status = status;
        this.command = command;
        this.config = config;
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

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
