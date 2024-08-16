package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;

import java.util.List;

public class AreaResponce {

    List<AreaName> meals;
    public List<AreaName> getMeals() {
        return meals;
    }

    public void setMeals(List<AreaName> meals) {
        this.meals = meals;
    }
}
