package com.example.foodplanner.meal_by_category.prestenter;

import com.example.foodplanner.Model.MealByCategory;
import com.example.foodplanner.meal_by_category.view.MealByCategoryView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.MealByCategoryCallBack;

import java.util.List;

public class MealByCategoryPresenter implements MealByCategoryCallBack {
    APIClient apiClint;
    MealByCategoryView mealByCategoryView;

    public MealByCategoryPresenter(MealByCategoryView mealByCategoryView) {
        this.mealByCategoryView = mealByCategoryView;
        this.apiClint = new APIClient();
       // apiClint.makeNetworkCall(this);
    }

    public void getMealByCategory (String categoryName )
    {
        apiClint.makeNetworkCall(this,categoryName);
    }

    @Override
    public void onSuccessResult(List<MealByCategory> AllMealsByCategory)
    {
        mealByCategoryView.getMealsByCategory(AllMealsByCategory);
    }

    @Override
    public void onFailureResult(String errMsg) {
        mealByCategoryView.getMealsByCategoryErrorMsg(errMsg);

    }
}
