package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreviousBooking extends AppCompatActivity {

    private String currentUsername; // Variable to store the current user's logged-in name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_booking);

        // Assuming you have the user's logged-in name available
        currentUsername = getCurrentUsername(); // Replace with actual code to fetch the current user's logged-in name

        // Fetch previous bookings for the current user
        List<Booking> bookings = fetchPreviousBookings(currentUsername);

        if (bookings.isEmpty()) {
            Toast.makeText(this, "No previous bookings found.", Toast.LENGTH_SHORT).show();
        } else {
            // Process the retrieved bookings
            for (Booking booking : bookings) {
                Log.d("PreviousBooking", "Booking ID: " + booking.getBookingId());
                Log.d("PreviousBooking", "Car Type: " + booking.getCarType());
                Log.d("PreviousBooking", "Wash Service Type: " + booking.getWashServiceType());
                Log.d("PreviousBooking", "Booking Date and Time: " + booking.getBookingDateTime());
                Log.d("PreviousBooking", "Payment Method: " + booking.getPaymentMethod());
            }
        }
    }

    private List<Booking> fetchPreviousBookings(String username) {
        List<Booking> bookings = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.createConnection();

        if (connection != null) {
            try {
                String query = "SELECT * FROM CarWashBooking WHERE Username = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int bookingId = resultSet.getInt("BookingId");
                    String carType = resultSet.getString("CarType");
                    String washServiceType = resultSet.getString("WashServiceType");
                    String bookingDateTime = resultSet.getString("BookingDateTime");
                    String paymentMethod = resultSet.getString("PaymentMethod");

                    Booking booking = new Booking(bookingId, carType, washServiceType, bookingDateTime, paymentMethod);
                    bookings.add(booking);
                }

                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error closing connection: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Failed to establish a database connection", Toast.LENGTH_SHORT).show();
        }

        return bookings;
    }

    private String getCurrentUsername() {
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        String username = sessionManager.getUsername(); // Retrieve the username from the session manager

        return username;
    }
}
