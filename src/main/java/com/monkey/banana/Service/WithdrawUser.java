package com.monkey.banana.Service;

import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WithdrawUser {
    String notice = "nothing";
    String username;
    DeviceConditionDAO dao;

    public WithdrawUser(String username) {
        this.username = username;
    }

    public void doAll() {
        checkExistance();
        deleteUserInfo();
    }

    private void checkExistance() {
        try {
            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            this.dao = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");

            int result = dao.checkUserExistenceJustById(username);

            if (result == 1) {
                notice = "continue";
            } else {
                notice = "fail_inExistence";
            }
        } catch (Exception e) {
            e.printStackTrace();
            notice = "fail_process";
        }
    }

    private void deleteUserInfo() {
        try {
            int result = dao.withdrawUser(username);
            if (result == 1) {
                //notice = "success";
                notice = "login.html";
            } else {
                notice = "fail_delete";
            }
        } catch (Exception e) {
            e.printStackTrace();
            notice = "fail_process";
        }
    }

    public String getResultNotice() {
        return this.notice;
    }
}
