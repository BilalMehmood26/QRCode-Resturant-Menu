package com.example.qrcoderestaurantsmenu;

public class SignUpModelClass {
    public String mEmail, mPhoneNo, mFullName;

    public SignUpModelClass(String mEmail, String mPhoneNo, String mFullName) {
        this.mEmail = mEmail;
        this.mPhoneNo = mPhoneNo;
        this.mFullName = mFullName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhoneNo() {
        return mPhoneNo;
    }

    public void setmPhoneNo(String mPhoneNo) {
        this.mPhoneNo = mPhoneNo;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }
}
