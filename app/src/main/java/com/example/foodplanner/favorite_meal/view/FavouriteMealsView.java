package com.example.foodplanner.favorite_meal.view;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealsDetailes;

import java.util.List;

public interface FavouriteMealsView {

    public void getFavMeals (List<MealsDetailes> AllFavouriteMeals);

    public void setErrorMessage(String message);
}
