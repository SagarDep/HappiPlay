package com.example.mac.myapplication.model;

import java.util.List;

/**
 * Created by happi on 16/1/7.
 */
public class IncomeModel {

    private int status;
    private float all;
    private float remainder;
    private float today;
    private String invitecode;
    private List<IncomeSimpleModel> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getAll() {
        return all;
    }

    public void setAll(float all) {
        this.all = all;
    }

    public float getRemainder() {
        return remainder;
    }

    public void setRemainder(float remainder) {
        this.remainder = remainder;
    }

    public float getToday() {
        return today;
    }

    public void setToday(float today) {
        this.today = today;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public List<IncomeSimpleModel> getList() {
        return list;
    }

    public void setList(List<IncomeSimpleModel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "IncomeModel{" +
                "status=" + status +
                ", all=" + all +
                ", remainder=" + remainder +
                ", today=" + today +
                ", invitecode='" + invitecode + '\'' +
                ", list=" + list +
                '}';
    }
}

