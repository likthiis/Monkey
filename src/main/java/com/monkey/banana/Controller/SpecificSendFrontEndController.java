package com.monkey.banana.Controller;

import com.monkey.banana.Service.SpecificSendFrontEndService;
import com.monkey.banana.Util.Tools;
import org.springframework.web.bind.annotation.*;

// 指定ip发送信息
@RestController
public class SpecificSendFrontEndController {

    SpecificSendFrontEndService service = new SpecificSendFrontEndService();

    // 从数据库查找在线的设备
    @RequestMapping(value = "/getone/online",method = RequestMethod.GET)
    @ResponseBody
    public String send(@RequestParam("user") String user,
                       @RequestParam("ip"  ) String ip) {
        String data = "No DeviceModel Info";

        // 用户登录判别逻辑
        boolean login = Tools.UserLoginSit(user);
        if(login) {
            data = service.doAll(ip);
        } else {
            data = "ImLogin";
        }
        // 会返回三个信息：
        // 如果存在数据(在线)，就发送一个JSON包
        // 如果查询无结果(离线)，就发送"error_dont_exist"
        // 如果查询失败，就发送"SQLconnection_fail"
        // 如果登录失败，就发送"ImLogin"
        return data;
    }
}
