package com.example.foodplanner.search.search_by_category.view;

import com.example.foodplanner.Model.Category;

import java.util.List;

public interface SearchByCategoryView {
    public void getCategory (List<Category> AllCategories);

    public void setErrorMessage(String message);
}
