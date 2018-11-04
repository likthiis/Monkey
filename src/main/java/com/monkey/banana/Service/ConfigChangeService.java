package com.monkey.banana.Service;

import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigChangeService {

    DeviceModel deviceModel;
    DeviceConditionDAO dcDAO;

    public ConfigChangeService(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }


    public void doAll() {
        boolean isDone;

        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        System.out.println("[配置更改]开始配置更改流程");

        isDone = ipvaChecking();
        if(isDone) {
            System.out.println("[配置更改]ip条目查询完毕，存在");
            isDone = configChange();
            if(isDone) {
                System.out.println("[配置更改]config条目更新完毕，存在");
            } else {
                System.out.println("[配置更改]config更新失败");
                return;
            }

        } else {
            System.out.println("[配置更改]ip条目不存在，停止更新");
            return;
        }
    }

    private boolean configChange() {
        try {
            int result = dcDAO.updataDeviceByConfig(deviceModel.getIp(),deviceModel.getConfig());
            System.out.println("[配置更改]result为[" + result + "]");
            // 假设result为1
            if(result == 1)
                return true;
            else {
                System.out.println("[配置更改]result为[" + result + "]");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean ipvaChecking() {
        // 查询是否存在
        try {

            int result = dcDAO.searchDeviceInfoByIp(deviceModel.getIp());

            if(result == 1) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
