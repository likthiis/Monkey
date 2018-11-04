package com.monkey.banana.Util;

import java.sql.Timestamp;

public class Tools {
    private static final long offset = 30000; //1000为一秒，这里刚好一分钟


    // true代表着发过来的包在时间限制之内，false代表着已经过期
    public static boolean timeJudge(Timestamp timeFromDevice) {

        Timestamp now = new Timestamp(System.currentTimeMillis() - offset);

        // 比较时间，并返回判断结果
        // 警告！after和before的使用要注意，避免判断错误
        // A.before(B)为真意味着A比B要早(A < B)
        return !timeFromDevice.before(now);
    }

    // 检查数据库记录的时间与目前时间之间的差距，并作出决定
    public static boolean timeJudge(Timestamp last,Timestamp now,int myOffset) {
        long nowTimeStamp = now.getTime();

        // 逻辑：心跳包最新时间减去偏移量后，必须早于记录的时间！！！这样才是有效的！！！！！！
        // 如果是有效的，那么before将获得false
        return !last.before(new Timestamp(nowTimeStamp - myOffset));
    }
}
