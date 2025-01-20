package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bookingsummary extends AppCompatActivity {
    private TextView textViewBookingSummary;

    private String selectedCarType;
    private String selectedWashServiceType;
    private String selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingsummary);

        textViewBookingSummary = findViewById(R.id.textViewBookingSummary);

        selectedCarType = getIntent().getStringExtra("carType"); // Use correct key name to retrieve car type
        selectedWashServiceType = getIntent().getStringExtra("washServiceType"); // Use correct key name to retrieve wash service type
        selectedDateTime = getIntent().getStringExtra("bookingDateTime");



        // Create the booking summary text
        String bookingSummary = "Car Type: " + selectedCarType +
                "\nWash Service Type: " + selectedWashServiceType +
                "\nBooking Date & Time: " + selectedDateTime;

        textViewBookingSummary.setText(bookingSummary);

        // Handle the button click event to proceed to payment
        Button btnProceedToPayment = findViewById(R.id.btnProceedToPayment);
        btnProceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PaymentGatewayActivity
                Intent intent = new Intent(Bookingsummary.this,PaymentGateway.class);
                // Pass the booking details to the PaymentGatewayActivity
                intent.putExtra("carType", selectedCarType);
                intent.putExtra("washServiceType", selectedWashServiceType);
                intent.putExtra("bookingDateTime", selectedDateTime);

                startActivity(intent);
            }
        });
    }
}
