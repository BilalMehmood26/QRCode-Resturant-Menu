package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

class OrderSummary{
    public String customerName, phone,foodItem,orderType;
    public int quantity,price;

    public OrderSummary(String customerName, String phone, String foodItem, String orderType, int quantity, int price) {
        this.customerName = customerName;
        this.phone = phone;
        this.foodItem = foodItem;
        this.orderType = orderType;
        this.quantity = quantity;
        this.price = price;
    }

    public int priceCalculate(){
        return quantity * price;
    }
}

public class Order extends AppCompatActivity {

    TextView item, itemDetail, price, custFullName, mcustPhoneNo;
    ImageView itemImage;
    String foodItem,foodItemDetail,foodItemPrice,custName,custPhoneNo = "";
    int quantity = 1;
    String OrderType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_tool_bar);
        setSupportActionBar(toolbar);
        //-----upEnable Button----//
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        foodItem = getIntent().getStringExtra("ItemName");
        foodItemDetail = getIntent().getStringExtra("ItemDetail");
        foodItemPrice = getIntent().getStringExtra("price");
        final int imageResource = getIntent().getIntExtra("ItemImage", 0);
        custName = getIntent().getStringExtra("mFullName");
        custPhoneNo = getIntent().getStringExtra("mPhoneNo");


        item = findViewById(R.id.item_title);
        item.setText(foodItem);

        itemDetail = findViewById(R.id.textView2);
        itemDetail.setText(foodItemDetail);

        price = findViewById(R.id.textViewPrice);
        price.setText(foodItemPrice);

        custFullName = findViewById(R.id.cust_name);
        custFullName.setText(custName);

        mcustPhoneNo = findViewById(R.id.TVphone_no);
        mcustPhoneNo.setText(custPhoneNo);

        itemImage = findViewById(R.id.itemImageView);
        itemImage.setImageResource(imageResource);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 20) {
            Toast toast = Toast.makeText(this, "You can't order more then 20 cups", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast toast = Toast.makeText(this, "You cannot order less then 1 cup", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.dineIn_RadioBtn:
                if (checked)
                    // Pirates are the best
                    this.OrderType = "Dine In";
                    break;
            case R.id.takeAway_Radiobtn:
                if (checked)
                    // Ninjas rule
                    this.OrderType = "Take Away";
                    break;
        }
    }

    public void generateSummary(View v){

        int price = Integer.parseInt(foodItemPrice.toString());
        OrderSummary orderSummary = new OrderSummary(custName,custPhoneNo,foodItem,OrderType,quantity,price);
        // Create the text message with a string
        String number = "+923315877212";  // The number on which you want to send SMS
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
        sendIntent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(orderSummary));
        startActivity(sendIntent);
    }

    private String createOrderSummary(OrderSummary summary) {
        String orderSummary = "Customer Name: " + summary.customerName + "\n";
        orderSummary += "Food Item : " + summary.foodItem;
        orderSummary += "\nOrder Type : " + summary.orderType;
        orderSummary += "\nQuantity : " + summary.quantity;
        orderSummary += "\nTotal Price:$ " + summary.priceCalculate();
        orderSummary += "\nCustomer ID: 01";
        return orderSummary;
    }
}
