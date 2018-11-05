package com.monkey.banana.Controller;


import com.monkey.banana.Service.ConfigSendFrontEndService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ConfigSendFrontEndController {

    ConfigSendFrontEndService service = new ConfigSendFrontEndService();

    // 获得特定设备的配置信息
    @RequestMapping(value = "/getone/config",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> send(@RequestParam("ip") String ip) {
        String data = "No DeviceModel Info";
        HashMap<String,String> map = new HashMap<>();
        data = service.doAll(ip);
        map.put("data",data);
        System.out.println("[控制器][配置信息单独发送]" + map);
        return map;
    }
}
