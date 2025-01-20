package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Mysignup extends AppCompatActivity {
    EditText email, mobile, fName, password, cPassword;
    Button btnSignUp;

    LinearLayout lvParent;

    Connection con = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysignup);

        TextView textView3 = findViewById(R.id.textView4);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Mylogin.class);
                startActivity(i);
            }
        });

        email = findViewById(R.id.editTxt1);
        fName = findViewById(R.id.editTxt2);
        mobile = findViewById(R.id.editTxt3);
        password = findViewById(R.id.editTxt4);
        cPassword = findViewById(R.id.editTxt5);
        btnSignUp = findViewById(R.id.button1);

        lvParent = findViewById(R.id.lvparent);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString().trim();
                String _fName = fName.getText().toString().trim();
                String _mobile = mobile.getText().toString().trim();
                String _password = password.getText().toString().trim();

                if (_email.isEmpty() || _fName.isEmpty() || _mobile.isEmpty() || _password.isEmpty()) {
                    Toast.makeText(Mysignup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        DBConnection connect = new DBConnection();
                        con = connect.createConnection();

                        if (con != null) {
                            String query = "INSERT INTO Users (UName, email, Unumber, UPassword) VALUES (?, ?, ?, ?)";
                            PreparedStatement pst = con.prepareStatement(query);
                            pst.setString(1, _fName);
                            pst.setString(2, _email);
                            pst.setString(3, _mobile);
                            pst.setString(4, _password);
                            int result = pst.executeUpdate();

                            if (result == 1) {
                                Toast.makeText(Mysignup.this, "Record Inserted", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(Mysignup.this, "Record Not Inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.e("Error: ", e.getMessage());
                    }
                }
            }

        });
    }



}
