package com.monkey.banana.Service;

import com.monkey.banana.Class.UserInfo;
import com.monkey.banana.DAO.DeviceConditionDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoginUserService {
    private String result;
    private String username;
    private String password;
    private DeviceConditionDAO dcDAO;

    public LoginUserService(String username, String password) {
        this.username = username;
        this.password = password;
        this.result = "do nothing";
    }

    public void doAll() {
        // 拿到用户名和密码后，先查验数据库是否存在该条目

        checkUserExistence();

        // 如果存在，则对登录登记表进行插入处理

        insertLoginTable();
    }



    private void checkUserExistence() {
        try {
            // 查阅spring.xml配置文件，导入DAO接口形成的类
            ApplicationContext atc = new ClassPathXmlApplicationContext("spring.xml");
            dcDAO = (DeviceConditionDAO) atc.getBean("deviceConditionDAO");
            int result = dcDAO.checkLoginInfoVaild(new UserInfo(username, password));
            if (result == 1) {
                this.result = "info_vaild";
            } else {
                this.result = "info_invaild";
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.result = "catch_exception";
        }
    }

    private void insertLoginTable() {
        if(this.result.equals("info_vaild")) {
            try {
                int re = dcDAO.loginUser(username);
                if (re == 1) {
                    this.result = "login_success";
                } else {
                    this.result = "login_fail";
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.result = "catch_exception";
            }
        } else {
            this.result = "login_fail";
        }
    }

    public String getResult() {
        return result;
    }
}
