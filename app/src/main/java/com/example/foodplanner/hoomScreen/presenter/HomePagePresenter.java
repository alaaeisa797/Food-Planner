package com.example.foodplanner.hoomScreen.presenter;

import android.content.Context;

import com.example.foodplanner.Model.RandomMealOfTheDad;
import com.example.foodplanner.hoomScreen.view.HomePageView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.MealOfTheDayCallback;

import java.util.List;

public class HomePagePresenter implements MealOfTheDayCallback
{
    APIClient apiClint;
    HomePageView homePageView;

    public HomePagePresenter(  HomePageView homePageView )
    {
        apiClint = new APIClient();
        apiClint.makeNetworkCall(this);

        this.homePageView = homePageView;

    }

    @Override
    public void onSuccessResult(List<RandomMealOfTheDad> AllMeals)
    {

        homePageView.getMealOfTheDay(AllMeals);
    }

    @Override
    public void onFailureResult(String errMsg) {

        homePageView.setErrorMessage(errMsg);
    }
}
