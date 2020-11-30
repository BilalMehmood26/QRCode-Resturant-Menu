package com.example.qrcoderestaurantsmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout mEmail, mPassword;
    Button signInBtn, signUpBtn;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInBtn = (Button) findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.register);
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();

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


        firebaseAuth.signInWithEmailAndPassword(userEnteredEmail,userEnteredPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                    Log.e("Uid", "onComplete: "+firebaseUser.getUid() );
                    Query query=FirebaseDatabase.getInstance().getReference().child("UserProfile").orderByChild("mEmail").equalTo(userEnteredEmail);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists())
                            {
                                for (DataSnapshot data:dataSnapshot.getChildren())
                                {
                                    String UserName= String.valueOf(data.child("mFullName").getValue());
                                    String  PhoneNumber= String.valueOf(data.child("mPhoneNo").getValue());

                                    Intent intent = new Intent(Login.this, QRScan.class);

                                    intent.putExtra("mPhoneNo",PhoneNumber);
                                    intent.putExtra("mFullName",UserName);

                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(Login.this, "data base not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error While Login"+e, Toast.LENGTH_SHORT).show();
            }
        });

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
//
//        Query checkUser = reference.orderByChild("mEmail").equalTo(userEnteredEmail);

//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//
//                    mEmail.setError(null);
//                    mEmail.setErrorEnabled(false);
//
//
//                        SignUpModelClass signupModelClass = dataSnapshot.getChildren().iterator().next().getValue(SignUpModelClass.class);
//
//
//
//                    if (signupModelClass.getPassword().equals(userEnteredPassword)) {
//
//                        mPassword.setError(null);
//                        mPassword.setErrorEnabled(false);
//

//
//                    }else{
//                        mPassword.setError("Wrong Password");
//                        mPassword.requestFocus();
//                    }
//                }else{
//                    mEmail.setError("Wrong Email");
//                    mEmail.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                Toast.makeText(Login.this, "There is no such data...", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }
}
