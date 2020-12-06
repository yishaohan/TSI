package com.ysh.talentshowintro.utils;

import java.sql.Timestamp;
import java.util.Date;

public class StringUtils {
    public static String isNull(String param) {
        if (param == null || "".equals(param)) {
            param = "unknown";
        }
        return param;
    }

    public static Timestamp isNull(Timestamp param) {
        if (param == null) {
            param = new Timestamp(new Date(1900, 0, 1).getTime());
        }
        return param;
    }

}
