package com.example.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MainActivity;
import com.example.R;
import com.example.adpater.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    SearchAdapter adapter;
    SearchView search;
    RecyclerView reViewSearch;
    private List<Cat> mlist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    @Override
    public
    void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search=view.findViewById(R.id.search);
        reViewSearch=view.findViewById(R.id.reViewSearch);
        adapter = new SearchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        reViewSearch.setLayoutManager(manager);
        reViewSearch.setAdapter(adapter);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return false;
            }
            private void filter(String s) {
                List<Cat> filterlist=new ArrayList<>();
                for(Cat i:mlist){
                    if(i.getName().toLowerCase().contains(s.toLowerCase())){
                        filterlist.add(i);
                    }
                }
                if(filterlist.isEmpty()){
                    Toast.makeText(getContext(),"No data found",Toast.LENGTH_SHORT).show();
                }else{
                    adapter.setListCat(filterlist);
                }
            }
        });
    }
    public void onResume() {
        super.onResume();
        mlist = ((MainActivity)getActivity()).list;
    }
}

