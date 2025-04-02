package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CaloriesFragment extends Fragment {

    private EditText editTextWeight, editTextHeight, editTextAge;
    private Spinner spinnerActivityLevel;
    private Button buttonCalculateCalories, buttonViewRecipes;
    private TextView textViewCaloriesResult;
    
    private double calculatedCalories = 0;

    private final double[] ACTIVITY_MULTIPLIERS = {1.2, 1.375, 1.55, 1.725, 1.9};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calories, container, false);

        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextHeight = view.findViewById(R.id.editTextHeight);
        editTextAge = view.findViewById(R.id.editTextAge);
        spinnerActivityLevel = view.findViewById(R.id.spinnerActivityLevel);
        buttonCalculateCalories = view.findViewById(R.id.buttonCalculateCalories);
        buttonViewRecipes = view.findViewById(R.id.buttonViewRecipes);
        textViewCaloriesResult = view.findViewById(R.id.textViewCaloriesResult);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.activity_levels,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivityLevel.setAdapter(adapter);

        buttonCalculateCalories.setOnClickListener(v -> calculateCalories());
        
        buttonViewRecipes.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToFragment(R.id.navigation_recipes);

                Bundle bundle = new Bundle();
                bundle.putDouble("calories", calculatedCalories);
                RecipesFragment recipesFragment = new RecipesFragment();
                recipesFragment.setArguments(bundle);
                
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, recipesFragment)
                        .commit();
            }
        });

        return view;
    }

    private void calculateCalories() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();
        String ageStr = editTextAge.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(getContext(), "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);
            int age = Integer.parseInt(ageStr);
            
            if (weight <= 0 || height <= 0 || age <= 0) {
                Toast.makeText(getContext(), "Wprowadź prawidłowe wartości!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isMale = ((RadioButton) getView().findViewById(R.id.radioButtonMale)).isChecked();

            int activityIndex = spinnerActivityLevel.getSelectedItemPosition();
            double activityMultiplier = ACTIVITY_MULTIPLIERS[activityIndex];

            double bmr;
            if (isMale) {
                bmr = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
            } else {
                bmr = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
            }
            
            calculatedCalories = bmr * activityMultiplier;
            textViewCaloriesResult.setText("Twoje dzienne zapotrzebowanie: " + Math.round(calculatedCalories) + " kcal");
            buttonViewRecipes.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Wprowadź prawidłowe wartości liczbowe!", Toast.LENGTH_SHORT).show();
        }
    }
} 