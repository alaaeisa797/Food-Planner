package com.example.foodplanner.search;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Ingrediant;
import com.example.foodplanner.R;
import com.example.foodplanner.search.search_by_area.presenter.SearchByAreaPresenter;
import com.example.foodplanner.search.search_by_area.view.SearchByAreaAdapter;
import com.example.foodplanner.search.search_by_area.view.SearchByAreaView;
import com.example.foodplanner.search.search_by_category.presenter.SearchByCategoryPresenter;
import com.example.foodplanner.search.search_by_category.view.SearchByCategoryAdapter;
import com.example.foodplanner.search.search_by_category.view.SearchByCategoryView;
import com.example.foodplanner.search.search_by_ingrediants.presenter.SearchByIngrediantPresenter;
import com.example.foodplanner.search.search_by_ingrediants.view.IngrediantSearchAdapter;
import com.example.foodplanner.search.search_by_ingrediants.view.SearchByIngrediantView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class SearchFragment extends Fragment implements SearchByCategoryView , SearchByAreaView , SearchByIngrediantView {


    List<Ingrediant> MyIngrediants;
    List<AreaName> MyAreaName;
    List<Category> MyCategory;
    Chip chp_category ;
    Chip chp_area ;
    Chip chp_Ingrediant;

    RecyclerView rv_categoryInSerach ;
    RecyclerView rv_AreaInSerach ;
    RecyclerView rv_IngrediantInSearch ;

    SearchByCategoryPresenter searchByCategoryPresenter;
    SearchByAreaPresenter searchByAreaPresenter;
    SearchByIngrediantPresenter searchByIngrediantPresenter;


    SearchByCategoryAdapter searchByCategoryAdapter;
    SearchByAreaAdapter searchByAreaAdapter;
    IngrediantSearchAdapter ingrediantSearchAdapter;

    SearchView searchView ;


     ChipGroup chipGroup;




    public SearchFragment() {

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchView);

        chp_category = view .findViewById(R.id.chp_category);
        rv_categoryInSerach = view.findViewById(R.id.rv_category);

        chp_area = view.findViewById(R.id.chp_country);
        rv_AreaInSerach=view.findViewById(R.id.rv_area);


        chp_Ingrediant = view.findViewById(R.id.chp_ingrediants);
        rv_IngrediantInSearch=view.findViewById(R.id.rv_Ingrediants);



        chipGroup = view.findViewById(R.id.ch_group);

        chipGroup.setSingleSelection(true);
        chp_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_categoryInSerach.setVisibility(View.VISIBLE);
                //rv_categoryInSerach.setVisibility(View.GONE);
                searchByCategoryPresenter= new SearchByCategoryPresenter( SearchFragment.this);
                rv_AreaInSerach.setVisibility(View.GONE);
                rv_IngrediantInSearch.setVisibility(View.GONE);
            }
        });

        chp_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rv_AreaInSerach.setVisibility(View.VISIBLE);

                searchByAreaPresenter = new SearchByAreaPresenter(SearchFragment.this );
                rv_categoryInSerach.setVisibility(View.GONE);
                rv_IngrediantInSearch.setVisibility(View.GONE);
            }
        });

        chp_Ingrediant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rv_IngrediantInSearch.setVisibility(View.VISIBLE);
                searchByIngrediantPresenter = new SearchByIngrediantPresenter(SearchFragment.this );
                rv_categoryInSerach.setVisibility(View.GONE);
                rv_AreaInSerach.setVisibility(View.GONE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("CheckResult")
            @Override
            public boolean onQueryTextChange(String newText) {

                if ( chp_Ingrediant.isChecked() ) {
                    Observable.fromIterable(MyIngrediants)
                            .filter(ingrediant -> ingrediant.getStrIngredient().toLowerCase().contains(newText.toString()))
                            .toList().subscribe(filterList -> ingrediantSearchAdapter.setData(filterList)


                            );
                }
                else if ( chp_area.isChecked())
                {
                    Observable.fromIterable(MyAreaName)
                            .filter( AreaName -> AreaName.getStrArea().toLowerCase().contains(newText.toString())  )
                            .toList().subscribe( filterList -> searchByAreaAdapter.setData(filterList)


                            );
                }
                else if (chp_category.isChecked())
                {


                    Observable.fromIterable(MyCategory)
                            .filter( category -> category.getStrCategory().toLowerCase().contains(newText.toString())  )
                            .toList().subscribe( filterList -> searchByCategoryAdapter.setData(filterList)


                            );

                }


                return  false;

            }
        });

    }


    @Override
    public void getCategory(List<Category> AllCategories) {

        MyCategory = AllCategories;
        searchByCategoryAdapter = new SearchByCategoryAdapter( getContext() , AllCategories );
        rv_categoryInSerach.setAdapter(searchByCategoryAdapter);
        rv_categoryInSerach.setLayoutManager( new GridLayoutManager( getContext() ,2 ));


    }

    @Override
    public void getArea(List<AreaName> AllAreas) {

        MyAreaName = AllAreas;
        searchByAreaAdapter = new SearchByAreaAdapter( getContext(),AllAreas);
        rv_AreaInSerach.setAdapter(searchByAreaAdapter);
        rv_AreaInSerach.setLayoutManager( new GridLayoutManager( getContext() , 4));
    }

    @Override
    public void getIngrediant(List<Ingrediant> AllIngrediants) {

        MyIngrediants  = AllIngrediants;
        ingrediantSearchAdapter = new IngrediantSearchAdapter( getContext(),AllIngrediants);
        rv_IngrediantInSearch.setAdapter(ingrediantSearchAdapter);
        rv_IngrediantInSearch.setLayoutManager( new GridLayoutManager( getContext() , 2));
    }

    @Override
    public void setErrorMessage(String message) {

    }
}