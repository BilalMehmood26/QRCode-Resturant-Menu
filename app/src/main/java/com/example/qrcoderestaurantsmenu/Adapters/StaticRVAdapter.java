package com.example.qrcoderestaurantsmenu.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrcoderestaurantsmenu.ModelClasses.StaticRVModel;
import com.example.qrcoderestaurantsmenu.Order;
import com.example.qrcoderestaurantsmenu.R;

import java.util.ArrayList;

public class StaticRVAdapter extends RecyclerView.Adapter<StaticRVAdapter.StaticViewHolder> {


    private ArrayList<StaticRVModel> items;
    public Activity activity;

    public StaticRVAdapter(ArrayList<StaticRVModel> items, Activity activity) {
        this.items = items;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StaticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.static_rv_template,parent,false);
        StaticViewHolder staticViewHolder = new StaticViewHolder(view);
        return staticViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticViewHolder holder, int position) {

    final StaticRVModel currentItem = items.get(position);

    holder.title.setText(currentItem.getmTitle());
    holder.itemImage.setImageResource(currentItem.getmImage());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent  = new Intent(activity, Order.class);
            intent.putExtra("ItemName",currentItem.getmTitle());
            intent.putExtra("ItemDetail",currentItem.getmDetail());
            intent.putExtra("ItemImage",currentItem.getmImage());
            intent.putExtra("price",currentItem.getmPrice());
            activity.startActivity(intent);
        }
    });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView itemImage;
        LinearLayout linearLayout;
        public StaticViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.staticTextView);
            itemImage = itemView.findViewById(R.id.staticImageView);
            linearLayout = itemView.findViewById(R.id.linearlayout_rv);

        }
    }
}
