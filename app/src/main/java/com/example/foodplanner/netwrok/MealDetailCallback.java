package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealsDetailes;

import java.util.List;

public interface MealDetailCallback {

    void onSuccessResult(List<MealsDetailes> AllMealsDetailes);
    void onFailureResult(String errMsg);
}
