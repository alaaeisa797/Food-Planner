package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.Model.MealByIngredant;

import java.util.List;

public class MealByIngrediantResponce {

    List<MealByIngredant> meals;

    public List<MealByIngredant> getMealsByIngrediant() {
        return meals;
    }

    public void setMealsByIngrediants(List<MealByIngredant> meals) {
        this.meals = meals;
    }
}
