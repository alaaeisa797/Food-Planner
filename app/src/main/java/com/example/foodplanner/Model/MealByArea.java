package com.example.foodplanner.Model;

public class MealByArea {
    private String strMealThumb;
    private String strMeal;

    public MealByArea(String strMealThumb, String strMeal) {
        this.strMealThumb = strMealThumb;
        this.strMeal = strMeal;
    }


    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }
}
