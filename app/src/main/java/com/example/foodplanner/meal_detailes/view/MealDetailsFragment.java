package com.example.foodplanner.meal_detailes.view;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.R;
import com.example.foodplanner.meal_detailes.presenter.MealsDetailesPresenter;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealsDetailesView {

TextView mealTitle ;
ImageView iv_mealimg;
RecyclerView rv_ingrediants ;

TextView mealInstructions ;
YouTubePlayerView youTubePlayerView;

ImageView favouriteImage;

MealDetailsAdapter mealsByCategoryAdapter;

MealsDetailesPresenter mealsDetailesPresenter;

MealsDetailes mealsDetailes;

ImageView iv_calnder;
 PlanMealDetail planMealDetail;
 FirebaseAuth auth ;


    public MealDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseApp.initializeApp(getContext());
        mealTitle = view.findViewById( R.id.tv_mealName);
        favouriteImage = view.findViewById( R.id.iv_addMealToFavourites);
        iv_mealimg = view.findViewById( R.id.iv_mealImg);
        rv_ingrediants = view.findViewById( R.id.rv_ingrediants);
        mealInstructions = view.findViewById( R.id.tv_stepsOfMeal);
        youTubePlayerView = view.findViewById( R.id.youTubeVideos);

        iv_calnder = view.findViewById(R.id.iv_calender);
        auth = FirebaseAuth.getInstance();

        getLifecycle().addObserver(youTubePlayerView);//>>

        mealsDetailesPresenter = new MealsDetailesPresenter(this , this.getContext());
        String s = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealName();
        mealsDetailesPresenter.getMealDetails(s);


        favouriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealsDetailesPresenter.insertFavProuduct(mealsDetailes);
                mealsDetailesPresenter.insertFavMealsIntoFireBase(auth.getUid() , mealsDetailes);

            }
        });

        iv_calnder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                       //presnter .insert ( dayOfMonth )
                                planMealDetail.setDayOfMonth(dayOfMonth);
                                mealsDetailesPresenter.addPlanMeal(planMealDetail);
                                mealsDetailesPresenter.insertPlanMealsIntoFireBase( auth.getUid(),planMealDetail);


                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });



        }

    @Override
    public void getMealDetail(List<MealsDetailes> MealsDetailes) {
      //  mealsDetailes = MealsDetailes.get(0);

        planMealDetail = Converter.convert(MealsDetailes.get(0));
        mealTitle.setText(MealsDetailes.get(0).getStrMeal());
        mealInstructions.setText(MealsDetailes.get(0).getStrInstructions());
        Glide.with(getContext()).load(MealsDetailes.get(0).getStrMealThumb()).into(iv_mealimg);
        mealsDetailes=MealsDetailes.get(0);//>>>>>>>>>

        mealsByCategoryAdapter =new MealDetailsAdapter(getContext(),MealsDetailes);
        rv_ingrediants.setAdapter(mealsByCategoryAdapter);
        rv_ingrediants.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
 if (MealsDetailes.get(0).getStrYoutube().equals(null)&&MealsDetailes.get(0).getStrYoutube().isEmpty())
 {
     youTubePlayerView.setVisibility(View.GONE);
 }
 else
 {
     String videoUrl = MealsDetailes.get(0).getStrYoutube();
     String videoId = videoUrl.substring(videoUrl.lastIndexOf("v=") + 2);
     youTubePlayer.loadVideo(videoId, 0);
 }

            }
        });

    }



    @Override
    public void setErrorMessage(String message) {

    }
}