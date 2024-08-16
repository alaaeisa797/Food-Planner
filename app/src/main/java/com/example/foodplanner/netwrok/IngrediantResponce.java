package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Ingrediant;

import java.util.List;

public class IngrediantResponce {

    List<Ingrediant> meals;
    public List<Ingrediant> getIngrediants() {
        return meals;
    }

    public void setIngradiants(List<Ingrediant> ingrediants) {
        this.meals = meals;
    }
}
