package com.example.foodplanner.hoomScreen.view;

import com.example.foodplanner.Model.RandomMealOfTheDad;

import java.util.List;

public interface HomePageView {
    public void getMealOfTheDay (List<RandomMealOfTheDad> allProducts);

    public void setErrorMessage(String message);



}
