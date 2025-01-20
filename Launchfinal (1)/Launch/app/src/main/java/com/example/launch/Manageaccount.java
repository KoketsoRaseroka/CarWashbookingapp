

package com.example.launch;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.security.AlgorithmParameterGenerator;

public class Manageaccount extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageaccount);

        TextView lblGreeting = findViewById(R.id.ztv);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userdetails", 0);
        String fname = sharedPreferences.getString("Fname", ""); // Retrieve Fname from the database

        lblGreeting.setText( fname );


        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertbox();

            }

            private void openAlertbox() {

                AlertDialog.Builder builder = new AlertDialog.Builder(Manageaccount.this);
                builder.setTitle("Cancel");
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Manageaccount.this, launchpage.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "You logged out!", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }


        };

    }

    View.OnClickListener listnr2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openAlertbox();

        }

        private void openAlertbox() {

            AlertDialog.Builder builder = new AlertDialog.Builder(Manageaccount.this);
            builder.setTitle("Payment Method");
            builder.setMessage("Which payment would you prefer?");
            builder.setCancelable(false);
            builder.setPositiveButton("Card", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Manageaccount.this, paymentoption.class));
                }
            });
            builder.setNegativeButton("Cash", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
    };


    View.OnClickListener listnr3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openAlertbox();

        }

        private void openAlertbox() {

            AlertDialog.Builder builder = new AlertDialog.Builder(Manageaccount.this);
            builder.setTitle("Personal Details");
            builder.setMessage("Enter your details?");
            builder.setCancelable(false);
            builder.setPositiveButton("Name:Frank", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Manageaccount.this, PersonalDetails.class));
                }
            });
            builder.setNegativeButton("Email:frank23@gmail.com", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }

    };
    View.OnClickListener listnr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openAlertbox();

        }

        private void openAlertbox() {

            AlertDialog.Builder builder = new AlertDialog.Builder(Manageaccount.this);
            builder.setTitle("Account Settings");
            builder.setMessage("Personal Tab");
            builder.setCancelable(false);
            builder.setPositiveButton("Home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Manageaccount.this, Mydashboard.class));
                }
            });
            builder.setNegativeButton("Personal Info", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "T.y!", Toast.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
    };


}