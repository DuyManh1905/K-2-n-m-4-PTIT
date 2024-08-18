package com.duymanh.datepickerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    private EditText txtTime;
    private EditText txtDate;
    private AutoCompleteTextView autoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
        autoText=findViewById(R.id.autoTxt);

        String[] city ={"Ha noi","Ha Giang", "Hai Phong", "Thai Binh", "Lai Chau"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,city);
        autoText.setAdapter(adapter);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int ch = c.get(Calendar.HOUR);
                int cm = c.get(Calendar.MINUTE);
                TimePickerDialog dialog1 = new TimePickerDialog(DateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int h, int m) {
                                txtTime.setText(h+":"+m);
                            }
                        },ch,cm,true);
                dialog1.show();
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog dialog = new DatePickerDialog(DateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                txtDate.setText("ngay:"+day+", thang:"+(month+1)+", nam"+year);
                            }
                        },year,month,day);
                dialog.show();
            }
        });
    }
}