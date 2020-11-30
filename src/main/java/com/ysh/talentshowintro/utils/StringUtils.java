package com.ysh.talentshowintro.utils;

public class StringUtils {
    public static String isNull(String param) {
        if (param == null || "".equals(param)) {
            param = "unkonw";
        }
        return param;
    }
}
