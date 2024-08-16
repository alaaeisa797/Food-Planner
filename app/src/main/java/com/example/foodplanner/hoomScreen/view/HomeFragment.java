package com.example.foodplanner.hoomScreen.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.RandomMealOfTheDad;
import com.example.foodplanner.R;
import com.example.foodplanner.category.presenter.CategoryPresenter;
import com.example.foodplanner.category.view.CategoryAdapter;
import com.example.foodplanner.category.view.CategoryView;
import com.example.foodplanner.hoomScreen.presenter.HomePagePresenter;

import java.util.List;


public class HomeFragment extends Fragment implements HomePageView , CategoryView {


    HomePagePresenter presenter ;
    CategoryPresenter categoryPresenter;
    HomePageAdapter adapter ;
    RecyclerView rv_mealOfTheDay;

    RecyclerView rv_category ;

    CategoryAdapter adapterCategory;




    public HomeFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_mealOfTheDay =view.findViewById(R.id.rv_MealOfTheDay);
        rv_category = view.findViewById(R.id.rv_category);


        presenter = new HomePagePresenter  ( this );
        categoryPresenter = new CategoryPresenter(this );


    }

    @Override
    public void getMealOfTheDay(List<RandomMealOfTheDad> allProducts) {
        adapter = new HomePageAdapter(  getContext() , allProducts );
        rv_mealOfTheDay.setAdapter(adapter);
        rv_mealOfTheDay.setLayoutManager (new LinearLayoutManager(getContext()));
    }

    @Override
    public void getCategory(List<Category> AllCategories) {

        adapterCategory = new CategoryAdapter( getContext() , AllCategories );
        rv_category.setAdapter(adapterCategory);
        rv_category.setLayoutManager( new LinearLayoutManager( getContext() , LinearLayoutManager.HORIZONTAL , false));
    }

    @Override
    public void setErrorMessage(String message) {

    }
}