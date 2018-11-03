package com.monkey.banana.Controller;

import com.monkey.banana.Service.SpecificSendFrontEndService;
import org.springframework.web.bind.annotation.*;

// 指定ip发送信息
@RestController
public class SpecificSendFrontEndController {

    SpecificSendFrontEndService service = new SpecificSendFrontEndService();

    @RequestMapping(value = "/getone/online",method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestParam("ip") String ip) {
        String data = "No DeviceModel Info";
        data = service.doAll(ip);
        // 会返回三个信息：
        // 如果存在数据(在线)，就发送一个JSON包
        // 如果查询无结果(离线)，就发送"error_dont_exist"
        // 如果查询失败，就发送"SQLconnection_fail"
        return data;
    }
}
