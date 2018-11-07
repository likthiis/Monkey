package com.monkey.banana;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Controller.KafkaReceiveController;
import com.monkey.banana.Feed.TempListSaver;
import com.monkey.banana.Kafka.KafkaConsumer;
import com.monkey.banana.Kafka.KafkaProperties;
import com.monkey.banana.SelfChecking.SelfCheckingThread;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BananaApplication {

    private static Gson gson = new Gson();
    private static KafkaReceiveController unitTest = new KafkaReceiveController();;
    private static TempListSaver tmpS = new TempListSaver();
    private static SelfCheckingThread sfT = new SelfCheckingThread();


    public static void main(String[] args) {
        //SpringApplication.run(BananaApplication.class, args);

        // 单元测试
        // 批量注册
        //insertBath();

        // 改变配置
        //changeConfigByIp("127.0.0.4", "dell");

        // 数据库自检
        //SelfCheckingThread selfCheckingThread = new SelfCheckingThread();
        //selfCheckingThread.start();




        // 撤销设备
        //unitTestOfWithDrawDevice();

        // http响应系统
        SpringApplication.run(BananaApplication.class, args);
        tmpS.start();
        sfT.start();
        BasicConfigurator.configure();
        //KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        //consumerThread.start();
        // 注册设备+发送心跳包
        //registerAndKeepingSend("192.168.1.102");
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

        tmpS.invokeForCheck(JSONData);
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

        tmpS.invokeForCheck(JSONData);
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
        System.out.println("单元测试：批量注册模拟");
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

        tmpS.invokeForCheck(JSONData);
        unitTest.getInfo(JSONData);
    }



}
