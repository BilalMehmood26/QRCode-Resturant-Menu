package com.example.qrcoderestaurantsmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrcoderestaurantsmenu.Adapters.RVAdapter;
import com.example.qrcoderestaurantsmenu.Interface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Menu extends AppCompatActivity {


    public static TextView resultTV;
    public RVAdapter adapter;
    RecyclerView  recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        resultTV = findViewById(R.id.textView3);
        recyclerView = findViewById(R.id.recyclerView);


        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayList<RVModelClass> arrayList = new ArrayList<>();
        arrayList.add(new RVModelClass("Burger Rowra F-11",
                "Double-XL beef shami burger & fries","99",R.drawable.pungburger));
        arrayList.add(new RVModelClass("Chicken Biryani",
                "Double-XL beef shami burger & fries","5",R.drawable.biryani));
        arrayList.add(new RVModelClass("Fettuccine Alfredo",
                "Double-XL beef shami burger & fries","6",R.drawable.pasta));
        arrayList.add(new RVModelClass("Pepperoni Pizza",
                "Double-XL beef shami burger & fries","9",R.drawable.pizza));
        arrayList.add(new RVModelClass("Zinger Burger Recipe",
                        "Spicy Zinger Burger Recipe (KFC Style) with fries","8",R.drawable.zinger));
        arrayList.add(new RVModelClass("Classic Club Sandwich",
                "Double-XL beef Sandwich & Cold Drink","10",R.drawable.sandwich));



        adapter = new RVAdapter(arrayList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

   /* public void loadableRV(){
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));
        items.add(new RVModelClass("Burger Rowra F-11","Double-XL beef shami burger & fries"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRVAdapter = new RecycerViewAdapter(recyclerView,this,items);
        recyclerView.setAdapter(mRVAdapter);
        mRVAdapter.setLoadmore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=10){
                    items.add(null);
                    mRVAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            mRVAdapter.notifyItemRemoved(items.size());

                            int index  = items.size();
                            int end = index+10;

                            for (int i = index; i<end ;i++){
                                String name = UUID.randomUUID().toString();
                                String itemDetail = UUID.randomUUID().toString();


                                RVModelClass rvItem = new RVModelClass(name,itemDetail);
                                items.add(rvItem);
                            }
                            mRVAdapter.notifyDataSetChanged();
                            mRVAdapter.setLoaded();
                        }
                    },4000);

                } else{
                    Toast.makeText(Menu.this, "Data Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/
}
