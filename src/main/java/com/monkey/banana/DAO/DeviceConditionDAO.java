package com.monkey.banana.DAO;

import com.monkey.banana.Class.*;
import com.monkey.banana.SelfChecking.SelfChecking;
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

    public List<DeviceInfo> showAllDevicesInfo(); // 已经配置

    public SendModel statusShowSpecific(@Param("ip")String id); // 已经配置

    public String configShowSpecific(@Param("ip")String id); // 已经配置

    public int updataDeviceByConfig(@Param("ip")String id,@Param("config")String config); // 已经配置

    public int searchDeviceInfoByIp(@Param("ip")String id); // 已经配置

    public int heartbeatUpdate(@Param("ip")String id, @Param("timestamp")String timestamp); // 已经配置

    public DeviceInfo getDeviceInfoByIp(@Param("ip")String id); // 已经配置


    public int registerUser(@Param("userInfo")UserInfo userInfo); // 已经配置

    public int checkUserExistence(@Param("userInfo")UserInfo userInfo); // 已经配置

    public int withdrawUser(@Param("username")String username); // 已经配置

    public int checkUserExistenceJustById(@Param("username")String username); // 已经配置


    public int updataDeviceByStatus (String id,String status);
    public List<DeviceModel> showAllDevices();
    public DeviceModel showSpecificDevice(String id);
    // 返回SendModel类型


    public int showCount();

    // 看看这个能不能一次执行两条或者两条以上的SQL指令
    public int withdrawDeviceByIp(String id);









    int deleteOneDeviceAllInfo(String id);

    List<SelfChecking> selfChecking();
}
