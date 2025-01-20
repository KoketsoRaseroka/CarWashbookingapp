package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Bookslot extends AppCompatActivity {
    private RadioGroup radioGroupCarType;
    private RadioButton radioButtonCarType;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookslot);

                radioGroupCarType = findViewById(R.id.radioGroupCarType);
                buttonNext = findViewById(R.id.buttonNext);

                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedCarTypeId = radioGroupCarType.getCheckedRadioButtonId();
                        radioButtonCarType = findViewById(selectedCarTypeId);
                        String selectedCarType = radioButtonCarType.getText().toString();

                        Intent intent = new Intent(Bookslot.this, WashService.class);
                        intent.putExtra("carType", selectedCarType);
                        startActivity(intent);
                    }
                });
            }
        }

