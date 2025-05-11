package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RecipeDetailsFragment extends Fragment {
    private static final String ARG_RECIPE_TITLE = "recipe_title";
    private static final String ARG_RECIPE_CALORIES = "recipe_calories";
    private static final String ARG_RECIPE_DESCRIPTION = "recipe_description";
    private static final String ARG_RECIPE_INGREDIENTS = "recipe_ingredients";
    private static final String ARG_RECIPE_STEPS = "recipe_steps";
    private static final String ARG_RECIPE_IMAGE = "recipe_image";

    private String recipeTitle;
    private String recipeCalories;
    private String recipeDescription;
    private String recipeIngredients;
    private String recipeSteps;
    private int recipeImage;

    public static RecipeDetailsFragment newInstance(String title, String calories, String description, 
            String ingredients, String steps, int imageResourceId) {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RECIPE_TITLE, title);
        args.putString(ARG_RECIPE_CALORIES, calories);
        args.putString(ARG_RECIPE_DESCRIPTION, description);
        args.putString(ARG_RECIPE_INGREDIENTS, ingredients);
        args.putString(ARG_RECIPE_STEPS, steps);
        args.putInt(ARG_RECIPE_IMAGE, imageResourceId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            recipeTitle = getArguments().getString(ARG_RECIPE_TITLE);
            recipeCalories = getArguments().getString(ARG_RECIPE_CALORIES);
            recipeDescription = getArguments().getString(ARG_RECIPE_DESCRIPTION);
            recipeIngredients = getArguments().getString(ARG_RECIPE_INGREDIENTS);
            recipeSteps = getArguments().getString(ARG_RECIPE_STEPS);
            recipeImage = getArguments().getInt(ARG_RECIPE_IMAGE, R.drawable.logo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.recipeImage);
        TextView titleView = view.findViewById(R.id.recipeTitle);
        TextView caloriesView = view.findViewById(R.id.recipeCalories);
        TextView descriptionView = view.findViewById(R.id.recipeDescription);
        TextView ingredientsView = view.findViewById(R.id.ingredientsList);
        TextView stepsView = view.findViewById(R.id.stepsList);
        Button addToShoppingListButton = view.findViewById(R.id.buttonAddToShoppingList);

        imageView.setImageResource(recipeImage);
        titleView.setText(recipeTitle);
        caloriesView.setText(recipeCalories);
        descriptionView.setText(recipeDescription);
        ingredientsView.setText(recipeIngredients);
        stepsView.setText(recipeSteps);
        
        addToShoppingListButton.setOnClickListener(v -> {
            try {
                ShoppingListFragment shoppingListFragment = ShoppingListFragment.getInstance();
                
                shoppingListFragment.addItemsFromRecipe(recipeTitle, recipeIngredients);
                
                Toast.makeText(requireContext(), R.string.item_added_to_shopping_list, Toast.LENGTH_SHORT).show();
                
                MainActivity activity = (MainActivity) requireActivity();
                activity.navigateToFragment(R.id.navigation_shopping);
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Wystąpił błąd: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
} 