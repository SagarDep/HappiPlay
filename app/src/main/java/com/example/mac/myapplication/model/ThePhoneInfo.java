package com.example.mac.myapplication.model;

/**
 * Created by happi on 16/1/13.
 */
public class ThePhoneInfo {

    public String deviceid;
    public String platform;
    public String locale;
    public String bundleid;

    @Override
    public String toString() {
        return "ThePhoneInfo{" +
                "deviceid='" + deviceid + '\'' +
                ", platform='" + platform + '\'' +
                ", locale='" + locale + '\'' +
                ", bundleid='" + bundleid + '\'' +
                '}';
    }
}
