package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// da el interface elli gwa no3 el operatio bta3ti w el EndPoint Bta3ti
public interface MealsService {

    @GET("random.php")
    Single<MealOfTheDayResponse> getAllProducts();

    @GET("categories.php")
    public Single<CategoryResponce> getAllCategory();

    @GET("filter.php") // for shownig the meal by category  when i choose a certain category from home Fragment
    public Single<MealByCategoryResponce> getMealsByCategory(@Query("c")String categoryName);
     @GET("search.php") // for shownig the meal by category  when i choose a certain category from home Fragment
    public Single<MealDetailResponce> getMealsDetailes(@Query("s")String mealName);

    @GET("filter.php")
    public Single<MealByAreaResponce> getMealsByArea(@Query("a") String AreaName);

    @GET("filter.php")
    Single<MealByIngrediantResponce> getMealsByIngredient(@Query("i") String country);

    @GET("list.php?a=list")
    public Single<AreaResponce>getAreas();
    @GET("list.php?i=list")
    Single<IngrediantResponce>getIngredients();



}
