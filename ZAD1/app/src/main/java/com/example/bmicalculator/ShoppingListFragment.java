package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListFragment extends Fragment implements ShoppingListAdapter.OnItemClickListener {

    private RecyclerView recyclerViewShoppingList;
    private TextView textViewEmptyList;
    private TextView textViewRecipeTitle;
    private Button buttonClearCompleted;
    private ShoppingListAdapter adapter;
    private static List<ShoppingItem> shoppingItems = new ArrayList<>();
    private static String pendingRecipeTitle = null;
    private static String pendingIngredients = null;

    private static ShoppingListFragment instance;

    public static ShoppingListFragment getInstance() {
        if (instance == null) {
            instance = new ShoppingListFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        
        recyclerViewShoppingList = view.findViewById(R.id.recyclerViewShoppingList);
        textViewEmptyList = view.findViewById(R.id.textViewEmptyList);
        textViewRecipeTitle = view.findViewById(R.id.textViewRecipeTitle);
        buttonClearCompleted = view.findViewById(R.id.buttonClearCompleted);
        
        setupRecyclerView();
        
        buttonClearCompleted.setOnClickListener(v -> {
            adapter.removeCompletedItems();
            updateEmptyListVisibility();
        });
        
        if (pendingRecipeTitle != null && pendingIngredients != null) {
            processIngredients(pendingRecipeTitle, pendingIngredients);
            pendingRecipeTitle = null;
            pendingIngredients = null;
        }
        
        return view;
    }

    private void setupRecyclerView() {
        recyclerViewShoppingList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingListAdapter(shoppingItems, this);
        recyclerViewShoppingList.setAdapter(adapter);
        updateEmptyListVisibility();
    }

    @Override
    public void onItemClick(int position) {
        ShoppingItem item = shoppingItems.get(position);
        item.setPurchased(!item.isPurchased());
        adapter.notifyItemChanged(position);
    }

    public void addItemsFromRecipe(String recipeTitle, String ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            return;
        }

        if (!isAdded()) {
            pendingRecipeTitle = recipeTitle;
            pendingIngredients = ingredients;
            return;
        }

        processIngredients(recipeTitle, ingredients);
    }
    
    private void processIngredients(String recipeTitle, String ingredients) {
        if (textViewRecipeTitle != null) {
            textViewRecipeTitle.setText(recipeTitle);
        }
        
        String[] lines = ingredients.split("\n");
        for (String line : lines) {
            String cleanLine = line.trim().replaceAll("^•\\s*", "");
            if (!cleanLine.isEmpty()) {
                shoppingItems.add(new ShoppingItem(cleanLine, recipeTitle));
            }
        }
        
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            updateEmptyListVisibility();
        }
        
        if (getContext() != null) {
            Toast.makeText(getContext(), R.string.item_added_to_shopping_list, Toast.LENGTH_SHORT).show();
        }
    }
    
    private void updateEmptyListVisibility() {
        if (shoppingItems.isEmpty()) {
            textViewEmptyList.setVisibility(View.VISIBLE);
            recyclerViewShoppingList.setVisibility(View.GONE);
        } else {
            textViewEmptyList.setVisibility(View.GONE);
            recyclerViewShoppingList.setVisibility(View.VISIBLE);
        }
    }
} 