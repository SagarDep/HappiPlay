package com.example.mac.myapplication.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.example.mac.myapplication.R;
import com.example.mac.myapplication.model.AuthInfo;
import com.example.mac.myapplication.model.ThePhoneInfo;
import com.example.mac.myapplication.net.NetCallBack;
import com.example.mac.myapplication.net.NetClient;
import com.example.mac.myapplication.utils.LogUtils;
import com.example.mac.myapplication.utils.MosApplication;

import java.util.Locale;

/**
 * Created by happi on 16/1/13.
 */
public class LogoActivity extends Activity {

    private NetClient netClient;
    private TelephonyManager tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);


        tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        ThePhoneInfo info = new ThePhoneInfo();
        info.platform = "2";
        //info.deviceid = tm.getDeviceId();
        info.deviceid = "FE5B5452-11A4-4730-880B-FCFD703A39F6";
        info.bundleid = getResources().getString(R.string.bundleid);
        info.locale = Locale.getDefault().getLanguage();// + "-Hans-US";

        LogUtils.e("=====",info.toString());

        getNetClient().registAuth(info);

    }

    public NetClient getNetClient() {
        if (netClient == null) {
            netClient = new NetClient(new BaseNetCallback());
        }
        return netClient;
    }

    public class BaseNetCallback implements NetCallBack {

        @Override
        public void onHttpComplete(int result, String url, Object obj) {
            if (result == NetClient.RESULT_OK) {
                AuthInfo info = (AuthInfo)obj;
                MosApplication.getInstance().setAuthInfo(info);
                Toast.makeText(LogoActivity.this,"注册成功-->" + info.getSessionKey(),Toast.LENGTH_SHORT).show();

               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       Intent intent = new Intent(LogoActivity.this,MainActivity.class);
                       startActivity(intent);
                       finish();
                   }
               },2900);
            } else {
                Toast.makeText(LogoActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
