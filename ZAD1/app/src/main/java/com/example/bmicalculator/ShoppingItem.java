package com.example.bmicalculator;

public class ShoppingItem {
    private String name;
    private boolean purchased;
    private String recipeSource;

    public ShoppingItem(String name, String recipeSource) {
        this.name = name;
        this.purchased = false;
        this.recipeSource = recipeSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getRecipeSource() {
        return recipeSource;
    }

    public void setRecipeSource(String recipeSource) {
        this.recipeSource = recipeSource;
    }
} 