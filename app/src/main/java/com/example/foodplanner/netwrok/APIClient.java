package com.example.foodplanner.netwrok;

import android.annotation.SuppressLint;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    final String BASE_URL = "https://www.themealdb.com/api/json/v1/1//";

    MealsService connectionsInterface; // ref 3al interface elli gwaha el network Operation bta3ty


    public APIClient(  ){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        connectionsInterface = retrofit.create(MealsService.class); // object mn class be implement el interface (ProuductService)
    }

// 44444-makeNetWorkCall de method hena fel API Client bs , w bta5od ref 3al netWorkCalBack As a parameter w leha one uuseage
    //ana aslun baml object mn API Client 3shanha (3shan a3raf a call 3leha )
    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealOfTheDayCallback networkCallback){

        connectionsInterface.getAllProducts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  networkCallback.onSuccessResult(item.getCategory()),
                        throwable ->
                        {
                            networkCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(CategoryCallback categoryCallback ){

        connectionsInterface.getAllCategory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  categoryCallback.onSuccessResult(item.getCategory()),
                        throwable ->
                        {
                            categoryCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealByCategoryCallBack mealByCategoryCallback , String category ){

        connectionsInterface.getMealsByCategory(category).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  mealByCategoryCallback.onSuccessResult(item.getMealByCategory()),
                        throwable ->
                        {
                            mealByCategoryCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }


    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealDetailCallback mealDetailsCallback , String mealName ){

        connectionsInterface.getMealsDetailes(mealName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  mealDetailsCallback.onSuccessResult(item.getMealDetailes()),
                        throwable ->
                        {
                            mealDetailsCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(AreaCallback areaCallback  ){

        connectionsInterface.getAreas().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  areaCallback.onSuccessResult(item.getMeals()),
                        throwable ->
                        {
                            areaCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(IngrediantCallback ingrediantCallback  ){

        connectionsInterface.getIngredients().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  ingrediantCallback.onSuccessResult(item.getIngrediants()),
                        throwable ->
                        {
                            ingrediantCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }


    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealByAreaCallback mealByAreaCallback , String areaName ){

        connectionsInterface.getMealsByArea(areaName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  mealByAreaCallback.onSuccessResult(item.getMealsByArea()),
                        throwable ->
                        {
                            mealByAreaCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void makeNetworkCall(MealByIngrediantCallback mealByIngrediantCallback , String ingrediantName ){

        connectionsInterface.getMealsByIngredient(ingrediantName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        item ->  mealByIngrediantCallback.onSuccessResult(item.getMealsByIngrediant()),
                        throwable ->
                        {
                            mealByIngrediantCallback.onFailureResult(throwable.getMessage());
                        }
                );
    }
}
