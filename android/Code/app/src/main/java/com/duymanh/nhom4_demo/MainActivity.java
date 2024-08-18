package com.duymanh.nhom4_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText tn1,tn2;
    private TextView txtKQ;
    private Button btAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGUI();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sn1 = tn1.getText().toString();
                String sn2 = tn2.getText().toString();
                double n1,n2;
                try{
                    n1 = Double.parseDouble(sn1);
                    n2 = Double.parseDouble(sn2);
                    txtKQ.setText("Tong: "+(n1+n2));
                }catch (NumberFormatException e){
                    System.out.println(e);
                    Toast.makeText(MainActivity.this,"de nghi nhap so",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initGUI() {
        tn1 = findViewById(R.id.num1);
        tn2 = findViewById(R.id.num2);
        txtKQ = findViewById(R.id.txtKQ);
        btAdd = findViewById(R.id.bt);
    }

    public void tong(View view) {
        String sn1 = tn1.getText().toString();
        String sn2 = tn2.getText().toString();
        double n1,n2;
        try{
            n1 = Double.parseDouble(sn1);
            n2 = Double.parseDouble(sn2);
            txtKQ.setText("tong: "+(n1+n2));
        }catch (NumberFormatException e){
            System.out.println(e);
            Toast.makeText(this,"de nghi nhap so",Toast.LENGTH_SHORT).show();
        }
    }


//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        final EditText textBox = (EditText) findViewById(R.id.editText);
//        CharSequence userText = textBox.getText();
//        outState.putCharSequence("savedText", userText);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        final EditText textBox = (EditText) findViewById(R.id.editText);
//        CharSequence userText = savedInstanceState.getCharSequence("savedText");
//        textBox.setText(userText+"!");
//    }
}