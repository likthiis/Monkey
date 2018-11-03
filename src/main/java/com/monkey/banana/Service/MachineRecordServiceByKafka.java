package com.monkey.banana.Service;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MachineRecordServiceByKafka {
    private Gson gson = new Gson();

    // 发过来的数据是json格式的
    public void doAll(String record) {
        // json解码
        DeviceModel deviceModel = gson.fromJson(record,DeviceModel.class);
        // 根据status的数据进行判断
        boolean log = judge(deviceModel.getStatus());
        // 执行操作
        if(log == true) {
            RegisterDevice(deviceModel);
        }
        if(log == false) {
            WithdrawDevice(deviceModel.getIp());
        }
    }

    private void WithdrawDevice(String ip) {
        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        int result = dcDAO.withdrawDevice(ip);
    }

    private void RegisterDevice(DeviceModel deviceModel) {
        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        //int result = dcDAO.registerDevice(deviceModel);
    }

    // 必须完善的一个方法
    private boolean judge(String status) {
        return false;
    }
}
