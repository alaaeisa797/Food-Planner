package com.example.foodplanner.DB;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;

@Database(entities = {MealsDetailes.class, PlanMealDetail.class },version = 2) // da el pojo aw el table el wa7ed 3ndi fel DB
public abstract class MyRoomDB extends RoomDatabase {
    private static MyRoomDB instance = null; // lazem el ref da ykon static


    public abstract DAO productsDAO();

    public static synchronized MyRoomDB getInstance(Context context) // lazem tkon static bardo
    {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyRoomDB.class,"myRoomDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
