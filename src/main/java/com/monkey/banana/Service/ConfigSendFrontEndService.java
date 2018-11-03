package com.monkey.banana.Service;

import com.google.gson.Gson;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 注意这里返回的不是JSON数据，配置数据在目前的版本里会直接发送给前端！
public class ConfigSendFrontEndService {
    private Gson gson = new Gson();

    public String doAll(String ip) {
        String data;
        data = getOnlineOfDevices(ip);
        return data;
    }

    private String getOnlineOfDevices(String ip) {
        String config;

        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        config = dcDAO.configShowSpecific(ip);

        return config;
    }
}
