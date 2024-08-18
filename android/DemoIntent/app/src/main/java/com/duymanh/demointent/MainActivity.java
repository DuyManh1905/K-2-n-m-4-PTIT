package com.duymanh.demointent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duymanh.demointent.model.Person;

public class MainActivity extends AppCompatActivity {

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.btnOpen);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,SecondActivity.class);
                String name = "Le duy Manh";
                intent.putExtra("name",name);
                int age=22;
                intent.putExtra("age",age);
                String[] subject ={"LT HDT", "LT UDTBDD", "LT HDV","Toan RR"};
                intent.putExtra("sub",subject);

                Person p = new Person(R.drawable.aot,"Taylor Swift");
                intent.putExtra("p",p);

                startActivity(intent);
            }
        });
    }
}