package com.example.foodplanner.netwrok;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Category;

import java.util.List;

public interface AreaCallback {

    void onSuccessResult(List<AreaName> AllAreas);
    void onFailureResult(String errMsg);
}
