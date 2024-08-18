package com.duymanh.demoviewpagertablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.duymanh.demoviewpagertablayout.model.FragmentFood;
import com.duymanh.demoviewpagertablayout.model.FragmentMovie;
import com.duymanh.demoviewpagertablayout.model.FragmentTravel;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int pageNumber;


    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNumber = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentFood ff = new FragmentFood();
                return ff;
            case 1:
                FragmentMovie fm = new FragmentMovie();
                return fm;
            case 2:
                FragmentTravel ft = new FragmentTravel();
                return ft;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageNumber;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "FODD";
            case 1:
                return "MOVIE";
            case 2:
                return "TRAVEL";
        }
        return null;
    }
}
