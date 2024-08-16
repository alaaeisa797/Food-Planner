package com.example.foodplanner.search.search_by_category.presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.search.search_by_category.view.SearchByCategoryView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.CategoryCallback;

import java.util.List;

public class SearchByCategoryPresenter implements CategoryCallback {
    APIClient apiClint;
    SearchByCategoryView searchByCategoryView;

    public SearchByCategoryPresenter(SearchByCategoryView categoryView ) {
        this.apiClint = new APIClient();
        apiClint.makeNetworkCall( this );
        this.searchByCategoryView = categoryView;
    }

    @Override
    public void onSuccessResult(List<Category> AllCategories) {
        searchByCategoryView.getCategory(AllCategories);
    }

    @Override
    public void onFailureResult(String errMsg) {
        searchByCategoryView.setErrorMessage(errMsg);
    }
}
