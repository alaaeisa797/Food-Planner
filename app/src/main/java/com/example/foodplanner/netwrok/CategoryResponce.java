package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.Category;

import java.util.List;

public class CategoryResponce {

    List<Category> categories;

    public List<Category> getCategory() {
        return categories;
    }

    public void setRandomMealOfTheDay(List<Category> categories) {
        this.categories = categories;
    }
}
