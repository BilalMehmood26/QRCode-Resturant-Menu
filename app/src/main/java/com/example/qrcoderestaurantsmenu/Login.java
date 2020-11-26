package com.example.qrcoderestaurantsmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout mEmail, mPassword;
    Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInBtn = (Button) findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.register);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public Boolean validateEmail() {
        String val = mEmail.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            mEmail.setError("Field cannot be empty");
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
            mPassword.setError("Field cannot be empty");
            return false;
        } else {
            mPassword.setError(null);
            mPassword.setErrorEnabled(false);
            return true;
        }

    }

    public void userLogin() {
        if (!validateEmail() || !validatePassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {
        final String userEnteredEmail = mEmail.getEditText().getText().toString().trim();
        final String userEnteredPassword = mPassword.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

        Query checkUser = reference.orderByChild("mEmail").equalTo(userEnteredEmail);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    mEmail.setError(null);
                    mEmail.setErrorEnabled(false);


                    SignUpModelClass signupModelClass = dataSnapshot.getChildren().iterator().next().getValue(SignUpModelClass.class);



                    if (signupModelClass.getPassword().equals(userEnteredPassword)) {

                        mPassword.setError(null);
                        mPassword.setErrorEnabled(false);

                        Intent intent = new Intent(Login.this, QRScan.class);

                        intent.putExtra("mEmail",signupModelClass.getEmail() );
                        intent.putExtra("mPassword",signupModelClass.getPassword() );
                        intent.putExtra("mPhoneNo",signupModelClass.getPhoneNo());
                        intent.putExtra("mFullName",signupModelClass.getmFullName());

                        startActivity(intent);
                        finish();

                    }else{
                        mPassword.setError("Wrong Password");
                        mPassword.requestFocus();
                    }
                }else{
                    mEmail.setError("Wrong Email");
                    mEmail.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Login.this, "There is no such data...", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
