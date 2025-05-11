package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.navigation_bmi) {
            fragment = new BmiFragment();
        } else if (itemId == R.id.navigation_calories) {
            fragment = new CaloriesFragment();
        } else if (itemId == R.id.navigation_recipes) {
            fragment = new RecipesFragment();
        } else if (itemId == R.id.navigation_shopping) {
            fragment = ShoppingListFragment.getInstance();
        }

        return loadFragment(fragment);
    }
    
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            try {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            } catch (Exception e) {
                Toast.makeText(this, "Błąd podczas ładowania fragmentu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    public void navigateToFragment(int fragmentId) {
        try {
            bottomNavigationView.setSelectedItemId(fragmentId);
        } catch (Exception e) {
            Toast.makeText(this, "Błąd podczas nawigacji: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}