package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealByCategory;

import java.util.List;

public interface MealByCategoryCallBack {

    void onSuccessResult(List<MealByCategory> AllMealsByCategory);
    void onFailureResult(String errMsg);
}
