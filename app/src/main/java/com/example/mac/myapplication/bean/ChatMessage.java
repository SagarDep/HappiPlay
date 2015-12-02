package com.example.mac.myapplication.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yons on 15/12/2.
 */
public class ChatMessage {
    private String userName;
    private String chatContent;
    private String timeStamp;
    private CHAT_VIEW_TYPE viewType;

    public enum CHAT_VIEW_TYPE {
        CHAT_RECEVIED,
        CHAT_SENT
    }

    public ChatMessage(JSONObject chatData) {
        try {
            chatContent = chatData.getString("chatContent");
            userName = chatData.getString("userName");
            timeStamp = chatData.getString("timeStamp");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CHAT_VIEW_TYPE getViewType() {
        return viewType;
    }

    public void setViewType(CHAT_VIEW_TYPE viewType) {
        this.viewType = viewType;
    }

    public ChatMessage(String userName, String chatContent, String timeStamp, CHAT_VIEW_TYPE viewType) {
        this.userName = userName;
        this.chatContent = chatContent;
        this.timeStamp = timeStamp;
        this.viewType = viewType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
