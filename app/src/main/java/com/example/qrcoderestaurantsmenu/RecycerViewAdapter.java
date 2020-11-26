package com.example.qrcoderestaurantsmenu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrcoderestaurantsmenu.Interface.LoadMore;

import java.util.List;

class LoadingViewHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;
    public LoadingViewHolder(@NonNull View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}

class itemViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;
    public itemViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textViewItemName);
    }
}

public class RecycerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;

    LoadMore loadmore;
    boolean isLoading;
    Activity activity;
    List<RVModelClass> items;
    int visibleThreshold = 5;
    int lastVisibleitem, totalItemCount;

    public RecycerViewAdapter(RecyclerView recyclerView, Activity activity, List<RVModelClass> item) {
        this.activity = activity;
        this.items = item;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleitem = linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleitem+visibleThreshold)){
                    if(loadmore != null){
                        loadmore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadmore (LoadMore loadmore){
        this.loadmore = loadmore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(activity).inflate(R.layout.menu_template,parent,false);
            return new LoadingViewHolder(view);
        }else if(viewType==VIEW_TYPE_LOADING){

            View view = LayoutInflater.from(activity).inflate(R.layout.rv_progressbar,parent,false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof itemViewHolder){
            RVModelClass item = items.get(position);
            itemViewHolder viewHolder  = (itemViewHolder) holder;
            viewHolder.textView.setText(items.get(position).getItemName());
        }else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setLoaded(){
        isLoading = false;
    }

}
