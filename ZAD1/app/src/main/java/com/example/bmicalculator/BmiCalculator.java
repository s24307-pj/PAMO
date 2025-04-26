package com.example.bmicalculator;

public class BmiCalculator {
    
    public static float calculateBmi(float weight, float height) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Waga i wzrost muszą być większe od zera");
        }
        return weight / (height * height);
    }
    
    public static String getBmiCategory(float bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Optimum";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }
} 