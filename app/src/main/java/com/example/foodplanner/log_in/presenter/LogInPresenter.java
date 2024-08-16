package com.example.foodplanner.log_in.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.DB.DAO;
import com.example.foodplanner.DB.MyRoomDB;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LogInPresenter {

    FirebaseFirestore db ;
    DAO dao;
    MyRoomDB myRoomDB ;


    public LogInPresenter(Context context) {
        db = FirebaseFirestore.getInstance();
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.productsDAO();
    }

    public Task<List<MealsDetailes>> getUserFavoriteMeals(String uid) {
        return db.collection("users")
                .document(uid)
                .collection("meals")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        List<MealsDetailes> meals = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                             MealsDetailes meal = document.toObject(MealsDetailes.class);
                            if (meal != null) {
                                meals.add(meal);
                            }
                        }
                        return meals;
                    } else {
                        throw task.getException();
                    }
                });

    }
    public Task<List<PlanMealDetail>> getUserPlaneMeals(String uid) {
        return db.collection("users")
                .document(uid)
                .collection("plan")
                .get()
                .continueWith(task -> {
                    if (task.isSuccessful()) {
                        List<PlanMealDetail> meals = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            PlanMealDetail meal = document.toObject(PlanMealDetail.class);
                            if (meal != null) {
                                meals.add(meal);
                            }
                        }
                        return meals;
                    } else {
                        throw task.getException();
                    }
                });

    }

    public void addallfavmealfromRemotDBToLocal ( List<MealsDetailes> mealdetailes )
    {
        for ( MealsDetailes p : mealdetailes )
        {
            dao.insertMealDetails(p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.d("TAG", "onComplete: inserted ");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            });

        }


    }

    public void addallPlanmealfromRemotDBToLocal ( List<PlanMealDetail> mealdetailes )
    {
        for ( PlanMealDetail p : mealdetailes )
        {
            dao.insertPlan(p).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    Log.d("TAG", "onComplete: inserted ");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.d("TAG", e.getMessage());
                                }
                            });

        }


    }

}
