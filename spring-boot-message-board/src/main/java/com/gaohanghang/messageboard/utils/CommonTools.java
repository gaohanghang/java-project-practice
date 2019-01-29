package com.gaohanghang.messageboard.utils;

import java.sql.Timestamp;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/25 10:52
 */
public class CommonTools {
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        if (str.isEmpty())
            return true;
        if (str == "" || str.equals("")) {
            return true;
        }
        return false;
    }

    public static Timestamp getCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
