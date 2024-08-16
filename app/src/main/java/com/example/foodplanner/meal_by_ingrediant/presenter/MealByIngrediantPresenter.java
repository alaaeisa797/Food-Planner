package com.example.foodplanner.meal_by_ingrediant.presenter;

import com.example.foodplanner.Model.MealByIngredant;
import com.example.foodplanner.meal_by_area_name.view.MealByAreaView;
import com.example.foodplanner.meal_by_ingrediant.view.MealByIngrediantView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.MealByIngrediantCallback;

import java.util.List;

public class MealByIngrediantPresenter implements MealByIngrediantCallback {

    APIClient apiClint;
    MealByIngrediantView mealByIngrediantView;

    public MealByIngrediantPresenter( MealByIngrediantView mealByIngrediantView ) {
        this.apiClint = new APIClient();

        this.mealByIngrediantView = mealByIngrediantView;
    }

    public void getMealByIngrediant(String ingrediantName )
    {
        apiClint.makeNetworkCall(this,ingrediantName);
    }
    @Override
    public void onSuccessResult(List<MealByIngredant> AllMealsByIngrediants)
    {
        mealByIngrediantView.getMealsByIngrediant(AllMealsByIngrediants);
    }

    @Override
    public void onFailureResult(String errMsg) {
        mealByIngrediantView.setErrorMessage(errMsg);
    }
}
