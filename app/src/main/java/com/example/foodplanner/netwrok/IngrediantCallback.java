package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Ingrediant;

import java.util.List;

public interface IngrediantCallback {

    void onSuccessResult(List<Ingrediant> AllIngrediants);
    void onFailureResult(String errMsg);

}
