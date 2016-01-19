package com.example.mac.myapplication.model;

/**
 * Created by happi on 16/1/7.
 */
public class AppSimpleModel {

    private String id;
    private String name;
    private String url;
    private String ico;
    private String views;
    private int hot;
    private int New;
    private String open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getNew() {
        return New;
    }

    public void setNew(int aNew) {
        New = aNew;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "AppSimpleModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", ico='" + ico + '\'' +
                ", views='" + views + '\'' +
                ", hot=" + hot +
                ", New=" + New +
                ", open='" + open + '\'' +
                '}';
    }
}
