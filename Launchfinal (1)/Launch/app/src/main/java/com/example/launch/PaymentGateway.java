package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.launch.DBConnection;
import com.example.launch.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentGateway extends AppCompatActivity {
    private TextView textViewPaymentOptions;
    private Button buttonPayCard, buttonPayCash, buttonPayPaypal;

    private DBConnection dbConnection;
    private String selectedCarType;
    private String selectedWashServiceType;
    private String selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        dbConnection = new DBConnection();

        textViewPaymentOptions = findViewById(R.id.textViewPaymentOptions);
        buttonPayCard = findViewById(R.id.buttonPayCard);
        buttonPayCash = findViewById(R.id.buttonPayCash);
        buttonPayPaypal = findViewById(R.id.buttonPayPaypal);

        buttonPayCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paymentMethod = "Debit/Credit Card";
                addBookingToDatabase(paymentMethod);
                Toast.makeText(PaymentGateway.this, "Payment successful!", Toast.LENGTH_SHORT).show();
                showBookingNotice();
                finish();
            }
        });

        buttonPayCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paymentMethod = "Cash";
                addBookingToDatabase(paymentMethod);
                Toast.makeText(PaymentGateway.this, "Payment successful!", Toast.LENGTH_SHORT).show();
                showBookingNotice();
                finish();
            }
        });

        buttonPayPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paymentMethod = "PayPal";
                addBookingToDatabase(paymentMethod);
                Toast.makeText(PaymentGateway.this, "Payment successful!", Toast.LENGTH_SHORT).show();
                showBookingNotice();
                finish();
            }
        });

        selectedCarType = getIntent().getStringExtra("carType");
        selectedWashServiceType = getIntent().getStringExtra("washServiceType");
        selectedDateTime = getIntent().getStringExtra("bookingDateTime"); // Use "bookingDateTime" key
    }

    private void addBookingToDatabase(String paymentMethod) {
        Connection connection = dbConnection.createConnection();

        if (connection != null) {
            try {
                String query = "INSERT INTO CarWashBooking (CarType, WashServiceType, BookingDateTime, PaymentMethod) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, selectedCarType);
                statement.setString(2, selectedWashServiceType);
                statement.setString(3, selectedDateTime);
                statement.setString(4, paymentMethod);
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int bookingId = generatedKeys.getInt(1);
                        Toast.makeText(PaymentGateway.this, "Booking added to the database. BookingId: " + bookingId, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PaymentGateway.this, "Failed to add booking to the database", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                Toast.makeText(PaymentGateway.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Toast.makeText(PaymentGateway.this, "Error closing connection: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(PaymentGateway.this, "Failed to establish a database connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBookingNotice() {
        Intent intent = new Intent(PaymentGateway.this, BookingNotice.class);
        startActivity(intent);
    }
}
