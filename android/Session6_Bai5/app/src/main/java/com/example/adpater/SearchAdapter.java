package com.example.adpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    private List<Cat> mSearch;
    public SearchAdapter(){
        this.mSearch = new ArrayList<>();
    }
    public void setListCat(List<Cat> mList) {
        this.mSearch=mList;
        notifyDataSetChanged();
    }
    public List<Cat> getList(){
        return mSearch;
    }
    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.SearchViewHolder(v);
    }
    @Override
    public
    void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        Cat c = mSearch.get(position);
        if(c==null)
            return;
        else {
            holder.img.setImageResource(c.getImg());
            holder.name.setText(c.getName());
            holder.price.setText(c.getPrice()+"$");
            holder.info.setText(c.getInfo());
        }
    }
    @Override
    public
    int getItemCount() {
        if(mSearch==null)
            return 0;
        return mSearch.size();
    }
    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name, price, info;
        public
        SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.item_img);
            name=itemView.findViewById(R.id.item_name);
            price=itemView.findViewById(R.id.item_price);
            info=itemView.findViewById(R.id.item_info);
        }
    }

}
