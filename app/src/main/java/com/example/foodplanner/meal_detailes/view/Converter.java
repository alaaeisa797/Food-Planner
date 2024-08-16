package com.example.foodplanner.meal_detailes.view;

import android.util.Log;

import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;

public class Converter {

    public static PlanMealDetail convert( MealsDetailes detailsMealItem) {
        Log.d("TAG", "convert: "+detailsMealItem.getStrMeal());
        PlanMealDetail planMealItem= new PlanMealDetail();
        planMealItem.setStrMeal(detailsMealItem.getStrMeal());
        planMealItem.setStrArea(detailsMealItem.getStrArea());
        planMealItem.setStrCategory(detailsMealItem.getStrCategory());
        planMealItem.setStrIngredient1(detailsMealItem.getStrIngredient1());
        planMealItem.setStrIngredient2(detailsMealItem.getStrIngredient2());
        planMealItem.setStrIngredient3(detailsMealItem.getStrIngredient3());
        planMealItem.setStrIngredient4(detailsMealItem.getStrIngredient4());
        planMealItem.setStrIngredient5(detailsMealItem.getStrIngredient5());
        planMealItem.setStrIngredient6(detailsMealItem.getStrIngredient6());
        planMealItem.setStrIngredient7(detailsMealItem.getStrIngredient7());
        planMealItem.setStrIngredient8(detailsMealItem.getStrIngredient8());
        planMealItem.setStrIngredient9(detailsMealItem.getStrIngredient9());
        planMealItem.setStrIngredient10(detailsMealItem.getStrIngredient10());
        planMealItem.setStrIngredient11(detailsMealItem.getStrIngredient11());
        planMealItem.setStrIngredient12(detailsMealItem.getStrIngredient12());
        planMealItem.setStrIngredient13(detailsMealItem.getStrIngredient13());
        planMealItem.setStrIngredient14(detailsMealItem.getStrIngredient14());
        planMealItem.setStrIngredient15(detailsMealItem.getStrIngredient15());
        planMealItem.setStrInstructions(detailsMealItem.getStrInstructions());
        planMealItem.setStrMealThumb(detailsMealItem.getStrMealThumb());
        planMealItem.setStrYoutube(detailsMealItem.getStrYoutube());
        planMealItem.setDayOfMonth(0);
        return planMealItem;
    }
}
