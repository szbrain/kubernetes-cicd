package com.koolearn.club.utils;

import java.util.UUID;

/**
 * Created by lilong01 on 2018/3/5.
 */
public class ClubUtils {

    public static String uuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
