package com.example.projectandroid;

public class items {
    String day;
    int imgID;
    String min, max;

    public items(String day, int imgID, String min, String max) {
        this.day = day;
        this.imgID = imgID;
        this.min = min;
        this.max = max;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
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
}
