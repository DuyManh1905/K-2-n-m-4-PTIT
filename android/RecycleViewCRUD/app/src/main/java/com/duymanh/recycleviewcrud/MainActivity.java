package com.duymanh.recycleviewcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.duymanh.recycleviewcrud.model.Cat;
import com.duymanh.recycleviewcrud.model.CatAdapter;
import com.duymanh.recycleviewcrud.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener,SearchView.OnQueryTextListener{

    private Spinner spinner;

    private int[] imgs ={R.drawable.cat1,R.drawable.cat2
            ,R.drawable.cat3,R.drawable.cat4,
            R.drawable.cat5,R.drawable.cat6};

    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private EditText edName, edDesc, edPrice;
    private Button btnAdd, btnUpdate;
    private SearchView searchView;

    private int positionCurr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        adapter = new CatAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        adapter.setClickListener(this);

        searchView.setOnQueryTextListener(this);

        //bat su kien cho nut them
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();
                String name = edName.getText().toString();
                String desc = edDesc.getText().toString();
                String p = edPrice.getText().toString();

                int img=R.drawable.cat1;
                double price = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    //
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.add(cat);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap dung du lieu",Toast.LENGTH_SHORT).show();
                }
                clearInput();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat cat = new Cat();
                String i = spinner.getSelectedItem().toString();
                String name = edName.getText().toString();
                String desc = edDesc.getText().toString();
                String p = edPrice.getText().toString();

                int img=R.drawable.cat1;
                double price = 0;
                try{
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    //
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.update(positionCurr,cat);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap dung du lieu",Toast.LENGTH_SHORT).show();
                }
                clearInput();
            }
        });
    }

    private void initView() {
        spinner=findViewById(R.id.sp_img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        spinner.setAdapter(adapter);
        //
        recyclerView = findViewById(R.id.recyclerView);
        edName = findViewById(R.id.name);
        edDesc = findViewById(R.id.describe);
        edPrice = findViewById(R.id.price);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);

        positionCurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p=0;
        for(int i=0; i<imgs.length; i++){
            if(img==imgs[i]){
                p=i;
                break;
            }
        }
        spinner.setSelection(p);
        edName.setText(cat.getName());
        edDesc.setText(cat.getDescribe());
        edPrice.setText(cat.getPrice().toString());


    }
    public void clearInput(){
        spinner.setSelection(0);
        edName.setText("");
        edDesc.setText("");
        edPrice.setText("");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    public void filter(String s){
        List<Cat> filterlist = new ArrayList<>();
        for(Cat c:adapter.getBackup()){
            if(c.getName().toLowerCase().contains(s.toLowerCase())){
                filterlist.add(c);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }
        else{
            adapter.filterList(filterlist);
        }
    }

}