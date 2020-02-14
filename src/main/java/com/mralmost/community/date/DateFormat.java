package com.mralmost.community.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Lxj
 * @Package com.mralmost.community
 * @Description TODO 设置时间的格式
 * @date: 2020/2/14
 */
public class DateFormat {

    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
