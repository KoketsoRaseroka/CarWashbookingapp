package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mybookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings);



        View.OnClickListener previousClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PreviousBooking.class);
                startActivity(i);
            }
        };
        Button previousButton = findViewById(R.id.ubookings);
        previousButton.setOnClickListener(previousClickListener);

        View.OnClickListener upcomingClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), UpcomingBooking.class);
                startActivity(i);
            }
        };
        Button upcomingButton = findViewById(R.id.pbookings);
        upcomingButton.setOnClickListener(upcomingClickListener);
    }
}
