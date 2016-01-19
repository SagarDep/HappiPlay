package com.example.mac.myapplication.net;

/**
 * Created by happi on 16/1/7.
 */
public interface NetCallBack {

    /**
     * 网络请求结束后的回调方法
     * @param result 返回结果标示 可能的值参看{@link NetClient}
     * @param url   请求参数
     * @param obj
     */
    void onHttpComplete(int result,String url,Object obj);
}
