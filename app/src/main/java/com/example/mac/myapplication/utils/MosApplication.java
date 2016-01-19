package com.example.mac.myapplication.utils;

import android.app.Activity;
import android.app.Application;

import com.example.mac.myapplication.model.AuthInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by happi on 16/1/13.
 */
public class MosApplication extends Application {


    //用户登录信息
    public AuthInfo authInfo;

    /*
    Activity 列表
     */
    public List<Activity> actlist = new ArrayList<>();

    /*
    单列模式中获取唯一的MosApplication实例
     */
    private static MosApplication instance;
    public static MosApplication getInstance() {
        if (null == instance) {
            instance = new MosApplication();
        }
        return instance;
    }

    /* ############################################ */
	/* ################ 生命週期 ##################### */
	/* ############################################ */
    @Override
    public void onCreate() {
        super.onCreate();
    }


    /**
     * Add Activity to the Container
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        actlist.add(activity);
    }

    /**
     * 关闭所有的activity
     */
    public void closeactivity() {
        for (int i = 0; i < actlist.size(); i++) {
            if (actlist.get(i) != null && !actlist.get(i).isFinishing()) {
                actlist.get(i).finish();
            }
        }
        System.exit(0);
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }
}
