package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.MealByCategory;

import java.util.List;

public class MealByCategoryResponce {

    List<MealByCategory> meals;

    public List<MealByCategory> getMealByCategory() {
        return meals;
    }

    public void setRandomMealOfTheDay(List<MealByCategory> MealBycategories) {
        this.meals = MealBycategories;
    }
}
