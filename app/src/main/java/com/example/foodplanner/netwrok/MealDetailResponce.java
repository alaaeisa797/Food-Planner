package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.MealByCategory;
import com.example.foodplanner.Model.MealsDetailes;

import java.util.List;

public class MealDetailResponce {

    List<MealsDetailes> meals;

    public List<MealsDetailes> getMealDetailes() {
        return meals;
    }

    public void setRandomMealOfTheDay(List<MealsDetailes> MealDetail) {
        this.meals = MealDetail;
    }
}
