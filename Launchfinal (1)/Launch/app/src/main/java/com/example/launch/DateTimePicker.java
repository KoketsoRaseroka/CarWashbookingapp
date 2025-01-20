package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimePicker extends AppCompatActivity {
    private Button buttonSelectDateTime;
    private Calendar calendar;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault());

        buttonSelectDateTime = findViewById(R.id.buttonNext);
        buttonSelectDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        final int year = currentDate.get(Calendar.YEAR);
        final int month = currentDate.get(Calendar.MONTH);
        final int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DateTimePicker.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        calendar.set(Calendar.YEAR, selectedYear);
                        calendar.set(Calendar.MONTH, selectedMonth);
                        calendar.set(Calendar.DAY_OF_MONTH, selectedDay);

                        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
                        int currentMinute = currentDate.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(DateTimePicker.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        calendar.set(Calendar.MINUTE, minute);

                                        String selectedDateTime = dateFormatter.format(calendar.getTime());

                                        Intent intent = getIntent();
                                        String selectedCarType = intent.getStringExtra("carType");
                                        String selectedWashServiceType = intent.getStringExtra("washServiceType");

                                        intent = new Intent(DateTimePicker.this, Bookingsummary.class);
                                        intent.putExtra("carType", selectedCarType);
                                        intent.putExtra("washServiceType", selectedWashServiceType);
                                        intent.putExtra("bookingDateTime", selectedDateTime);
                                        startActivity(intent);
                                    }
                                }, currentHour, currentMinute, false);

                        timePickerDialog.show();
                    }
                }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

}
