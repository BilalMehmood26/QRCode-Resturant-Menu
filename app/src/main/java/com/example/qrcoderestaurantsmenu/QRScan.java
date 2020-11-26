package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QRScan extends AppCompatActivity {

    /*public static TextView resultTV;*/
    Button scanBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_tool_bar);
        setSupportActionBar(toolbar);

        /*resultTV = findViewById(R.id.result);*/

        scanBtn = findViewById(R.id.scan_qr_code);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QRScan.this,Scanner.class);
                startActivity(intent);
            }
        });
    }
}
