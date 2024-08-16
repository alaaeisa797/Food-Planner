package com.example.foodplanner.netwrok;




import com.example.foodplanner.Model.RandomMealOfTheDad;


import java.util.List;

public interface MealOfTheDayCallback {

    void onSuccessResult(List<RandomMealOfTheDad> AllMeals);
    void onFailureResult(String errMsg);
}