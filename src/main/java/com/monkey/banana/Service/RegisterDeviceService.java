package com.monkey.banana.Service;

import com.monkey.banana.Class.DeviceConfig;
import com.monkey.banana.Class.DeviceInfo;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import com.monkey.banana.Util.Tools;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;


// 少写了日志记录(看情况)
public class RegisterDeviceService {
    DeviceConditionDAO dcDAO;
    DeviceModel deviceModel;
    DeviceInfo deviceInfo;
    DeviceConfig deviceConfig;

    public RegisterDeviceService(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
        System.out.println("[设备注册]已经进入RegisterDeviceService");
    }

    public void doAll() {
        boolean isDone = false;

        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        System.out.println("[设备注册]已经进入doAll");

        // 判断有效性
        isDone = timeJudge(deviceModel.getTimestamp());
        if(isDone) {
            System.out.println("[设备注册]timeJudge过关，流程继续");
        } else {
            System.out.println("[设备注册][过期警告]timeJudge不过关，流程终止");
            return;
        }

        // 将信息注入DeviceInfo和DeviceConfig
        isDone = modelToInfoAndConfig();
        if(isDone) {
            System.out.println("[设备注册]modelToInfoAndConfig已经成功");
        } else {
            System.out.println("[设备注册]modelToInfoAndConfig失败");
            return;
        }
        // 数据库查重
        isDone = checkDuplicate();
        if(isDone) {
            System.out.println("[设备注册]无重复数据存在，可以继续");
        } else {
            System.out.println("[设备注册]插入重复，停止操作");
            return;
        }

        // 操作数据库
        isDone = infoInsert();
        if(isDone) {
            System.out.println("[设备注册]infoInsert已经成功");
        } else {
            System.out.println("[设备注册]infoInsert失败");
            return;
        }
        System.out.println("[设备注册]注册成功，流程结束！");
        return;
    }

    private boolean checkDuplicate() {

        try {
            System.out.println("[设备注册][查重]检查是否重复");
            int result = dcDAO.showCountOfSpecificObject(deviceModel.getIp());

            if(result == 1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean timeJudge(String timestamp) {
        try {
            System.out.println("[设备注册][时间戳检测]时间戳内容为" + timestamp);
            Timestamp ts = new Timestamp(Long.parseLong(timestamp));
            return Tools.timeJudge(ts);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean infoInsert() {
        int a,b;
        a = b = 404; // 表示其数据处理状态

        try {
            a = dcDAO.registerDeviceByDeviceInfo(deviceInfo);
            b = dcDAO.registerDeviceByDeviceConfig(deviceConfig);
            System.out.println("[设备注册]" + deviceModel.getIp() + "插入DeviceInfo   的情况为[" + a + "]");
            System.out.println("[设备注册]" + deviceModel.getIp() + "插入DeviceConfig 的情况为[" + b + "]");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean modelToInfoAndConfig() {
        try {
            String ip, status, command, config, timestamp;
            ip = deviceModel.getIp();
            status = deviceModel.getStatus(); // 这里是要online
            command = deviceModel.getCommand();
            config = deviceModel.getConfig();
            timestamp = deviceModel.getTimestamp();

            System.out.println("[设备注册]" +
                                    "ip是" + ip          + "," +
                                "status是" + status      + "," +
                               "command是" + command     + "," +
                                "config是" + config      + "," +
                             "timestamp是" + timestamp);

            deviceInfo = new DeviceInfo(ip, status, timestamp);
            deviceConfig = new DeviceConfig(ip, config);
            //deviceConfig = new DeviceConfig(ip, null, null, null, null);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
