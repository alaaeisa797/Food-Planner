package com.example.foodplanner.Model;

public class MealByCategory {
    private String strMealThumb;
    private String strMeal;

    public MealByCategory()
    {

    }

    public MealByCategory(String strMealThumb, String strMeal) {
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
