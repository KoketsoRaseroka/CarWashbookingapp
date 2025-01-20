package com.example.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class WashService extends AppCompatActivity {
    private RadioGroup radioGroupWashServiceType;
    private RadioButton radioButtonWashServiceType;
    private Button buttonNext;

    private String selectedCarType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_service);

                selectedCarType = getIntent().getStringExtra("carType");

                radioGroupWashServiceType = findViewById(R.id.radioGroupWashServiceType);
                buttonNext = findViewById(R.id.buttonNext);

                buttonNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedWashServiceTypeId = radioGroupWashServiceType.getCheckedRadioButtonId();
                        radioButtonWashServiceType = findViewById(selectedWashServiceTypeId);
                        String selectedWashServiceType = radioButtonWashServiceType.getText().toString();

                        Intent intent = new Intent(WashService.this, DateTimePicker.class);
                        intent.putExtra("carType", selectedCarType);
                        intent.putExtra("washServiceType", selectedWashServiceType);
                        startActivity(intent);
                    }
                });
            }
        }

