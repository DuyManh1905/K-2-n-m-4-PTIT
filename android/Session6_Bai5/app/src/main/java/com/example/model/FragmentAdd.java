package com.example.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MainActivity;
import com.example.R;
import com.example.adpater.RecycleViewAdapter;
import com.example.adpater.SpinnerAdapter;

public class FragmentAdd extends Fragment implements RecycleViewAdapter.CatItemListener {
    RecycleViewAdapter adapter;
    Spinner spinner;
    EditText editName, editPrice, editInfo;
    Button btnAdd, btnUpdate;
    RecyclerView recyclerView;
    private int pcurr;
    private int[] imgs = {R.drawable.cat1, R.drawable.cat2, R.drawable.cat3,
            R.drawable.cat4, R.drawable.cat5, R.drawable.cat6};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price,
                            editInfo.getText().toString());
                    adapter.addCat(cat);
                    adapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {

                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i = spinner.getSelectedItem().toString();
                int img;
                try {
                    img = imgs[Integer.parseInt(i)];
                    double price = Double.parseDouble(editPrice.getText().toString());
                    Cat cat = new Cat(img, editName.getText().toString(), price, editInfo.getText().toString());
                    adapter.update(pcurr,cat);
                    adapter.notifyDataSetChanged();
                    btnUpdate.setVisibility(View.INVISIBLE);
                    btnAdd.setVisibility(View.VISIBLE);
                } catch (NumberFormatException e) {

                }
            }
        });
    }
    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), imgs);
        spinner.setAdapter(adapter);
        editName = view.findViewById(R.id.editName);
        editPrice = view.findViewById(R.id.editPrice);
        editInfo = view.findViewById(R.id.editInfo);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        recyclerView = view.findViewById(R.id.reView);
        btnUpdate.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.VISIBLE);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i = 0; i < imgs.length; i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        spinner.setSelection(p);
        editName.setText(cat.getName());
        editInfo.setText(cat.getInfo());
        editPrice.setText(cat.getPrice()+"");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).list=adapter.getListCat();
    }
}

