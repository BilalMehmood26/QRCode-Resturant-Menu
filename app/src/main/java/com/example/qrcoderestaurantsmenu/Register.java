package com.example.qrcoderestaurantsmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    Button signUnBtn, gotoLoginBtn;
    TextInputLayout mEmail, mPassword,mPhoneNo, mFullName;

    private DatabaseReference mDataBaseReference;
    private FirebaseDatabase mRootNode;

    FirebaseAuth firebaseAuth;

    String Email,password,userName,contactNumber;
    SignUpModelClass userSignUp;
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


        firebaseAuth=FirebaseAuth.getInstance();
        mDataBaseReference=FirebaseDatabase.getInstance().getReference();

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

         Email = mEmail.getEditText().getText().toString().trim();
         password = mPassword.getEditText().getText().toString().trim();
         contactNumber = mPhoneNo.getEditText().getText().toString().trim();
         userName = mFullName.getEditText().getText().toString().trim();

        userSignUp = new SignUpModelClass(Email, contactNumber,userName);

        firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {

                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    firebaseUser.getUid();

                    getuserValue();
                    mDataBaseReference.child("UserProfile").child(firebaseUser.getUid()).setValue(userSignUp).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Register.this, "Error while Data Save", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "Error while SignUp", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "SignUp Failure"+e, Toast.LENGTH_SHORT).show();
            }
        });

//        mRootNode = FirebaseDatabase.getInstance();
//        mDataBaseReference = mRootNode.getReference("user");
//


//        mDataBaseReference.child(contact).setValue(userSignUp);

        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();

        mPassword.getEditText().getText().clear();
        mPhoneNo.getEditText().getText().clear();


        mEmail.getEditText().getText().clear();

        mFullName.getEditText().getText().clear();

    }

    private void getuserValue() {
        userSignUp.setmEmail(Email);
        userSignUp.setmFullName(userName);
        userSignUp.setmPhoneNo(contactNumber);
    }
}
