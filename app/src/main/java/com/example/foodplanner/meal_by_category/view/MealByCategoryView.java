package com.example.foodplanner.meal_by_category.view;

import com.example.foodplanner.Model.MealByCategory;

import java.util.List;

public interface MealByCategoryView {
    public void getMealsByCategory(List<MealByCategory> list);
    public void getMealsByCategoryErrorMsg(String errMsg);

}
