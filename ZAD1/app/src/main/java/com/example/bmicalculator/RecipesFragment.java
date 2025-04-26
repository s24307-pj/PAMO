package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RecipesFragment extends Fragment {

    private TextView textViewCaloriesInfo;
    private double calories = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        
        textViewCaloriesInfo = view.findViewById(R.id.textViewCaloriesInfo);

        Bundle args = getArguments();
        if (args != null && args.containsKey("calories")) {
            calories = args.getDouble("calories", 0);
            updateCaloriesInfo();
        }
        
        return view;
    }
    
    private void updateCaloriesInfo() {
        if (calories > 0) {
            textViewCaloriesInfo.setText(getString(R.string.caloric_info, Math.round(calories)));
        } else {
            textViewCaloriesInfo.setText(getString(R.string.calories_info_empty));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View recipe1Card = view.findViewById(R.id.recipe1Card);
        recipe1Card.setOnClickListener(v -> {
            RecipeDetailsFragment fragment = RecipeDetailsFragment.newInstance(
                getString(R.string.recipe_1_title),
                getString(R.string.recipe_1_calories),
                getString(R.string.recipe_1_description),
                getString(R.string.recipe_1_ingredients),
                getString(R.string.recipe_1_steps),
                R.drawable.saltaka_kurczak_avocado
            );
            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        });

        View recipe2Card = view.findViewById(R.id.recipe2Card);
        recipe2Card.setOnClickListener(v -> {
            RecipeDetailsFragment fragment = RecipeDetailsFragment.newInstance(
                getString(R.string.recipe_2_title),
                getString(R.string.recipe_2_calories),
                getString(R.string.recipe_2_description),
                getString(R.string.recipe_2_ingredients),
                getString(R.string.recipe_2_steps),
                R.drawable.omlet_z_warzywami
            );
            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
        });
    }
} 