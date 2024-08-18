package com.duymanh.demoviewpagertablayout.model;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class HorizontalFlipTransformation implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // Nếu trang đang nằm ở bên trái của màn hình
            view.setAlpha(0f);
        } else if (position <= 0) { // Nếu trang đang hiển thị trên màn hình
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setScaleX(1f);
        } else if (position <= 1) { // Nếu trang đang nằm ở bên phải của màn hình
            view.setAlpha(1 - position);
            view.setTranslationX(pageWidth * -position);
            float scaleFactor = 0.75f + (1 - 0.75f) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // Nếu trang đang nằm ở bên phải của màn hình
            view.setAlpha(0f);
        }
    }
}