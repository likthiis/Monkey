package com.monkey.banana.Controller;


import com.monkey.banana.Service.AllSendFrontEndService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllSendFrontEndController {

    AllSendFrontEndService service = new AllSendFrontEndService();

    @RequestMapping(value = "/getall",method = RequestMethod.GET)
    @ResponseBody
    public String send() {
        String data = "No DeviceModel Info";
        data = service.doAll();
        return data;
    }
}
