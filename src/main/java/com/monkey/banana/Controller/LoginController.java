package com.monkey.banana.Controller;

import com.monkey.banana.Service.LoginUserService;
import com.monkey.banana.Service.OfflineService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    // 两个功能：登录 下线

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        String data = "No Info";
        LoginUserService service = new LoginUserService(username,password);
        service.doAll();
        data = service.getResult();
        //login_success 表示成功， 任何别的字符串都是失败
        return data;
    }

    @RequestMapping(value = "/offline",method = RequestMethod.POST)
    @ResponseBody
    public String offline(@RequestParam("username") String username) {
        String data = "No Info";
        OfflineService service = new OfflineService(username);
        service.doAll();
        data = service.getResult();
        //off_success 表示成功，任何别的字符串都是失败
        return data;
    }
}
