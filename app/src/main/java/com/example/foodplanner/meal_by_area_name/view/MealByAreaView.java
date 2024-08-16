package com.example.foodplanner.meal_by_area_name.view;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealByArea;

import java.util.List;

public interface MealByAreaView {

    public void getMealsByArea (List<MealByArea> AllMealsByArea);

    public void setErrorMessage(String message);
}
