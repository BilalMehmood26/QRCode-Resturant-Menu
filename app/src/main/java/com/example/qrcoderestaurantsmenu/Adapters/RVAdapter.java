package com.example.qrcoderestaurantsmenu.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrcoderestaurantsmenu.Order;
import com.example.qrcoderestaurantsmenu.R;
import com.example.qrcoderestaurantsmenu.RVModelClass;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {


    private ArrayList<RVModelClass> arrayList;
    Activity activity;


    public RVAdapter(ArrayList<RVModelClass> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item, itemDetail,price;
        ImageView itemImage;
        ConstraintLayout cardViewLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.textViewItemName);
            itemDetail = itemView.findViewById(R.id.itemDetailTV);
            itemImage = itemView.findViewById(R.id.itemIV);
            price = itemView.findViewById(R.id.item_price);
            cardViewLayout = itemView.findViewById(R.id.menuItemconstraintLayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.menu_template, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final RVModelClass modelClass = arrayList.get(position);
        holder.item.setText(modelClass.getItemName());
        holder.itemDetail.setText(modelClass.getItemDetail());
        holder.itemImage.setImageResource(modelClass.getmImageID());
        holder.price.setText(modelClass.getPrice());

        holder.cardViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Order.class);
                intent.putExtra("ItemName", modelClass.getItemName());
                intent.putExtra("ItemDetail", modelClass.getItemDetail());
                intent.putExtra("ItemImage", modelClass.getmImageID());
                intent.putExtra("price",modelClass.getPrice());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
