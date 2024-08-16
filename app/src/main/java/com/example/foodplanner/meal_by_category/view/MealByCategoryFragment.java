package com.example.foodplanner.meal_by_category.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.MealByCategory;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_by_category.prestenter.MealByCategoryPresenter;

import java.util.List;


public class MealByCategoryFragment extends Fragment  implements MealByCategoryView {

    MealByCategoryPresenter mealByCategoryPresenter ;
    RecyclerView rv_mealByCategory;

    MealsByCategoryAdapter mealsByCategoryAdapter;

    public MealByCategoryFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_by_category, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_mealByCategory = view.findViewById(R.id.rv_mealByCategory);



        mealByCategoryPresenter = new MealByCategoryPresenter(this);
        String categoryName = MealByCategoryFragmentArgs.fromBundle(getArguments()).getCategoryName();

        mealByCategoryPresenter.getMealByCategory(categoryName);

    }

    @Override
    public void getMealsByCategory(List<MealByCategory> list) {
        mealsByCategoryAdapter = new MealsByCategoryAdapter(getContext() , list );
        rv_mealByCategory.setAdapter(mealsByCategoryAdapter);
        rv_mealByCategory.setLayoutManager( new LinearLayoutManager(getContext()));
    }

    @Override
    public void getMealsByCategoryErrorMsg(String errMsg) {

    }
}