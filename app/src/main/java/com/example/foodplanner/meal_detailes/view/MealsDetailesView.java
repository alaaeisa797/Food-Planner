package com.example.foodplanner.meal_detailes.view;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealsDetailes;

import java.util.List;

public interface MealsDetailesView {

    public void getMealDetail (List<MealsDetailes> MealsDetailes);

    public void setErrorMessage(String message);
}
