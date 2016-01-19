package com.example.mac.myapplication.net;

import com.alibaba.fastjson.JSON;
import com.example.mac.myapplication.model.AppSimpleModel;
import com.example.mac.myapplication.model.AuthInfo;
import com.example.mac.myapplication.model.IncomeModel;
import com.example.mac.myapplication.model.PlazaModel;
import com.example.mac.myapplication.model.ThePhoneInfo;
import com.example.mac.myapplication.utils.LogUtils;
import com.example.mac.myapplication.utils.Util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.net.ssl.SSLContext;

/**
 * Created by happi on 16/1/7.
 */
public class NetClient {

    public static final int LOGIN_ERROR = 401;
    public static final int RESULT_OK = 200;
    public static final int RESULT_ERROR = 701;
    public static final int BACK_ERROR = 702;
    protected AsyncHttpClient client;
    protected NetCallBack callBack;
    public static final String TAG = "NetClient";

    public NetClient(NetCallBack callBack) {
        super();
        this.client = new AsyncHttpClient();
        this.callBack = callBack;
        client.setTimeout(5000);
    }

    /**
     * ...获取收益.../api/mobile/income
     *
     * @param sessionKey 用户登录授权
     */
    public void getIncome(String sessionKey) {
        String url = Util.getServiceUrl() + "income?sessionKey=" + sessionKey;
        get(new MyHttpResponseHandler(url) {
            @Override
            public void onResultOk(JSONObject response) throws JSONException {
                IncomeModel obj = JSON.parseObject(response.toString(), IncomeModel.class);
                callBack.onHttpComplete(RESULT_OK, url, obj);
            }
        });
    }

    /**
     *...广场列表.../api/mobile/snsFind
     * @param pageIndex 当前页
     * @param pageSize 每页记录数
     * @param type 搜索类型
     */
    public void getPlazaList(int pageIndex, int pageSize, int type) {
        String url = Util.getServiceUrl() + "weixinFind?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&type=" + type;
        get(new MyHttpResponseHandler(url) {
            @Override
            public void onResultOk(JSONObject response) throws JSONException {
                PlazaModel obj = JSON.parseObject(response.toString(), PlazaModel.class);
                callBack.onHttpComplete(RESULT_OK, url, obj);
            }
        });

    }

    /**
     * ...百宝箱列表APP.../api/mobile/appstore_games | appstore_apps
     * @param type APP类型
     * @param page 每页记录数
     */
    public void getAppsList(int type,int page) {
        String url = "";
        if (type == 0) {
            url = Util.getServiceUrl() + "appstore_games?page=" + page;
        } else {
            url = Util.getServiceUrl() + "appstore_apps?page=" + page;
        }
        get(new MyHttpResponseHandler(url) {
            @Override
            public void onResultOk(JSONObject response) throws JSONException {
                JSONArray jsonArray = response.getJSONArray("list");
                List<AppSimpleModel> list = JSON.parseArray(jsonArray.toString(), AppSimpleModel.class);
                callBack.onHttpComplete(RESULT_OK,url,list);
            }
        });
    }

    /**
     * ...获取用户主页信息.../api/mobile/profile
     * @param sessionKey    用户登陆授权(可选 如果不传，uid为必传 表示获取别人主页信息)
     * @param uid           用户ID(可选 如果不传 sessionKey为必传 表示获取自己主页信息)
     */
    public void getUserHomeInfo(String sessionKey,String uid) {
        String head = Util.getServiceUrl() + "profile";
        RequestParams params = new RequestParams();
        params.put("sessionKey",sessionKey);
        params.put("uid",uid);

        get(new MyHttpResponseHandler(getCompleteUrl(head,params)) {
            @Override
            public void onResultOk(JSONObject response) throws JSONException {
                callBack.onHttpComplete(RESULT_OK,url,response);
            }
        });
    }




    // ------------------------post去请求--------------------------------
    public void registAuth(ThePhoneInfo info) {
        RequestParams params = new RequestParams();
        params.put("deviceid",info.deviceid);
        params.put("platform",info.platform);
        params.put("locale",info.locale);
        params.put("bundleid",info.bundleid);

        String url = Util.getServiceUrl() + "auth";
        post(params, new MyHttpResponseHandler(url) {
            @Override
            public void onResultOk(JSONObject response) throws JSONException {
                AuthInfo obj = JSON.parseObject(response.toString(), AuthInfo.class);
                callBack.onHttpComplete(RESULT_OK, url, obj);
            }
        });
    }




    //====================get部分=========================
    private void get(MyHttpResponseHandler handler) {
        LogUtils.e(TAG, "get地址==》" + handler.url);
        client.get(handler.url, handler);
    }

    //===================post架构部分=================
    private void post(RequestParams params, MyHttpResponseHandler handler) {
       // get(handler.url, params);
        LogUtils.e(TAG, "post地址==》" + getCompleteUrl(handler.url,params));
        client.post(handler.url, params, handler);
    }

    /**
     * @param uri
     * @param params
     */
    protected String getCompleteUrl(String uri, RequestParams params) {
        String urlWithQueryString = AsyncHttpClient.getUrlWithQueryString(
                false, uri, params);
        return urlWithQueryString;
    }


    protected abstract class MyHttpResponseHandler extends AsyncHttpResponseHandler {
        String url;

        private MyHttpResponseHandler(String url) {
            super();
            this.url = url;
        }

        /**
         * 解析正确的数据 RESULT_OK的情况
         *
         * @param response
         * @throws JSONException
         */
        public abstract void onResultOk(JSONObject response) throws JSONException;

        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);
            LogUtils.e(TAG, "获取到的网络数据=====》\n" + content);

            try {
                JSONObject response = new JSONObject(content);
                int ret = response.getInt("status");
                if (ret == 1) {
                    onResultOk(response);
                } else {
                    String msg = response.getString("msg");
                    callBack.onHttpComplete(BACK_ERROR, url, msg);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                callBack.onHttpComplete(RESULT_ERROR, url, null);
            }
        }

        @Override
        public void onFailure(int statusCode, Throwable error, String content) {
            super.onFailure(statusCode, error, content);
            LogUtils.e(TAG, "服务器返回错误码：=====》" + statusCode + "<====>" + error);
            if (statusCode == 401) {
                callBack.onHttpComplete(LOGIN_ERROR, url, null);
            } else {
                callBack.onHttpComplete(RESULT_ERROR, url, null);
            }
            error.printStackTrace();
        }
    }

}
