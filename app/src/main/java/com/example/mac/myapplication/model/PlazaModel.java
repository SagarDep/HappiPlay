package com.example.mac.myapplication.model;

import java.util.List;

/**
 * Created by happi on 16/1/7.
 */
public class PlazaModel {
    private int pageIndex;
    private int pageSize;
    private int pageTotal;
    private List<PlazaSimpleModel> snsFind;
    private int status;
    private int total;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public List<PlazaSimpleModel> getSnsFind() {
        return snsFind;
    }

    public void setSnsFind(List<PlazaSimpleModel> snsFind) {
        this.snsFind = snsFind;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PlazaModel{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", snsFind=" + snsFind +
                ", status=" + status +
                ", total=" + total +
                '}';
    }
}
