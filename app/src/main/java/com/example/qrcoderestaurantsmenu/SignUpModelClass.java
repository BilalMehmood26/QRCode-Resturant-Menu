package com.example.qrcoderestaurantsmenu;

public class SignUpModelClass {
    public String mEmail, mPassword, mPhoneNo, mFullName;
    public SignUpModelClass() {
    }

    public SignUpModelClass(String mEmail, String mPassword, String mPhoneNo, String mFullName) {
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mPhoneNo = mPhoneNo;
        this.mFullName = mFullName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getPhoneNo() {
        return mPhoneNo;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public void setPhoneNo(String mPhoneNo) {
        this.mPhoneNo = mPhoneNo;
    }
}
