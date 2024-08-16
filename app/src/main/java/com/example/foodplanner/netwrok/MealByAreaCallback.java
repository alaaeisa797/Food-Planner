package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.Model.MealByCategory;

import java.util.List;

public interface MealByAreaCallback {

    void onSuccessResult(List<MealByArea> AllMealsByArea);
    void onFailureResult(String errMsg);
}
