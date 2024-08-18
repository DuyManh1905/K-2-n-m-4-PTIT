package com.duymanh.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duymanh.recyclerview.model.Cat;
import com.duymanh.recyclerview.model.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView reView;
    private RecyclerViewAdapter adapter;

    private AutoCompleteTextView completeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reView = findViewById(R.id.reView);
        completeTextView = findViewById(R.id.search);

        List<String> lists = new ArrayList<>();
        lists.add("Ha Noi");
        lists.add("Da Nang");
        lists.add("Thai Binh");
        lists.add("Quang Nam");
        lists.add("Bac Giang");


        ArrayAdapter<String> listTP = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,lists);
        completeTextView.setAdapter(listTP);

        List<Cat> list = new ArrayList<>();

        //add
        list.add(new Cat(R.drawable.cat1,"Cat 1"));
        list.add(new Cat(R.drawable.cat2,"Cat 2"));
        list.add(new Cat(R.drawable.cat3,"Cat 3"));
        list.add(new Cat(R.drawable.cat4,"Cat 4"));
        list.add(new Cat(R.drawable.cat5,"Cat 5"));
        list.add(new Cat(R.drawable.cat6,"Cat 6"));
        //
        adapter = new RecyclerViewAdapter(this,list);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        reView.setLayoutManager(manager);
        reView.setAdapter(adapter);
        adapter.setItemListener(new RecyclerViewAdapter.OnCatItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                //xu ly su kien
                Toast.makeText(MainActivity.this,list.get(position).getName()+" Hello",Toast.LENGTH_LONG).show();
            }
        });

        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

                Toast.makeText(MainActivity.this,selectedText,Toast.LENGTH_SHORT).show();
                completeTextView.setText("");
            }
        });
    }

}