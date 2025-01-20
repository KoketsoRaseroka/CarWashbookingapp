package com.example.launch;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Avvailability extends AppCompatActivity {
    private CalendarView calendarView;
    private TextView reservedSlotsTextView;
    private DBConnection dbConnection;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avvailability);

        dbConnection = new DBConnection();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        calendarView = findViewById(R.id.calenderview);
        reservedSlotsTextView = findViewById(R.id.reservedslotstxtv1);

        // Set the calendar view listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                selectedDate.set(Calendar.HOUR_OF_DAY, 0);
                selectedDate.set(Calendar.MINUTE, 0);
                selectedDate.set(Calendar.SECOND, 0);
                selectedDate.set(Calendar.MILLISECOND, 0);

                String selectedDateString = dateFormatter.format(selectedDate.getTime());

                // Query the database for reserved timeslots on the selected date
                Connection connection = dbConnection.createConnection();
                if (connection != null) {
                    try {
                        String query = "SELECT CONVERT(VARCHAR(5), BookingDateTime, 108) AS BookingTime FROM CarWashBooking WHERE CONVERT(DATE, BookingDateTime) = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, selectedDateString);
                        ResultSet resultSet = statement.executeQuery();

                        StringBuilder reservedSlots = new StringBuilder();
                        while (resultSet.next()) {
                            String bookingTime = resultSet.getString("BookingTime");
                            reservedSlots.append(bookingTime).append("\n");
                        }

                        // Set the reserved slots text in the TextView
                        reservedSlotsTextView.setText(reservedSlots.toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
