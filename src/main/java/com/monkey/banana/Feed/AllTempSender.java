package com.monkey.banana.Feed;


import com.google.gson.Gson;
import com.monkey.banana.Class.SendModel;
import org.apache.kafka.common.network.Send;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// 前端请求
@RestController
public class AllTempSender {



    Gson gson = new Gson();

    @RequestMapping(value = "/now",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String,String> send() {
        String data = "No DeviceModel Info";

        HashMap<String,String> map = new HashMap<>();
        System.out.println("[内存表信息发送]" + map);

        Map<String, SendModel> tmp = TempListSaver.sendModels;

        Iterator entries = tmp.entrySet().iterator();

        while(entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            SendModel s = (SendModel) entry.getValue();
            String JSON = gson.toJson(s);
            map.put(s.getIp(),JSON);
        }

        return map;
    }
}

