package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.MealByIngredant;

import java.util.List;

public interface MealByIngrediantCallback {

    void onSuccessResult(List<MealByIngredant> AllMealsByIngrediants);
    void onFailureResult(String errMsg);
}
