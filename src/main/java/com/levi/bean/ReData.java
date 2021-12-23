package com.levi.bean;

public class ReData {
    private int d_id;
    private String d_poster;
    private String d_name;
    private double d_price;
    private String d_download;

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getD_poster() {
        return d_poster;
    }

    public void setD_poster(String d_poster) {
        this.d_poster = d_poster;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public double getD_price() {
        return d_price;
    }

    public void setD_price(double d_price) {
        this.d_price = d_price;
    }

    public String getD_download() {
        return d_download;
    }

    public void setD_download(String d_download) {
        this.d_download = d_download;
    }

    @Override
    public String toString() {
        return "ReData{" +
                "d_id=" + d_id +
                ", d_poster='" + d_poster + '\'' +
                ", d_name='" + d_name + '\'' +
                ", d_price=" + d_price +
                ", d_download='" + d_download + '\'' +
                '}';
    }
}
