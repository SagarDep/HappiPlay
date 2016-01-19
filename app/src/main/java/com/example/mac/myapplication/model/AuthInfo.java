package com.example.mac.myapplication.model;

/**
 * Created by happi on 16/1/13.
 */
public class AuthInfo {
    private int ShowMeitu;
    private int login;
    private String sessionKey;
    private int status;
    private UserInfo user;

    public int getShowMeitu() {
        return ShowMeitu;
    }

    public void setShowMeitu(int showMeitu) {
        ShowMeitu = showMeitu;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
                "ShowMeitu=" + ShowMeitu +
                ", login=" + login +
                ", sessionKey='" + sessionKey + '\'' +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
