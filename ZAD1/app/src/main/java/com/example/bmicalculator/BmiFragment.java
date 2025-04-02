package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BmiFragment extends Fragment {

    private EditText editTextWeight, editTextHeight;
    private Button buttonCalculateBmi;
    private TextView textViewBmiResult, textViewBmiStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextHeight = view.findViewById(R.id.editTextHeight);
        buttonCalculateBmi = view.findViewById(R.id.buttonCalculateBmi);
        textViewBmiResult = view.findViewById(R.id.textViewBmiResult);
        textViewBmiStatus = view.findViewById(R.id.textViewBmiStatus);

        buttonCalculateBmi.setOnClickListener(v -> calculateBMI());

        return view;
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(getContext(), getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100;

            if (weight <= 0) {
                Toast.makeText(getContext(), getString(R.string.enter_valid_values), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (height <= 0) {
                Toast.makeText(getContext(), "Wzrost musi być większy od zera!", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (height < 0.5) {
                Toast.makeText(getContext(), "Wzrost wydaje się być zbyt niski. Sprawdź wprowadzone dane.", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (height > 2.5) {
                Toast.makeText(getContext(), "Wzrost wydaje się być zbyt wysoki. Sprawdź wprowadzone dane.", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);
            textViewBmiResult.setText(getString(R.string.bmi_result, String.format("%.2f", bmi)));

            String status;
            if (bmi < 18.5) {
                status = getString(R.string.bmi_underweight);
            } else if (bmi < 25) {
                status = getString(R.string.bmi_normal);
            } else if (bmi < 30) {
                status = getString(R.string.bmi_overweight);
            } else {
                status = getString(R.string.bmi_obese);
            }

            textViewBmiStatus.setText(status);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), getString(R.string.enter_valid_numeric_values), Toast.LENGTH_SHORT).show();
        }
    }
} 