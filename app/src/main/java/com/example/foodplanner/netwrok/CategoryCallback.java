package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.RandomMealOfTheDad;

import java.util.List;

public interface CategoryCallback {

    void onSuccessResult(List<Category> AllCategories);
    void onFailureResult(String errMsg);
}
