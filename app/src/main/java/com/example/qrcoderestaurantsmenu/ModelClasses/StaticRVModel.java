package com.example.qrcoderestaurantsmenu.ModelClasses;

public class StaticRVModel {

    public int mImage;
    public String mTitle, mDetail,mPrice;

    public StaticRVModel(int mImage, String mTitle) {
        this.mImage = mImage;
        this.mTitle = mTitle;
    }

    public StaticRVModel(int mImage, String mTitle, String mDetail, String mPrice) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mDetail = mDetail;
        this.mPrice = mPrice;
    }

    public String getmPrice() {
        return mPrice;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDetail() {
        return mDetail;
    }
}

