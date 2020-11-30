package com.example.qrcoderestaurantsmenu.ModelClasses;

public class AddToCart {
    String quantity,userName, userPhoneNumber, itemName, itemPrice, orderType;

    public AddToCart(String quantity, String userName, String userPhoneNumber, String itemName, String itemPrice, String orderType) {
        this.quantity = quantity;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.orderType = orderType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
