package com.example.foodplanner.search.search_by_area.presenter;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.search.search_by_area.view.SearchByAreaView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.AreaCallback;

import java.util.List;

public class SearchByAreaPresenter implements AreaCallback {
    APIClient apiClint;
    SearchByAreaView searchByAreaView;

    public SearchByAreaPresenter(SearchByAreaView searchByAreaView ) {
        this.apiClint = new APIClient();
        apiClint.makeNetworkCall( this );
        this.searchByAreaView = searchByAreaView;
    }



    @Override
    public void onSuccessResult(List<AreaName> AllAreas) {
        searchByAreaView.getArea(AllAreas);
    }

    @Override
    public void onFailureResult(String errMsg) {
        searchByAreaView.setErrorMessage(errMsg);
    }
}
