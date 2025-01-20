package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mydashboard extends AppCompatActivity {


        private TextView lblGreeting;
        private SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mydashboard);
            lblGreeting = findViewById(R.id.Txtvw);
            View.OnClickListener listnr = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Avvailability.class);
                    startActivity(i);
                }
            };
            TextView Calendarv = (TextView) findViewById(R.id.ctv);
            Calendarv.setOnClickListener(listnr);



            View.OnClickListener listnr2 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Notificationss.class);
                    startActivity(i);
                }
            };
            TextView notificationss = (TextView) findViewById(R.id.Ntv);
            notificationss.setOnClickListener(listnr2);



            View.OnClickListener listnr3 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Bookslot.class);
                    startActivity(i);
                }
            };
            TextView booksl = (TextView) findViewById(R.id.btv);
            booksl.setOnClickListener(listnr3);

            View.OnClickListener listnr4 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), about.class);
                    startActivity(i);
                }
            };
            TextView about = (TextView) findViewById(R.id.atv);
            about.setOnClickListener(listnr4);

            View.OnClickListener listnr5 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Contactus.class);
                    startActivity(i);
                }
            };
            TextView Contentus = (TextView) findViewById(R.id.Utv);
            Contentus.setOnClickListener(listnr5);

            View.OnClickListener listnr6 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Mybookings.class);
                    startActivity(i);
                }
            };
            TextView Mybooking = (TextView) findViewById(R.id.MBtv);
            Mybooking.setOnClickListener(listnr6);

            View.OnClickListener listnr7 = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Manageaccount.class);
                    startActivity(i);
                }
            };
            ImageView settings = (ImageView) findViewById(R.id.menu);
            settings.setOnClickListener(listnr7);



            sharedPreferences = getApplicationContext().getSharedPreferences("userdetails", 0);
            String email = sharedPreferences.getString("email", ""); // Retrieve email from SharedPreferences

            // Retrieve the user's name from the database using the email
            String fName = getUserNameFromDatabase(email);

            // Set personalized greeting
            lblGreeting.setText("Welcome, " + fName + "!");


        }

        private String getUserNameFromDatabase(String email) {
            String userName = "";

            // Connect to the database and retrieve the user's name using the email
            DBConnection dbConnection = new DBConnection();
            Connection con = dbConnection.createConnection();

            if (con != null) {
                try {
                    String query = "SELECT UName FROM Users WHERE email = ?";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, email);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        userName = resultSet.getString("UName");
                    }
                } catch (SQLException e) {
                    Log.e("ERROR: ", e.getMessage());
                } finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        Log.e("ERROR: ", e.getMessage());
                    }
                }
            }

            return userName;
        }
    }
