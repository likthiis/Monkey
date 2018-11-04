package com.monkey.banana.SelfChecking;

import com.monkey.banana.DAO.DeviceConditionDAO;
import com.monkey.banana.Util.Tools;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.util.List;

public class SelfCheckingThread extends Thread{
    private DeviceConditionDAO dcDAO;
    private List<SelfChecking> devicesInfo;

    // 自检数据库内的信息，自动剔除失联设备
    @Override
    public void run() {
        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");



        while (true) {
            try {
                System.out.println("[自检处理]自检开始");
                devicesInfo = dcDAO.selfChecking(); // 仅返回带有ip和timestamp的数据集
                for (SelfChecking a : devicesInfo) {
                    System.out.println("[自检处理]ip为" + a.getIp() + "的时间戳为" + a.getTimestamp());
                    boolean judge = Tools.timeJudge(new Timestamp(Long.parseLong(a.getTimestamp())));
                    System.out.println("[自检处理]结果为[" + (!judge) + "]");
                    if (!judge) { // 当过期时

                        System.out.println("[自检处理]数据库中ip为" + a.getIp() + "的设备过期，开始删除");
                        int result = dcDAO.withdrawDeviceByIp(a.getIp());
                        if (result == 1) {
                            System.out.println("[自检处理]数据库中ip为" + a.getIp() + "的设备过期，数据删除");
                        } else {
                            System.out.println("[自检处理]删除失败");
                        }
                    }
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("[自检处理]异常抛出");
            }
        }
    }

}
