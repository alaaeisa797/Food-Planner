package com.example.foodplanner.plane_meals.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;

import com.example.foodplanner.log_in.view.LoginActivity;
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.R;
import com.example.foodplanner.plane_meals.presenter.PlaneMealPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements PlaneMealView , SetOnDeleteFromPlanListner {

    ImageView imageView;
    PlaneMealPresenter planeMealPresenter;

    PlaneMealAdapter planeMealAdapter;
    RecyclerView recyclerView;
    FirebaseAuth auth ;

    public CalendarFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_calender);
        imageView = view.findViewById(R.id.iv_calenderfrag);
        auth = FirebaseAuth.getInstance();
        planeMealPresenter = new PlaneMealPresenter( this , getContext());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
                else {
                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                planeMealPresenter.setData(String.valueOf(dayOfMonth));

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }}
        });

    }

    @Override
    public void getPlanMeals(List<PlanMealDetail> planMealDetailList) {




            planeMealAdapter = new PlaneMealAdapter(this.getContext(), planMealDetailList, this);
            recyclerView.setAdapter(planeMealAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }


    @Override
    public void setErrorMessage(String message) {

    }

    @Override
    public void onDeleteClick(PlanMealDetail p) {

        planeMealPresenter.deleteFromPlan(p);
        planeMealPresenter.deletePlanMealsFromFirebase(auth.getUid() , p.getStrMeal());
    }
}