package com.example.mac.myapplication.utils;

/**
 * Created by happi on 16/1/7.
 */
public class Util {

    public static String getServiceUrl() {
        int num = 0;//0：内网测试服务器  1：线上地址
        String url = "";
        switch (num) {
            case 0:
                url = "http://poker.upupgame.com/api/mobile/";
                break;
            case 1:
                url = "http://www.happiplay.com/api/mobile/";
                break;
            default:
                break;
        }
        return url;
    }

    public static String getResourceServiceUrl() {
        int num = 0;//0：内网测试服务器  1：线上地址
        String url = "";
        switch (num) {
            case 0:
                url = "http://poker.upupgame.com/api/resources/";
                break;
            case 1:
                url = "http://www.happiplay.com/api/resources/";
                break;
            default:
                break;
        }
        return url;
    }
}
