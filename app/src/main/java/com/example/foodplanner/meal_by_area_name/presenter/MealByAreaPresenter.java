package com.example.foodplanner.meal_by_area_name.presenter;

import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.category.view.CategoryView;
import com.example.foodplanner.meal_by_area_name.view.MealByAreaView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.MealByAreaCallback;

import java.util.List;

public class MealByAreaPresenter  implements MealByAreaCallback {

    APIClient apiClint;
    MealByAreaView mealByAreaView;

    public MealByAreaPresenter( MealByAreaView mealByAreaView ) {
        this.apiClint = new APIClient();

        this.mealByAreaView = mealByAreaView;
    }

    public void getMealByArea(String mealName )
    {
        apiClint.makeNetworkCall(this,mealName);
    }
    @Override
    public void onSuccessResult(List<MealByArea> AllMealsByArea) {
        mealByAreaView.getMealsByArea(AllMealsByArea);
    }

    @Override
    public void onFailureResult(String errMsg) {
        mealByAreaView.setErrorMessage(errMsg);
    }
}
