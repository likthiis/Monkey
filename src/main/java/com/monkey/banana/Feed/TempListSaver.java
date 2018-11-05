package com.monkey.banana.Feed;

import com.google.gson.Gson;
import com.monkey.banana.Class.DeviceInfo;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Class.SendModel;
import com.monkey.banana.Service.AllSendFrontEndService;
import com.monkey.banana.Service.ConfigSendFrontEndService;
import com.monkey.banana.Util.Tools;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempListSaver extends Thread {
    public static Map<String, SendModel> sendModels = new HashMap<String, SendModel>();

    private Gson gson = new Gson();


    /* 这里要加个判断，需要判断是否过期后才能注入内存表 */
    /* 这里要加个判断，需要判断是否是心跳包或者注册表后才能注入内存表 */
    /* 如果是删除指令包则删除内存表的条目 */
    public void invokeForCheck(String kafkaInfo) {
        DeviceModel deviceModel = gson.fromJson(kafkaInfo,DeviceModel.class);
        String command = deviceModel.getCommand();

        //register withdraw heartbeat config_change

        if(command.equals("register")) {
            System.out.println("[内存表存储处理]注册新设备");
            SendModel s = tranDeviceModelToSendModel(deviceModel);
            sendModels.put(s.getIp(),s);
        }

        if(command.equals("withdraw")) {
            System.out.println("[内存表存储处理]注销新设备");
            String ip = deviceModel.getIp();
            SendModel s = sendModels.get(ip);
            if(s != null) {
                sendModels.remove(ip);
            } else {
                System.out.println("[内存表存储处理]设备不存在");
            }
        }

        if(command.equals("heartbeat")) {
            System.out.println("[内存表存储处理]心跳包检测");
            Timestamp t = new Timestamp(Long.parseLong(deviceModel.getTimestamp()));
            if(Tools.timeJudge(t)) {
                System.out.println("[内存表存储处理]心跳包合格");
                sendModels.remove(deviceModel.getIp());
                sendModels.put(deviceModel.getIp(),tranDeviceModelToSendModel(deviceModel));
            } else {
                System.out.println("[内存表存储处理]心跳包不合格");
                sendModels.remove(deviceModel.getIp());
            }
        }

        if(command.equals("config_change")) {
            System.out.println("[内存表存储处理]配置更改");
            sendModels.remove(deviceModel.getIp());
            sendModels.put(deviceModel.getIp(),tranDeviceModelToSendModel(deviceModel));
        }
    }

    private SendModel tranDeviceModelToSendModel(DeviceModel d) {
        SendModel s = new SendModel(d.getIp(),d.getStatus(),d.getTimestamp(),d.getConfig());
        return s;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("[内存表存储处理]数据库检查");
                AllSendFrontEndService service = new AllSendFrontEndService();
                service.doAll();
                List<DeviceInfo> ds = service.getDeviceInfos();
                for (DeviceInfo d : ds) {
                    SendModel s = sendModels.get(d.getIp());
                    if (s != null) {
                        Timestamp t = new Timestamp(Long.parseLong(s.getTimestamp()));
                        if (!Tools.timeJudge(t)) {
                            System.out.println("[内存表存储处理][删除数据]ip为" + d.getIp() + "的设备需要删除");
                            sendModels.remove(d.getIp());
                        }
                    } else {
                        sendModels.put(d.getIp(), tranDeviceInfoToSendModel(d));
                    }
                }
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SendModel tranDeviceInfoToSendModel(DeviceInfo d) {
        // 查询数据库数据
        ConfigSendFrontEndService service = new ConfigSendFrontEndService();
        String config = service.doAll(d.getIp());

        SendModel s = new SendModel(d.getIp(),d.getStatus(),d.getTimestamp(),config);
        return s;
    }
}
