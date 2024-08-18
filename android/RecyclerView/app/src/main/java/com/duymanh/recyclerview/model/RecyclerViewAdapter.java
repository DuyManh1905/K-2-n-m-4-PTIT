package com.duymanh.recyclerview.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.duymanh.recyclerview.R;

import java.util.List;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.CatViewHolder> {

    private Context context;
    private List<Cat> mList;
    private OnCatItemListener itemListener;

    public void setItemListener(OnCatItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public RecyclerViewAdapter(Context context, List<Cat> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    //bat su kien chi item thu position
    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat cat = mList.get(position);
        holder.img.setImageResource(cat.getImage());
        holder.txt.setText(cat.getName());

//        holder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // xu ly khi tick chuot
//                Toast.makeText(context.getApplicationContext(),cat.getName()+" Hello",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txt;

//        CardView card;

        public CatViewHolder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.img);
            txt = v.findViewById(R.id.txt);
//            card = v.findViewById(R.id.card);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemListener!=null){
                        itemListener.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }
    }


    public interface  OnCatItemListener {
        public  void onItemClick(View view, int position);
    }

}
