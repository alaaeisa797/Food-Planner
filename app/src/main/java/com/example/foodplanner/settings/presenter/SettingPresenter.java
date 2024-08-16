package com.example.foodplanner.settings.presenter;

import android.content.Context;

import com.example.foodplanner.DB.DAO;
import com.example.foodplanner.DB.MyRoomDB;

public class SettingPresenter {
    DAO dao;
    MyRoomDB myRoomDB;

    public SettingPresenter(Context context) {
        myRoomDB = MyRoomDB.getInstance(context);
        dao = myRoomDB.productsDAO();
    }

    public void deleteAllFavMeals ()
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteAllFavoriteMealsFromRoom();
            }
        }.start();

    }

    public void deleteAllPlanMeals ()
    {
        new Thread()
        {
            @Override
            public void run() {
                super.run();
                dao.deleteAllPlanMealsFromRoom();
            }
        }.start();

    }
}
