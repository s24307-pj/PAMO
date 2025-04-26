package com.example.bmicalculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class BmiCalculatorTest {

    @Test
    public void calculateBmi_normalValues_isCorrect() {
        float weight = 70.0f;
        float height = 1.75f;
        
        float expectedBmi = weight / (height * height);
        
        float calculatedBmi = BmiCalculator.calculateBmi(weight, height);
        
        assertEquals(expectedBmi, calculatedBmi, 0.01);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateBmi_zeroHeight_throwsException() {
        BmiCalculator.calculateBmi(70.0f, 0.0f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateBmi_zeroWeight_throwsException() {
        BmiCalculator.calculateBmi(0.0f, 1.75f);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void calculateBmi_negativeHeight_throwsException() {
        BmiCalculator.calculateBmi(70.0f, -1.75f);
    }
    
    @Test
    public void getBmiCategory_underweight() {
        assertEquals("Niedowaga", BmiCalculator.getBmiCategory(17.5f));
    }
    
    @Test
    public void getBmiCategory_normal() {
        assertEquals("Optimum", BmiCalculator.getBmiCategory(22.0f));
    }
    
    @Test
    public void getBmiCategory_overweight() {
        assertEquals("Nadwaga", BmiCalculator.getBmiCategory(27.5f));
    }
    
    @Test
    public void getBmiCategory_obese() {
        assertEquals("Otyłość", BmiCalculator.getBmiCategory(32.0f));
    }
} 