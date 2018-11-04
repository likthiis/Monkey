package com.monkey.banana.Service;

import com.monkey.banana.Class.DeviceInfo;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.DAO.DeviceConditionDAO;
import com.monkey.banana.Util.Tools;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;

public class HeartbeatTransactionService {
    DeviceModel deviceModel;
    DeviceConditionDAO dcDAO;

    public HeartbeatTransactionService(DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    public void doAll() {
        System.out.println("[心跳传送事务]ip为" + deviceModel.getIp() + "的设备已经开始流程");

        boolean isDone = false;

        // 查阅spring.xml配置文件，导入DAO接口形成的类
        ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
        dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

        // 验证流程
        // 验证机器登录情况
        isDone = ipvaChecking();
        if(isDone) {
            System.out.println("[心跳传送事务]ip验证环节结束");

            // 验证心跳包时限情况
            isDone = hbvaChecking();
            if(isDone) {
                System.out.println("[心跳传送事务]心跳包时限验证流程结束");

                // 心跳包信息更新
                isDone = heartbeatUpdate();
                if(isDone) {
                    System.out.println("[心跳传送事务]心跳包信息更新流程结束");
                } else {
                    System.out.println("[心跳传送事务]心跳包信息更新流程失败，删除原ip信息");

                    // 自动注销机器
                    isDone = withdrawDevice();
                    if(isDone) {
                        System.out.println("[心跳传送事务]info与config条目删除完毕");
                    } else {
                        System.out.println("[心跳传送事务]info与config条目删除失败");
                    }
                }

            } else {
                System.out.println("[心跳传送事务]心跳包时限验证失败，终止");
            }

        } else {
            System.out.println("[心跳传送事务]ip验证失败，终止");
        }





    }

    private boolean withdrawDevice() {
        try {
            // 这里假设dcDAO能操作多条语句
            int result = dcDAO.withdrawDeviceByIp(deviceModel.getIp());
            if(result == 1) return true;
            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean heartbeatUpdate() {
        try {
            int result = 0;
            result = dcDAO.heartbeatUpdate(deviceModel.getIp(), deviceModel.getTimestamp());
            if(result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean hbvaChecking() {
        try {

            boolean isVa = false;
            // 首先需要将最近的一次数据返回来
            String ip = deviceModel.getIp();
            DeviceInfo deviceInfo = (DeviceInfo)dcDAO.getDeviceInfoByIp(ip);

            // 设置两个时间戳
            String lastTimeStamp = deviceInfo.getTimestamp();

            String nowTimeStamp = deviceModel.getTimestamp();



            Timestamp last = new Timestamp(Long.parseLong(lastTimeStamp));
            Timestamp now = new Timestamp(Long.parseLong(nowTimeStamp));

            isVa = Tools.timeJudge(last,now,10000);

            return isVa;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean ipvaChecking() {
        // 这里也许是错的，查询无法返回一个判断结果，而是返回整个内容
        int isExist = 0;

        try {
            String ip = deviceModel.getIp();
            isExist = dcDAO.searchDeviceInfoByIp(ip);
            if(isExist == 1) return true;
            else return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
