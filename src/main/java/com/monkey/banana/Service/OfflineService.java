package com.monkey.banana.Service;

import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OfflineService {
    private String username;
    private String result;
    private DeviceConditionDAO dcDAO;

    public OfflineService(String username) {
        this.username = username;
        this.result = "do nothing";
    }

    public void doAll() {
        // 直接删除数据库登记表的条目即可

        deleteLoginTable();
    }

    private void deleteLoginTable() {
        try {
            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");
            int re = dcDAO.offUser(username);
            if (re == 1) {
                result = "off_success";
            } else {
                result = "off_fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "catch_exception";
        }
    }

    public String getResult() {
        return result;
    }
}
