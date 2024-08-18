package com.duymanh.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duymanh.fragment.fragment.FragmentA;
import com.duymanh.fragment.fragment.FragmentB;
import com.duymanh.fragment.fragment.FragmentC;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        add(new FragmentA(),"fragA","fa");
    }

    private void add(Fragment fg, String tag, String name){
        transaction = manager.beginTransaction();
        transaction.add(R.id.frame,fg,tag);
        transaction.addToBackStack(name);
        transaction.commit();
    }

    public void addA(View view){
        FragmentA fragmentA = new FragmentA();
        add(fragmentA,"fragA","fa");
    }

    public void addB(View view){
        FragmentB fragmentB = new FragmentB();
        add(fragmentB,"fragB","fb");
    }
    public void addC(View view){
        FragmentC fragmentC = new FragmentC();
        add(fragmentC,"fragC","fc");
    }

    public void remove(Fragment fg, String tag){
        transaction = manager.beginTransaction();
        fg = manager.findFragmentByTag(tag);
        transaction.remove(fg);
        transaction.commit();
    }

    public void removeA(View view){
        FragmentA fragmentA = new FragmentA();
        remove(fragmentA,"fragA");
    }
    public void removeB(View view){
        FragmentB fragmentB = new FragmentB();
        remove(fragmentB,"fragB");
    }
    public void removeC(View view){
        FragmentC fragmentC = new FragmentC();
        remove(fragmentC,"fragC");
    }

    public void back(View view){
        manager.popBackStack();
    }

    public void popA(View view){
        manager.popBackStack("fa",0);
    }

    @Override
    public void onBackPressed() {
        if(manager.getBackStackEntryCount()>0){
            manager.popBackStack();
        }
        super.onBackPressed();
    }

    private void loadFragment(Fragment fragment){
        transaction = manager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}