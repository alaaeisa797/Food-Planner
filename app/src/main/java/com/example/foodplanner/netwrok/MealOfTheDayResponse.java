package com.example.foodplanner.netwrok;




import com.example.foodplanner.Model.RandomMealOfTheDad;


import java.util.List;

public class MealOfTheDayResponse {
    List<RandomMealOfTheDad> meals;

    public List<RandomMealOfTheDad> getCategory() {
        return meals;
    }

    public void setRandomMealOfTheDay(List<RandomMealOfTheDad> randomMealOfTheDay) {
        this.meals = randomMealOfTheDay;
    }
}