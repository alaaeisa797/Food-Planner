package com.example.foodplanner.DB;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@androidx.room.Dao
public interface DAO {

    @Query("SELECT * FROM favMeals")  // lw 8yart esm el tabel hena hytla3lak compile errror 3shan el CompileTimw Verfication onsql Querirs elli hwa advantage 3nd el Room
    Flowable <List<MealsDetailes>> getAllMeals(); // 5alle balak me el liveData .. nan kda b observe lel data elli rag3a mel DB




    //@Insert (onConflict = OnConflictStrategy.IGNORE) // de 3shan lw 7assal w 3mlt insert l 7aga mareten
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertMealDetails(MealsDetailes mealDetailes);

    @Query("DELETE FROM favMeals WHERE strMeal = :strMeal") // lazem ykon Qeuery 3shan el livedata mbtsht8alsh 8er ma3 Query

    void deleteMealDetails(String strMeal);

  @Query("SELECT * FROM PlanMeals WHERE dayOfMonth = :day")
    Flowable<List<PlanMealDetail>> getPlansByDay(String day);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlan(PlanMealDetail mealDetailes);

    @Query("DELETE FROM PlanMeals WHERE strMeal = :strMeal") // lazem ykon Qeuery 3shan el livedata mbtsht8alsh 8er ma3 Query

    void deletePlan(String strMeal);


    @Query("DELETE FROM favMeals")
    void deleteAllFavoriteMealsFromRoom();

    @Query("DELETE FROM PlanMeals")
    void deleteAllPlanMealsFromRoom();
}