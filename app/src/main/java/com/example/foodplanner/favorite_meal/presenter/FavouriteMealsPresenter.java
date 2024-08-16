package com.example.foodplanner.favorite_meal.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.DB.DAO;
import com.example.foodplanner.DB.MyRoomDB;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.favorite_meal.view.FavouriteMealsView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouriteMealsPresenter {

    DAO dao ;
    MyRoomDB myRoomDB;

    FavouriteMealsView favouriteMealsView ;
    Context context ;
    FirebaseFirestore fdb ;

    public FavouriteMealsPresenter(Context context ,FavouriteMealsView favouriteMealsView) {
        this.favouriteMealsView = favouriteMealsView;

        myRoomDB = MyRoomDB.getInstance(context.getApplicationContext());
        dao = myRoomDB.productsDAO();
        this.context = context;
        fdb = FirebaseFirestore.getInstance();


    }

    public void deleteMeal ( MealsDetailes mealsDetailes )
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteMealDetails(mealsDetailes.getStrMeal());
            }
        }.start();

    }
    public Task<Void> deleteFavMealsFromFirebase(String uid, String mealId) {
        return fdb.collection("users").document(uid)
                .collection("meals").document(mealId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: Meal deleted successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.d("TAG", "onFailure: Failed to delete meal: " + e.getMessage());
                    }
                });
    }

    public void sendFavData() {
        Log.d("TAG", "sendFavData: Method called");

        Flowable<List<MealsDetailes>> flowable = dao.getAllMeals();
        Log.d("TAG", "sendFavData: Flowable created");

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<MealsDetailes>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        Log.d("TAG", "onSubscribe: Subscribed");
                        s.request(Long.MAX_VALUE); // Requests the Flowable to emit all available items (s.request(Long.MAX_VALUE);)
                        // ensuring that the subscriber receives all data without being throttled by (backpressure).
                    }

                    @Override
                    public void onNext(List<MealsDetailes> productInfos) {
                        Log.d("TAG", "onNext: Received " + productInfos.size() + " products");
                        favouriteMealsView.getFavMeals(productInfos);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG", "onError: Error occurred", t);
                        favouriteMealsView.setErrorMessage(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: Completed");
                    }
                });
    }



}
