package com.example.mac.myapplication.model;

/**
 * Created by happi on 16/1/7.
 */
public class IncomeSimpleModel {
    private float game;
    private float img;
    private float invite;
    private float login;
    private float money;
    private String ymd;

    public float getGame() {
        return game;
    }

    public void setGame(float game) {
        this.game = game;
    }

    public float getImg() {
        return img;
    }

    public void setImg(float img) {
        this.img = img;
    }

    public float getInvite() {
        return invite;
    }

    public void setInvite(float invite) {
        this.invite = invite;
    }

    public float getLogin() {
        return login;
    }

    public void setLogin(float login) {
        this.login = login;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    @Override
    public String toString() {
        return "IncomeSimpleModel{" +
                "game=" + game +
                ", img=" + img +
                ", invite=" + invite +
                ", login=" + login +
                ", money=" + money +
                ", ymd='" + ymd + '\'' +
                '}';
    }
}
