package com.droid;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by yons on 15/10/15.
 */
public class ParserJson {
    public static JSONObject getJSONfromURL(final String url) {

        // initialize
        JSONObject jsonObject;
        // http post
        InputStream is;
        String result;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
//inputStreamReader iso-8859-1
            BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();
            return new JSONObject(result);

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        return null;
    }

}
