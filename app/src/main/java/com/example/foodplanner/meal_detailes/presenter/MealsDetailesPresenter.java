package com.example.foodplanner.meal_detailes.presenter;

import android.content.Context;
import android.util.Log;

import com.example.foodplanner.DB.DAO;
import com.example.foodplanner.DB.MyRoomDB;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.meal_detailes.view.MealsDetailesView;
import com.example.foodplanner.netwrok.APIClient;
import com.example.foodplanner.netwrok.MealDetailCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsDetailesPresenter implements MealDetailCallback {
    APIClient apiClint;
    MealsDetailesView mealsDetailesView ;
    DAO dao ;
    MyRoomDB DB ;
    FirebaseFirestore db ;
    public MealsDetailesPresenter(MealsDetailesView  mealsDetailesView , Context context) {
        this.mealsDetailesView = mealsDetailesView;
        this.apiClint = new APIClient();
        // apiClint.makeNetworkCall(this);

        DB = MyRoomDB.getInstance(context.getApplicationContext());
        dao = DB.productsDAO();
       db = FirebaseFirestore.getInstance();
    }

    public void getMealDetails(String mealName )
    {
        apiClint.makeNetworkCall(this,mealName);
    }

    @Override
    public void onSuccessResult(List<MealsDetailes> AllMealsDetailes) {
        mealsDetailesView.getMealDetail(AllMealsDetailes);
    }

    // for delete use (.delete)
//    public Task <Void> getFavMealsIntoFireBase (String uid , List<MealsDetailes> mealsDetailes )
//    {
//        return   db.collection("users").document(uid)
//                .collection("meals").document(mealsDetailes.get(0).getStrMeal())
//                .get(mealsDetailes).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("TAG", "onSuccess: 3ezzat ");
//                    }
//                }) .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@androidx.annotation.NonNull Exception e) {
//                        Log.d("TAG", "onFailure: 3ezzat " +e.getMessage());
//                    }
//                }) ;
//
//    }
    public Task <Void> insertFavMealsIntoFireBase (String uid , MealsDetailes mealsDetailes)
    {
      return   db.collection("users").document(uid)
                .collection("meals").document(mealsDetailes.getStrMeal())
                .set(mealsDetailes).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: 3ezzat ");
                    }
                }) .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@androidx.annotation.NonNull Exception e) {
                      Log.d("TAG", "onFailure: 3ezzat " +e.getMessage());
                  }
              }) ;

    }

    public Task <Void> insertPlanMealsIntoFireBase (String uid , PlanMealDetail planMealsDetailes)
    {
        return   db.collection("users").document(uid)

                .collection("plan").document(planMealsDetailes.getStrMeal())
                .set(planMealsDetailes).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSuccess: 3ezzat ");
                    }
                }) .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.d("TAG", "onFailure: 3ezzat " +e.getMessage());
                    }
                }) ;

    }
    public void insertFavProuduct ( MealsDetailes mealsDetailes )
    {
   dao.insertMealDetails(mealsDetailes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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

    public void addPlanMeal (PlanMealDetail planMealDetail)
    {

        dao.insertPlan(planMealDetail).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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

    @Override
    public void onFailureResult(String errMsg) {
        mealsDetailesView.setErrorMessage(errMsg);
    }
}
