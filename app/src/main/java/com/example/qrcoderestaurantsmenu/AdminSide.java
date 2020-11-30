package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AdminSide extends AppCompatActivity {

    TextView custName, custPhone, itemName, itemPrice, itemQuantity, orderType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_side);


        custName = findViewById(R.id.custName);
        custPhone = findViewById(R.id.custPhone);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.ItemPrice);
        itemQuantity = findViewById(R.id.quantity);
        orderType = findViewById(R.id.orderType);


        custName.setText(Order.cartItems.get(0).getUserName());
        custPhone.setText(Order.cartItems.get(0).getUserPhoneNumber());
        itemName.setText(Order.cartItems.get(0).getItemName());
        itemPrice.setText(Order.cartItems.get(0).getItemPrice());
        itemQuantity.setText(Order.cartItems.get(0).getQuantity());
        orderType.setText(Order.cartItems.get(0).getOrderType());
    }
}
