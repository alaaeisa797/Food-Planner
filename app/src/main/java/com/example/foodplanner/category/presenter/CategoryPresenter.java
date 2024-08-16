package com.example.foodplanner.category.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.category.view.CategoryView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.CategoryCallback;

import java.util.List;

public class CategoryPresenter implements CategoryCallback {
    APIClient apiClint;
    CategoryView categoryView;

    public CategoryPresenter( CategoryView categoryView ) {
        this.apiClint = new APIClient();
        apiClint.makeNetworkCall( this );
        this.categoryView = categoryView;
    }

    @Override
    public void onSuccessResult(List<Category> AllCategories) {
        categoryView.getCategory(AllCategories);
    }

    @Override
    public void onFailureResult(String errMsg) {
        categoryView.setErrorMessage(errMsg);
    }
}
