package com.example.qrcoderestaurantsmenu;

public class RVModelClass {

    public String itemName,itemDetail, price;
    public int mImageID;


    public RVModelClass(String itemName, String itemDetail, String price, int imageId) {
        this.itemName = itemName;
        this.itemDetail = itemDetail;
        this.price = price;
        this.mImageID = imageId;

    }

    public String getItemName() {
        return itemName;
    }

    public String getPrice() {
        return price;
    }

    public int getmImageID() {
        return mImageID;
    }

    public String getItemDetail() {
        return itemDetail;
    }
}
