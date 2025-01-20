package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UpcomingBooking extends AppCompatActivity {

    private String currentUsername; // Variable to store the current user's logged-in name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_booking);

        // Assuming you have the user's logged-in name available
        currentUsername = getCurrentUsername();
        // Fetch upcoming bookings for the current user
        List<Booking> bookings = fetchUpcomingBookings(currentUsername);

        // Process the retrieved bookings
        for (Booking booking : bookings) {
            Log.d("UpcomingBooking", "Booking ID: " + booking.getBookingId());
            Log.d("UpcomingBooking", "Car Type: " + booking.getCarType());
            Log.d("UpcomingBooking", "Wash Service Type: " + booking.getWashServiceType());
            Log.d("UpcomingBooking", "Booking Date and Time: " + booking.getBookingDateTime());
            Log.d("UpcomingBooking", "Payment Method: " + booking.getPaymentMethod());
        }
    }

    private List<Booking> fetchUpcomingBookings(String username) {
        List<Booking> bookings = new ArrayList<>();

        DBConnection dbConnection = new DBConnection();
        Connection connection = dbConnection.createConnection();

        if (connection != null) {
            try {
                // Get the UserId of the current user based on their username
                String getUserIdQuery = "SELECT UserId FROM Users WHERE YourColumnName = ?";
                PreparedStatement getUserIdStatement = connection.prepareStatement(getUserIdQuery);
                getUserIdStatement.setString(1, username);
                ResultSet getUserIdResultSet = getUserIdStatement.executeQuery();

                if (getUserIdResultSet.next()) {
                    int userId = getUserIdResultSet.getInt("UserId");

                    // Use the UserId in the join condition to fetch upcoming bookings for the current user
                    String query = "SELECT b.* FROM CarWashBooking AS b INNER JOIN Users AS u ON b.UserId = u.UserId WHERE u.Username = ? AND b.BookingDateTime > CURRENT_TIMESTAMP";
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
                }

                getUserIdResultSet.close();
                getUserIdStatement.close();
            } catch (SQLException e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
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
