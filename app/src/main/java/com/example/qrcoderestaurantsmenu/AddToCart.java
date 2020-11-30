package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCart extends AppCompatActivity {

    TextView custName,  custPhone, itemName, itemPrice, itemQuantity, orderType;
    Button placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Toast.makeText(this, ""+Order.cartItems.get(0).getItemName(), Toast.LENGTH_SHORT).show();


        placeOrder = findViewById(R.id.placeOrder);
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

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToCart.this,OrderInvoice.class);
                intent.putExtra("custName", Order.cartItems.get(0).getUserName());
                intent.putExtra("custPhone", Order.cartItems.get(0).getUserPhoneNumber());
                intent.putExtra("itemName", Order.cartItems.get(0).getItemName());
                intent.putExtra("itemPrice", Order.cartItems.get(0).getItemPrice());
                intent.putExtra("quantity", Order.cartItems.get(0).getQuantity());
                intent.putExtra("orderType", Order.cartItems.get(0).getOrderType());
                startActivity(intent);
            }
        });
    }
}
