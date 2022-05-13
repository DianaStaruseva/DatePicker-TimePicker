package com.example.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtDataTime;
    Button btnTime, btnDate;
    //Calendar - класс для работы с датами. Статический метод getInstance()
        // класса Calendar создаст объект Calendar, инициализированный текущей датой.
    Calendar dateTime  = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtDataTime = findViewById(R.id.txtDataTime);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        //Установка начальных даты и времени
        txtDataTime.setText(DateUtils.formatDateTime(this,dateTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE |DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));

        //Установка выбврв даты
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, monthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                txtDataTime.setText(DateUtils.formatDateTime(getApplicationContext(),
                        dateTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE |DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        btnDate.setOnClickListener(view -> new DatePickerDialog(MainActivity.this, d,
                dateTime.get(Calendar.YEAR),
                dateTime.get(Calendar.MONTH),
                dateTime.get(Calendar.DAY_OF_MONTH)).show());


        //Установка обработчика выбора времени
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateTime.set(Calendar.MINUTE, minute);
                txtDataTime.setText(DateUtils.formatDateTime(getApplicationContext(),
                        dateTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE
                                |DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
            }
        };

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(MainActivity.this, t,
                        dateTime.get(Calendar.HOUR_OF_DAY),
                        dateTime.get(Calendar.MINUTE),true).show();
            }
        });


    }
}