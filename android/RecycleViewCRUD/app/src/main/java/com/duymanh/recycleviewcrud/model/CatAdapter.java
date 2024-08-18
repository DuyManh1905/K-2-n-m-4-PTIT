package com.duymanh.recycleviewcrud.model;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duymanh.recycleviewcrud.R;

import java.util.ArrayList;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private Context context;
    private List<Cat> mList;

    private List<Cat> searchList;

    private CatItemListener mListener;

    public CatAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
        searchList = new ArrayList<>();
    }

    public List<Cat> getBackup(){
        return searchList;
    }

    public void setClickListener (CatItemListener mListener){
        this.mListener = mListener;
    }

    public Cat getItem(int posittion) {
        return mList.get(posittion);
    }

    public void filterList(List<Cat> filterList) {
        mList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cat cat = mList.get(position);
        if (cat==null){
            return;
        }
        holder.imageView.setImageResource(cat.getImg());
        holder.tvName.setText(cat.getName());
        holder.tvDescribe.setText(cat.getDescribe());
        holder.tvPrice.setText(cat.getPrice().toString());


        //xu ly khi click nut xoa
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa!");
                builder.setMessage("Bạn có chắc muốn xóa "+cat.getName()+" không?");
                builder.setIcon(R.drawable.baseline_add_alert_24);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchList.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context,"Xoa thanh cong!",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    public void add(Cat cat){
        searchList.add(cat);
        mList.add(cat);
        notifyDataSetChanged();
    }

    public void update(int posittion, Cat cat){
        searchList.set(posittion,cat);
        mList.set(posittion,cat);
        notifyDataSetChanged();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView tvName,tvDescribe,tvPrice;
        private Button btnRemove;

        public CatViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDescribe = view.findViewById(R.id.txtDescribe);
            tvPrice = view.findViewById(R.id.txtPrice);
            btnRemove = view.findViewById(R.id.btnRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener!=null){
                mListener.onItemClick(v,getAdapterPosition());
            }
        }
    }


    public interface CatItemListener {
        void onItemClick(View view, int position);
    }
}
