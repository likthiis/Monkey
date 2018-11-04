package com.monkey.banana.Service;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceInfo;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AllSendFrontEndService {

    private Gson gson = new Gson();
    private List<DeviceInfo> deviceInfos;

    public String doAll() {
        String data;
        data = tranToJSON(getAllDataOfDevices());
        return data;
    }

    private String tranToJSON(List<DeviceInfo> deviceInfos) {
        String JsonData = gson.toJson(deviceInfos);
        return JsonData;
    }

    private List<DeviceInfo> getAllDataOfDevices() {

        try {
            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

            deviceInfos = dcDAO.showAllDevicesInfo();

            return deviceInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DeviceInfo> getDeviceInfos() {
        return deviceInfos;
    }
}
