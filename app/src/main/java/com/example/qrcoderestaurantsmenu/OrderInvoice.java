package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class OrderInvoice extends AppCompatActivity {

    TextView custName, custPhone, itemName, itemPrice, itemQuantity, orderType,totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_invoice);

        String customerName = getIntent().getStringExtra("custName");
        String customerPhone = getIntent().getStringExtra("custPhone");
        String foodItem = getIntent().getStringExtra("itemName");
        String foodItemPrice = getIntent().getStringExtra("itemPrice");
        String foodItemQuantity = getIntent().getStringExtra("quantity");
        String foodOrderType = getIntent().getStringExtra("orderType");

        custName = findViewById(R.id.custName);
        custName.setText(customerName);

        custPhone = findViewById(R.id.custPhone);
        custPhone.setText(customerPhone);

        itemName = findViewById(R.id.itemName);
        itemName.setText(foodItem);

        itemPrice = findViewById(R.id.ItemPrice);
        itemPrice.setText(foodItemPrice);

        itemQuantity = findViewById(R.id.quantity);
        itemQuantity.setText(foodItemQuantity);

        orderType = findViewById(R.id.orderType);
        orderType.setText(foodOrderType);

        totalPrice = findViewById(R.id.textView8);
        totalPrice.setText(foodItemPrice);



    }
}
