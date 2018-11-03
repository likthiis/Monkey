package com.monkey.banana.Service;

import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WithdrawDeviceService {
    DeviceModel deviceModel;
    DeviceConditionDAO dcDAO;

    public WithdrawDeviceService(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
        System.out.println("[机器注销]ip为" + this.deviceModel.getIp() + "的注销服务层启动！");
    }

    public void doAll() {
        boolean isDone = false;

        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        // 不判断有效性：当断必断
        // 数据库中删除数据
        isDone = withdrawInDB();
        if(isDone) {
            System.out.println("[机器注销]注销操作成功！");
        } else {
            System.out.println("[机器注销]注销操作失败！");
        }
    }

    private boolean withdrawInDB() {
        // 这里假设一个DAO函数能够操作多个SQL语句
        try {
            int isDone = dcDAO.withdrawDeviceByIp(deviceModel.getIp());
            System.out.println("[机器注销]" + deviceModel.getIp() + "注销操作完毕！结果为[" + isDone + "]");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
