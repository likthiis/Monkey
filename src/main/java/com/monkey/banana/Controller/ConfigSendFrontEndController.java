package com.monkey.banana.Controller;


import com.monkey.banana.Service.ConfigSendFrontEndService;
import com.monkey.banana.Util.Tools;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ConfigSendFrontEndController {

    ConfigSendFrontEndService service = new ConfigSendFrontEndService();

    // 获得特定设备的配置信息
    @RequestMapping(value = "/getone/config",method = RequestMethod.POST)
        @ResponseBody
        public HashMap<String,String> send(@RequestParam("user") String user,
                                           @RequestParam("ip")   String ip) {
            String data = "No HashMap Info";
            HashMap<String,String> map = new HashMap<>();
            // 用户登录判别逻辑
            boolean login = Tools.UserLoginSit(user);
            if(login) {
                data = service.doAll(ip);
                map.put("data",data);
                System.out.println("[控制器][配置信息单独发送]" + map);
            } else {
                map.put("data","ImLogin");
                System.out.println("[控制器][登录检查]登录失败");
            }
            return map;
    }
}
