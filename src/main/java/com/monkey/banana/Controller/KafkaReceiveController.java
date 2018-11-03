package com.monkey.banana.Controller;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Service.*;
import com.monkey.banana.Util.Tools;

import java.sql.Timestamp;

public class KafkaReceiveController {

    private Gson gson = new Gson();

    // kafka的信息包括以下几种：1、注册机器 2、心跳包信息记录 3、机器注销 4、配置消息注入
    public void getInfo(String kafkaData) {
        DeviceModel deviceModel = gson.fromJson(kafkaData, DeviceModel.class);
        int vectorTo; // 系统可识别的命令


        // deviceModel包括了ip(string) status(string) command(string) config(string) timestamp(string)
        // controller判断传来的command是什么样的后，再将deviceModel传给服务层

        // 指令判断
        vectorTo = commandJudge(deviceModel.getCommand());

        if(vectorTo == 1761) { // 注册机器
            RegisterDeviceService rdService = new RegisterDeviceService(deviceModel);
            rdService.doAll();
        }
        if(vectorTo == 1937) { // 注销机器
            WithdrawDeviceService wdService = new WithdrawDeviceService(deviceModel);
            wdService.doAll();
        }

        if(vectorTo == 1991) { // 心跳包事务
            HeartbeatTransactionService htService = new HeartbeatTransactionService(deviceModel);
            htService.doAll();
        }

        if(vectorTo == 2018) { // 配置更改
            ConfigChangeService ccService = new ConfigChangeService(deviceModel);
            ccService.doAll();
        }

        // 判断kafakData中的特征字status
        int result = judge(deviceModel.getStatus());

        if(result == 1) {
            InfoServiceByKafka infoServiceByKafka = new InfoServiceByKafka();
            infoServiceByKafka.doAll(kafkaData);
        }
        if(result == 2) {
            MachineRecordServiceByKafka machineRecordServiceByKafka = new MachineRecordServiceByKafka();
            machineRecordServiceByKafka.doAll(kafkaData);
        }
    }

    private int commandJudge(String command) {
        if(command.equals("register")) {
            return 1761; // 用个比较特殊的数字，不也挺好吗？
        }
        if(command.equals("withdraw")) {
            return 1937;
        }

        if(command.equals("heartbeat")) {
            return 1991;
        }

        // 这个没有做草图

        if(command.equals("config_change")) {
            return 2018;
        }


        return 404; // 用个常用标识，不也挺好吗？
    }

    // 很重要的判断
    private int judge(String status) {
        return 0;
    }

}
