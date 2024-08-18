package com.duymanh.demointent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.duymanh.demointent.model.Person;

public class SecondActivity extends AppCompatActivity {

    Button bt;
    TextView txt;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bt=findViewById(R.id.bt);
        txt=findViewById(R.id.txt);
        imageView = findViewById(R.id.img);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",22);
        String[] sub = intent.getStringArrayExtra("sub");

        String text = "Ho va ten: "+name+" age: "+age+"\n subjects: ";
        for(String x:sub){
            text+=x+" ";
        }

        Person p = (Person) intent.getSerializableExtra("p");

        txt.setText(text);
        imageView.setImageResource(p.getImage());
    }

}