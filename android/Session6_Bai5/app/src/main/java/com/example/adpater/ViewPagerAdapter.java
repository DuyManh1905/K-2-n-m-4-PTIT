package com.example.adpater;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.model.FragmentAdd;
import com.example.model.FragmentSearch;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentAdd();
            case 1:
                return new FragmentSearch();
            default:
                return new FragmentAdd();
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}
