package com.example.foodplanner.favorite_meal.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.log_in.view.LoginActivity;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.R;
import com.example.foodplanner.favorite_meal.presenter.FavouriteMealsPresenter;
import com.example.foodplanner.hoomScreen.view.HomePageActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class FavouriteMealsFragment extends Fragment implements FavouriteMealsView, OnDeleteClickListner {

    RecyclerView rv_favMeals;

    FavouriteMealsPresenter favouriteMealsPresenter;

    FavouriteMealAdapter favouriteMealAdapter;
    FirebaseAuth auth ;


    public FavouriteMealsFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favourite_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_favMeals = view.findViewById(R.id.rv_favMeals);

        favouriteMealsPresenter = new FavouriteMealsPresenter( this.getContext() , this );
        favouriteMealsPresenter.sendFavData();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void getFavMeals(List<MealsDetailes> AllFavouriteMeals) {
        if (LoginActivity.isGuest) {

            new AlertDialog.Builder(getContext())
                    .setTitle("Sign Up Required")
                    .setMessage("Please sign up to proceed. Do you want to sign up now?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            getContext().startActivity(intent);
                            getActivity().finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(getContext(), HomePageActivity.class);
                            getContext().startActivity(intent);
                            getActivity().finish();
                        }
                    })
                    .show();
 }
 else {
            favouriteMealAdapter = new FavouriteMealAdapter(this.getContext(), AllFavouriteMeals, this);
            rv_favMeals.setAdapter(favouriteMealAdapter);
            rv_favMeals.setLayoutManager(new LinearLayoutManager(this.getContext()));
        }
    }

    @Override
    public void setErrorMessage(String message) {

    }

    @Override
    public void onDeleteClick(MealsDetailes p) {

        favouriteMealsPresenter.deleteMeal(p);
        favouriteMealsPresenter.deleteFavMealsFromFirebase(auth.getUid(),p.getStrMeal());
    }
}