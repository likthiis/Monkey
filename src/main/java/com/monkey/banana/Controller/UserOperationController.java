package com.monkey.banana.Controller;

import com.monkey.banana.Service.RegisterUser;
import com.monkey.banana.Service.WithdrawUser;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserOperationController {

    @RequestMapping(value = "/user_register",method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        String data = "No Info";

        RegisterUser registerUser = new RegisterUser(username,password);
        registerUser.doAll();
        data = registerUser.getResultNotice();
        // 如果注册成功：success
        // 如果注册失败：fail && No Info
        // 如果账户已经存在：fail_duplicate
        return data;
    }


    @RequestMapping(value = "/user_withdraw",method = RequestMethod.POST)
    @ResponseBody
    public String withdrawUser(@RequestParam("username") String username) {
        String data = "No Info";

        WithdrawUser withdrawUser = new WithdrawUser(username);
        withdrawUser.doAll();
        data = withdrawUser.getResultNotice();
        // 如果注销成功：success
        // 如果注销失败：fail && No Info
        return data;
    }

    // 增加上线下线功能(门禁功能)
}
