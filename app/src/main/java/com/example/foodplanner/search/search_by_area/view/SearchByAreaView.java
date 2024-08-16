package com.example.foodplanner.search.search_by_area.view;

import com.example.foodplanner.Model.AreaName;

import java.util.List;

public interface SearchByAreaView {
    public void getArea(List<AreaName> AllAreas);

    public void setErrorMessage(String message);
}
