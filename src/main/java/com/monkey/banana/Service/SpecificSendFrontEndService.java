package com.monkey.banana.Service;

import com.google.gson.Gson;
import com.monkey.banana.Class.SendModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.apache.kafka.common.network.Send;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpecificSendFrontEndService {

    private Gson gson = new Gson();
    private String ip;

    public String doAll(String ip) {
        String data;
        this.ip = ip;
        data = getOnlineOfDevices();
        // 返回null代表不在线或者出现其他意外情况
        return data;
    }


    private String getOnlineOfDevices() {
        try {
            SendModel status;

            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            DeviceConditionDAO dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

            // 查询baseinfo中的数据和config中的数据，合起来发给控制器
            status = dcDAO.statusShowSpecific(ip);
            if(status.equals(null)) {
                System.out.println("[发送指定设备信息]查询无结果");
                return "error_dont_exist";
            } else {
                System.out.println("[发送指定设备信息]传输查询结果");
                return gson.toJson(status);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[发送指定设备信息]查询操作失败");
            return "SQLconnection_fail";
        }
    }
}
