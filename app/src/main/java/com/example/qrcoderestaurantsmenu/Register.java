package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    Button signUnBtn, gotoLoginBtn;
    TextInputLayout mEmail, mPassword,mPhoneNo, mFullName;

    private DatabaseReference mDataBaseReference;
    private FirebaseDatabase mRootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUnBtn = (Button) findViewById(R.id.sighUpBtn);
        gotoLoginBtn = findViewById(R.id.goToLoginScreen);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mPhoneNo = findViewById(R.id.et_Contact);
        mFullName = findViewById(R.id.et_name);


        signUnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSignUp();
            }
        });
        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public boolean validateEmail() {
        String val = mEmail.getEditText().getText().toString().trim();
        String pattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
        /*   String pattern = "[a-zA-Z0-9._]+@[a-z]+";*/

        if (val.isEmpty()) {
            mEmail.setError("Required");
            return false;
        } else if (!val.matches(pattern)) {

            mEmail.setError("Invalid Email Pattern");
            return false;
        } else {
            mEmail.setError(null);
            mEmail.setErrorEnabled(false);
            return true;
        }

    }

    public Boolean validatePassword() {
        String val = mPassword.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            mPassword.setError("Required");
            return false;
        } else {
            mPassword.setError(null);
            mPassword.setErrorEnabled(false);
            return true;
        }

    }

    public Boolean validatePhoneNumber() {
        String val = mPhoneNo.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            mPhoneNo.setError("Required");
            return false;
        } else if (val.length() > 11) {
            mPhoneNo.setError("Invalid Phone No");
            return false;
        } else if (val.length() < 11) {
            mPhoneNo.setError("Invalid Phone No");
            return false;
        }else {
            mPhoneNo.setError(null);
            mPhoneNo.setErrorEnabled(false);
            return true;
        }

    }

    public void toSignUp() {

        if (!validateEmail() || !validatePassword() || !validatePhoneNumber()) {
            return;
        }

        String email = mEmail.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        String contact = mPhoneNo.getEditText().getText().toString().trim();
        String name = mFullName.getEditText().getText().toString().trim();


        mRootNode = FirebaseDatabase.getInstance();
        mDataBaseReference = mRootNode.getReference("user");


        SignUpModelClass userSignUp = new SignUpModelClass(email, password, contact,name);
        mDataBaseReference.child(contact).setValue(userSignUp);


        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();


        mEmail.getEditText().getText().clear();
        mPassword.getEditText().getText().clear();
        mPhoneNo.getEditText().getText().clear();
        mFullName.getEditText().getText().clear();

    }
}
