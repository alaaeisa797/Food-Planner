package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.MealByArea;

import java.util.List;

public class MealByAreaResponce {

    List<MealByArea> meals;


    public List<MealByArea> getMealsByArea() {
        return meals;
    }

    public void setMeals(List<MealByArea> meals) {
        this.meals = meals;
    }
}
