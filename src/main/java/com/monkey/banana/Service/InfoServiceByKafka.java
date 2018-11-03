package com.monkey.banana.Service;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InfoServiceByKafka {
    private Gson gson = new Gson();

    // 发过来的数据是json格式的
    public void doAll(String info) {
        // json解码
        DeviceModel deviceModel = gson.fromJson(info,DeviceModel.class);
        // 根据status进行判断
        int log = judge(deviceModel.getStatus());
        // 执行操作
        if(log == 1) {
            statusInsert(deviceModel.getIp(), deviceModel);
        }
        if(log == 2) {
            configInsert(deviceModel.getIp(), deviceModel);
        }
    }

    private void configInsert(String ip, DeviceModel deviceModel) {
        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        int result = dcDAO.updataDeviceByStatus(ip, deviceModel.getConfig());
    }

    private void statusInsert(String ip, DeviceModel deviceModel) {
        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        int result = dcDAO.updataDeviceByStatus(ip, deviceModel.getStatus());
    }

    // 必须完善的一个方法
    private int judge(String status) {
        return 404;
    }
}
