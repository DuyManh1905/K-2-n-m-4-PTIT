package com.duymanh.nhom4_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity2 extends AppCompatActivity {

    private CheckBox ck1,ck2,ck3;
    private RadioButton rMale, rFemale;
    private RatingBar rt;
    private Spinner sp;
    private Button bt;

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ck1 = findViewById(R.id.ckIphone);
        ck2 = findViewById(R.id.ckAndroid);
        ck3 = findViewById(R.id.ckWindow);
        rMale = findViewById(R.id.male);
        rFemale = findViewById(R.id.female);
        rt = findViewById(R.id.rt);
        sp = findViewById(R.id.sp);
        bt = findViewById(R.id.btSubmit);
        txt = findViewById(R.id.txt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st="Cong nghe: ";
                if(ck1.isChecked()){
                    st+="Iphone";
                }
                if(ck2.isChecked()){
                    st+="Adroid";
                }
                if(ck1.isChecked()){
                    st+="Window Phone";
                }
                //
                st+="\n Gioi Tinh: ";
                if(rMale.isChecked()){
                    st+="Nam";
                }
                else{
                    st+="Nu";
                }
                //
                st+="\nRating:"+rt.getRating();

                txt.setText(st);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}