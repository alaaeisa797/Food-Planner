package com.example.foodplanner.search.search_by_ingrediants.presenter;

import com.example.foodplanner.Model.Ingrediant;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.IngrediantCallback;
import com.example.foodplanner.search.search_by_area.view.SearchByAreaView;
import com.example.foodplanner.search.search_by_ingrediants.view.SearchByIngrediantView;

import java.util.List;

public class SearchByIngrediantPresenter implements IngrediantCallback {

    APIClient apiClint;
    SearchByIngrediantView SearchByIngrediantView;

    public SearchByIngrediantPresenter(SearchByIngrediantView SearchByIngrediantView ) {
        this.apiClint = new APIClient();
        apiClint.makeNetworkCall( this );
        this.SearchByIngrediantView = SearchByIngrediantView;
    }


    @Override
    public void onSuccessResult(List<Ingrediant> AllIngrediants) {
        SearchByIngrediantView.getIngrediant(AllIngrediants);
    }

    @Override
    public void onFailureResult(String errMsg) {
        SearchByIngrediantView.setErrorMessage(errMsg);
    }
}
