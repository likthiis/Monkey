package com.monkey.banana.Controller;


import com.monkey.banana.Service.AllSendFrontEndService;
import com.monkey.banana.Util.Tools;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllSendFrontEndController {

    AllSendFrontEndService service = new AllSendFrontEndService();

    // 获得所有信息
    @ResponseBody
    @RequestMapping(value = "/getall",method = RequestMethod.GET)
    public String send(@RequestParam(value="user") String user) {
        String data = "No DeviceModel Info";
        // 用户登录判别逻辑
        boolean login = Tools.UserLoginSit(user);
        if(login) {
            data = service.doAll();
        } else {
            data = "ImLogin";
        }
        return data;
    }
}
