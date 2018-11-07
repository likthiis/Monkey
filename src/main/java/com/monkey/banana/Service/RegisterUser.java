package com.monkey.banana.Service;

import com.monkey.banana.Class.UserInfo;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RegisterUser {
    UserInfo userInfo = new UserInfo();
    String resultNotice = "nothing";
    DeviceConditionDAO dao;

    public RegisterUser(String username, String password) {
        userInfo.setUsername(username);
        userInfo.setPassword(password);
    }

    public void doAll() {


        checkExistence();
        doTheRegister();

    }

    private void checkExistence() {
        try {
            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            this.dao = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

            int result = dao.checkUserExistenceJustById(userInfo.getUsername());
            if (result == 1) {
                System.out.println("[用户注册]有重复数据");
                resultNotice = "fail_duplicate";
            } else {
                System.out.println("[用户注册]无重复数据");
                resultNotice = "continue";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultNotice = "fail";
        }
    }

    private void doTheRegister() {
        try {
            if(resultNotice.equals("fail_duplicate") || resultNotice.equals("fail")) return; // 如果查重有问题则停止插入

            int result = dao.registerUser(userInfo);

            if (result == 1) {
                System.out.println("[用户注册]注册成功");
                //resultNotice = "success";
                resultNotice = "success";
            } else {
                System.out.println("[用户注册]注册失败");
                resultNotice = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[用户注册]注册失败");
            resultNotice = "fail";
        }
    }

    public String getResultNotice() {
        return resultNotice;
    }
}
