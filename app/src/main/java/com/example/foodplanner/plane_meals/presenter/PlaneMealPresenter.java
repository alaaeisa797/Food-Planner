package com.example.foodplanner.plane_meals.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplanner.DB.DAO;
import com.example.foodplanner.DB.MyRoomDB;
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.plane_meals.view.PlaneMealView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlaneMealPresenter {

    PlaneMealView planeMealView;
    Context context ;
    DAO dao ;
    MyRoomDB myRoomDB ;
    FirebaseFirestore fdb ;

    public PlaneMealPresenter(PlaneMealView planeMealView, Context context) {
        this.planeMealView = planeMealView;
        this.context = context;
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.productsDAO();
        fdb = FirebaseFirestore.getInstance();

    }
    public Task<Void> deletePlanMealsFromFirebase(String uid, String mealId) {
        return fdb.collection("users").document(uid)
                .collection("plan").document(mealId)
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
     public void setData ( String day )
     {
         Log.d("TAG", "sendFavData: Method called");

         Flowable<List<PlanMealDetail>> flowable = dao.getPlansByDay(day);
         Log.d("TAG", "sendFavData: Flowable created");

         flowable.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new FlowableSubscriber<List<PlanMealDetail>>() {
                     @Override
                     public void onSubscribe(@NonNull Subscription s) {
                         Log.d("TAG", "onSubscribe: Subscribed");
                         s.request(Long.MAX_VALUE);
                     }

                     @Override
                     public void onNext(List<PlanMealDetail> detailsMealItems) {
                         Log.d("TAG", "onNext: Received " + detailsMealItems.size() + " products");
                         planeMealView.getPlanMeals(detailsMealItems);
                     }

                     @Override
                     public void onError(Throwable t) {
                         Log.e("TAG", "onError: Error occurred", t);
                         planeMealView.setErrorMessage(t.getMessage());
                     }

                     @Override
                     public void onComplete() {
                         Log.d("TAG", "onComplete: Completed");
                     }
                 });
     }

     public void deleteFromPlan ( PlanMealDetail planMealDetail)
     {
         new Thread( )
         {
             @Override
             public void run() {
                 super.run();
                 dao.deletePlan(planMealDetail.getStrMeal());
             }
         }.start();

     }
}
