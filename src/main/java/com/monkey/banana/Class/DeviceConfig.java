package com.monkey.banana.Class;

public class DeviceConfig {
    String ip;
    String config;
    String type;
    String CPU;
    String memory;
    String graphicsCard;


    public DeviceConfig(String ip, String config) {
        this.ip = ip;
        this.config = config;
    }

    public DeviceConfig(String ip, String type, String CPU, String memory, String graphicsCard) {
        this.ip = ip;
        this.type = type;
        this.CPU = CPU;
        this.memory = memory;
        this.graphicsCard = graphicsCard;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
