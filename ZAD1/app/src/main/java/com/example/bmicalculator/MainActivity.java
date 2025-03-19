package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText weightButton, heightButton;
    private Button buttonSubmitBmi;
    private TextView result, resultBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightButton = findViewById(R.id.weight);
        heightButton = findViewById(R.id.height);
        buttonSubmitBmi = findViewById(R.id.submitBmi);
        result = findViewById(R.id.result);
        resultBMI = findViewById(R.id.resultBMI);

        buttonSubmitBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightButton.getText().toString();
        String heightStr = heightButton.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100;

            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Wprowadź prawidłowe wartości!", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);

            result.setText("Twoje BMI wynosi: " + String.format("%.2f", bmi));

            String status;
            if (bmi < 18.5) {
                status = "Niedowaga";
            } else if (bmi < 25) {
                status = "Optimum";
            } else if (bmi < 30) {
                status = "Nadwaga";
            } else {
                status = "Otyłość";
            }

            resultBMI.setText(status);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wprowadź prawidłowe wartości liczbowe!", Toast.LENGTH_SHORT).show();
        }
    }
}