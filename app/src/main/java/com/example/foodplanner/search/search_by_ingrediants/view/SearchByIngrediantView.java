package com.example.foodplanner.search.search_by_ingrediants.view;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Ingrediant;

import java.util.List;

public interface SearchByIngrediantView {

    public void getIngrediant(List<Ingrediant> AllIngrediants);

    public void setErrorMessage(String message);
}
