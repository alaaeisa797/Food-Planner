package com.example.foodplanner.plane_meals.view;

import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;

import java.util.List;

public interface PlaneMealView {

    public void getPlanMeals (List<PlanMealDetail> planMealDetailList);
    public void setErrorMessage(String message);

}
