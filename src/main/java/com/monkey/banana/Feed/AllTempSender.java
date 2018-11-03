package com.monkey.banana.Feed;


import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// 前端请求
@RestController
public class AllTempSender {

    //这里还没写

    Gson gson = new Gson();

    @RequestMapping(value = "/now",method = RequestMethod.GET)
    @ResponseBody
    public String send() {
        String data = "No DeviceModel Info";
        data = gson.toJson(TempListSaver.deviceModels);
        // 这里要截取TempListSaver进程的数据
        return data;
    }
}
