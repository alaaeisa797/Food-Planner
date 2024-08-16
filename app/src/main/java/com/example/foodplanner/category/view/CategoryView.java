package com.example.foodplanner.category.view;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.RandomMealOfTheDad;

import java.util.List;

public interface CategoryView {
    public void getCategory (List<Category> AllCategories);

    public void setErrorMessage(String message);
}
