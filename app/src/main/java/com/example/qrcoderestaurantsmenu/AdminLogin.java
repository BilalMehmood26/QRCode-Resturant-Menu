package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class AdminLogin extends AppCompatActivity {

    TextInputLayout mEmail, mPassword;
    Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        signInBtn = (Button) findViewById(R.id.loginButton);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isUser();
            }
        });


    }



    public  void isUser(){

        Intent intent = new Intent(AdminLogin.this, AdminSide.class);
        startActivity(intent);
        finish();

    }
}
