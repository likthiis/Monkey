package com.monkey.banana;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Controller.KafkaReceiveController;
import com.monkey.banana.SelfChecking.SelfCheckingThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BananaApplication {

    private static Gson gson = new Gson();
    private static KafkaReceiveController unitTest;

    public static void main(String[] args) throws InterruptedException {
        //SpringApplication.run(BananaApplication.class, args);

        unitTest = new KafkaReceiveController();


        // 单元测试
        //insertBath();

        //changeConfigByIp("127.0.0.4", "dell");

        //SelfCheckingThread selfCheckingThread = new SelfCheckingThread();
        //selfCheckingThread.start();

        registerAndKeepingSend("192.168.1.102");

        //unitTestOfWithDrawDevice();

        //SpringApplication.run(BananaApplication.class, args);

    }

    private static void registerAndKeepingSend(String ip) throws InterruptedException {
        // 先注册，然后持续发送心跳包
        regsiterYourLovelyDevice(ip);
        Thread.sleep(2000);

        while(true) {
            //发送心跳包
            heartBeatGo(ip);

            Thread.sleep(3000);
        }
    }

    private static void heartBeatGo(String ip) {
        System.out.println("新的心跳包！");
        String timestamp = Long.toString(System.currentTimeMillis());
        DeviceModel unitTestModel = new DeviceModel(
                ip     ,
                "online"      ,
                "heartbeat" ,
                "无所谓"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);
        unitTest.getInfo(JSONData);
    }

    private static void regsiterYourLovelyDevice(String ip) {
        System.out.println("单元测试：模拟注册+心跳包注入");
        String timestamp = Long.toString(System.currentTimeMillis());
        DeviceModel unitTestModel = new DeviceModel(
                ip     ,
                "online"      ,
                "register" ,
                "清华同方"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);
        unitTest.getInfo(JSONData);
    }

    private static void changeConfigByIp(String ip,String config) {
        System.out.println("单元测试：修改配置模拟");
        String timestamp = Long.toString(System.currentTimeMillis());
        DeviceModel unitTestModel = new DeviceModel(
                ip     ,
                "online"      ,
                "config_change" ,
                config  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);
        unitTest.getInfo(JSONData);
    }

    private static void insertBath() {
        // 批量注入
        int NUMBER = 10;

        String[] bath = new String[NUMBER];
        for(int i = 1;i <= NUMBER;i++) {
            bath[i - 1] = "127.0.0." + i;
            unitTestOfRegisterDevice(bath[i - 1]);
        }
    }

    private static void unitTestOfWithDrawDevice(String ip) {
        System.out.println("单元测试：注销流程模拟");
        String timestamp = Long.toString(System.currentTimeMillis());
        DeviceModel unitTestModel = new DeviceModel(
                ip     ,
                "whatever"      ,
                "withdraw" ,
                "empty"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);
        unitTest.getInfo(JSONData);
    }

    private static void unitTestOfRegisterDevice(String ip) {

        System.out.println("单元测试：注册流程模拟");
        String timestamp = Long.toString(System.currentTimeMillis());


        DeviceModel unitTestModel = new DeviceModel(
                ip      ,
                "online"      ,
                "register" ,
                "Lenovo_laptop"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);

        unitTest.getInfo(JSONData);
    }



}
