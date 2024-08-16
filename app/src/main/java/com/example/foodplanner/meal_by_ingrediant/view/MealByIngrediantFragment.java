package com.example.foodplanner.meal_by_ingrediant.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.MealByIngredant;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_by_area_name.presenter.MealByAreaPresenter;
import com.example.foodplanner.meal_by_area_name.view.MealByAreaAdapter;
import com.example.foodplanner.meal_by_area_name.view.MealByAreaFragmentArgs;
import com.example.foodplanner.meal_by_ingrediant.presenter.MealByIngrediantPresenter;

import java.util.List;


public class MealByIngrediantFragment extends Fragment implements MealByIngrediantView {


    RecyclerView rv_mealByIng;
    MealByIngrediantAdapter mealByIngrediantAdapter;
    MealByIngrediantPresenter mealByIngrediantPresenter ;


    public MealByIngrediantFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meal_by_ingrediant, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_mealByIng = view.findViewById(R.id.rv_mealByIngrediant);

        mealByIngrediantPresenter = new MealByIngrediantPresenter(this);
        String IngrediantName = MealByIngrediantFragmentArgs.fromBundle(getArguments()).getMealByIngrediantName();

        mealByIngrediantPresenter.getMealByIngrediant(IngrediantName);
    }

    @Override
    public void getMealsByIngrediant(List<MealByIngredant> AllMealsByIngrediant) {
        mealByIngrediantAdapter = new MealByIngrediantAdapter(getContext() , AllMealsByIngrediant );
        rv_mealByIng.setAdapter(mealByIngrediantAdapter);
        rv_mealByIng.setLayoutManager( new LinearLayoutManager(getContext()));
    }

    @Override
    public void setErrorMessage(String message) {

    }
}