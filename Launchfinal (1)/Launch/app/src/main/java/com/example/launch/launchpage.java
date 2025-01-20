package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;

public class launchpage extends AppCompatActivity {
    Connection conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchpage);

        Button button = findViewById(R.id.Loginbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mylogin.class);
                startActivity(i);
            }
        });

        Button button2 = findViewById(R.id.Registerbtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mysignup.class);
                startActivity(i);
            }
        });

        try {
            DBConnection connect = new DBConnection();
            conn = connect.createConnection();
            if (conn != null) {
                Toast.makeText(this, "Connection Established", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Connection Not Established", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }
}
