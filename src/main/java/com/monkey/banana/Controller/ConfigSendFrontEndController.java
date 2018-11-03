package com.monkey.banana.Controller;


import com.monkey.banana.Service.ConfigSendFrontEndService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigSendFrontEndController {

    ConfigSendFrontEndService service = new ConfigSendFrontEndService();

    @RequestMapping(value = "/getone/config",method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestParam("ip") String ip) {
        String data = "No DeviceModel Info";
        data = service.doAll(ip);
        return data;
    }
}
