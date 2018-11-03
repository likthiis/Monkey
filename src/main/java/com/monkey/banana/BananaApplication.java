package com.monkey.banana;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Controller.KafkaReceiveController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BananaApplication {

    private static Gson gson = new Gson();
    private static KafkaReceiveController unitTest;

    public static void main(String[] args) {
        //SpringApplication.run(BananaApplication.class, args);

        unitTest = new KafkaReceiveController();

        // 单元测试

        //unitTestOfRegisterDevice();

        unitTestOfWithDrawDevice();

    }

    private static void unitTestOfWithDrawDevice() {
        System.out.println("单元测试：注销流程模拟");
        String timestamp = Long.toString(System.currentTimeMillis());
        DeviceModel unitTestModel = new DeviceModel(
                "127.0.0.1"      ,
                "whatever"      ,
                "withdraw" ,
                "empty"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);
        unitTest.getInfo(JSONData);
    }

    private static void unitTestOfRegisterDevice() {

        System.out.println("单元测试：注册流程模拟");
        String timestamp = Long.toString(System.currentTimeMillis());


        DeviceModel unitTestModel = new DeviceModel(
                "127.0.0.2"      ,
                "online"      ,
                "register" ,
                "Lenovo_laptop"  ,
                timestamp
        );

        String JSONData = gson.toJson(unitTestModel);

        unitTest.getInfo(JSONData);
    }


}
