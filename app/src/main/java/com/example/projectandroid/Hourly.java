package com.example.projectandroid;

public class Hourly {
    String hour, min, max;
    int imgID;

    public Hourly(String hour, String min, String max, int imgID) {
        this.hour = hour;
        this.min = min;
        this.max = max;
        this.imgID = imgID;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
