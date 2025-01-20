package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mylogin extends AppCompatActivity {
    EditText edtEmailAddress, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);

        edtEmailAddress = findViewById(R.id.editTxt1);
        edtPassword = findViewById(R.id.editTxt2);
        btnLogin = findViewById(R.id.Loginbutton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtEmailAddress.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Validate login
                boolean isValidLogin = checkLogin(username, password);

                if (isValidLogin) {
                    // Login successful
                    Toast.makeText(Mylogin.this, "OTP successfully sent!", Toast.LENGTH_SHORT).show();
                    EditText edtEmailAddress = findViewById(R.id.editTxt1);
                    String email = edtEmailAddress.getText().toString().trim();

                    // Save the email in SharedPreferences
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userdetails", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString( "email", email);
                    editor.apply();

                    // Show personalized greeting in the dashboard
                    Intent intent = new Intent(getApplicationContext(), Mydashboard.class);
                    startActivity(intent);

                } else {
                    // Login failed
                    Toast.makeText(Mylogin.this, "Invalid username or password. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView signUpTxt = findViewById(R.id.textV4);
        signUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mysignup.class);
                startActivity(i);
            }
        });

        TextView forgotPtxt = findViewById(R.id.textView2);
        forgotPtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), forgot.class);
                startActivity(i);
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        DBConnection dbConnection = new DBConnection();
        Connection con = dbConnection.createConnection();

        if (con != null) {
            try {
                // Execute the SQL query to validate the login
                String query = "SELECT * FROM Users WHERE email = ? AND UPassword = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                // Check if a matching user was found
                if (resultSet.next()) {
                    // User exists and credentials are valid
                    return true;
                }
            } catch (SQLException e) {
                Log.e("ERROR: ", e.getMessage());
            } finally {
                // Close the database resources
                try {
                    con.close();
                } catch (SQLException e) {
                    Log.e("ERROR: ", e.getMessage());
                }
            }
        }

        return false;
    }
}
