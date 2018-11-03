package com.monkey.banana.DAO;

import com.monkey.banana.Class.DeviceConfig;
import com.monkey.banana.Class.DeviceInfo;
import com.monkey.banana.Class.DeviceModel;
import com.monkey.banana.Class.SendModel;
import com.monkey.banana.SelfChecking.SelfCheckingThread;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceConditionDAO {
    // We need KafkaReceiveService to cooperate for
    // judging the status

    // 查重
    public int cnkiQuery(String ip);

    // registerDeviceByDeviceInfo:将DeviceInfo的所有信息都存入DeviceBaseInfo
    public int registerDeviceByDeviceInfo(@Param("baseinfo")DeviceInfo d); // 已经配置
    // registerDeviceByDeviceConfig:将DeviceConfig的所有信息都存入DeviceConfigInfo
    public int registerDeviceByDeviceConfig(@Param("configinfo")DeviceConfig d); // 已经配置

    public int withdrawDevice(@Param("ip")String id); // 已经配置

    public int showCountOfSpecificObject(@Param("ip")String id); // 已经配置
    public int updataDeviceByConfig(String id,String config);
    public int updataDeviceByStatus (String id,String status);
    public List<DeviceModel> showAllDevices();
    public DeviceModel showSpecificDevice(String id);
    // 返回SendModel类型
    public SendModel statusShowSpecific(String id);
    public String configShowSpecific(String id);
    public int showCount();

    // 看看这个能不能一次执行两条或者两条以上的SQL指令
    public int withdrawDeviceByIp(String id);


    public int heartbeatUpdate(String id, String timestamp);
    public DeviceInfo getDeviceInfoByIp(String id);
    public int searchDeviceInfoByIp(String id);

    public List<DeviceInfo> showAllDevicesInfo();


    int deleteOneDeviceAllInfo(String id);

    List<SelfCheckingThread.SelfChecking> selfChecking();


}
