package com.example.foodplanner.meal_by_area_name.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_by_area_name.presenter.MealByAreaPresenter;
import com.example.foodplanner.meal_by_category.prestenter.MealByCategoryPresenter;
import com.example.foodplanner.meal_by_category.view.MealByCategoryFragmentArgs;
import com.example.foodplanner.meal_by_category.view.MealsByCategoryAdapter;

import java.util.List;


public class MealByAreaFragment extends Fragment implements MealByAreaView {

    MealByAreaPresenter mealByAreaPresenter;
    MealByAreaAdapter mealByAreaAdapter ;
    RecyclerView rv_mealByArea;

    public MealByAreaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meal_by_area, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_mealByArea = view.findViewById(R.id.rv_mealByArea);

        mealByAreaPresenter = new MealByAreaPresenter(this);
        String categoryName = MealByAreaFragmentArgs.fromBundle(getArguments()).getMealByAreaName();

        mealByAreaPresenter.getMealByArea(categoryName);

    }

    @Override
    public void getMealsByArea(List<MealByArea> AllMealsByArea) {


        mealByAreaAdapter = new MealByAreaAdapter(getContext() , AllMealsByArea );
        rv_mealByArea.setAdapter(mealByAreaAdapter);
        rv_mealByArea.setLayoutManager( new LinearLayoutManager(getContext()));

    }

    @Override
    public void setErrorMessage(String message) {

    }
}