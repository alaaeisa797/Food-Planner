package com.example.foodplanner.meal_by_ingrediant.view;

import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.Model.MealByIngredant;

import java.util.List;

public interface MealByIngrediantView {
    public void getMealsByIngrediant (List<MealByIngredant> AllMealsByIngrediant);

    public void setErrorMessage(String message);
}
