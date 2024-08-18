package com.example.adpater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MainActivity;
import com.example.R;
import com.example.model.Cat;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CatViewHolder> {

    private List<Cat> mList;
    private CatItemListener itemListener;
    public RecycleViewAdapter(){
        this.mList = new ArrayList<>();
    }
    public void setItemListener(CatItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public Cat getItem(int position){
        return mList.get(position);
    }
    public List<Cat> getListCat()
    {
        return mList;
    }
    @NonNull
    @Override
    public
    CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);
        return new CatViewHolder(v);
    }
    @Override
    public
    void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        Cat c = mList.get(position);
        if(c==null)
            return;
        else {
            holder.img.setImageResource(c.getImg());
            holder.name.setText(c.getName());
            holder.price.setText(c.getPrice()+"$");
            holder.info.setText(c.getInfo());
            holder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Thong bao xoa");
                    builder.setMessage("Ban co chac chan muon xoa "+c.getName()+" nay khong?");
                    builder.setIcon(R.drawable.remove);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            });
        }
    }
    @Override
    public
    int getItemCount() {
        if(mList==null)
            return 0;
        return mList.size();
    }
    public void addCat(Cat cat) {
        mList.add(cat);
        notifyDataSetChanged();
    }
    public void update(int position, Cat cat){
        mList.set(position,cat);
        notifyDataSetChanged();
    }
    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView name, price, info;
        Button btnRemove;
        public
        CatViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.item_img);
            name=view.findViewById(R.id.item_name);
            price=view.findViewById(R.id.item_price);
            info=view.findViewById(R.id.item_info);
            btnRemove=view.findViewById(R.id.item_btnRemove);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface CatItemListener{
        void onItemClick(View view,int position);
    }
}
