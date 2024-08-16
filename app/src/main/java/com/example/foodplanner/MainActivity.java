package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.log_in.view.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Handler hander = new Handler(Looper.getMainLooper());
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        runnable = new Runnable()
        {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        hander.postDelayed(runnable,3000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hander.removeCallbacks(runnable);
    }


}
