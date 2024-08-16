package com.example.foodplanner.log_in.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.R;
import com.example.foodplanner.sing_up.view.RegisterActivity;
import com.example.foodplanner.hoomScreen.view.HomePageActivity;
import com.example.foodplanner.log_in.presenter.LogInPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btn_login;

    TextView tv_goToSignUp;
    TextView tv_guest ;
    private FirebaseAuth mAuth;
    public static boolean isGuest ;
    LogInPresenter logInPresenter;
    List <MealsDetailes> list ;
    List <PlanMealDetail> Planlist ;


   @Override
   protected void onStart() {
       super.onStart();
       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       if(user!=null)
       {
           startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
           finish();
       }
   }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isGuest = false ;
        mAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.et_email_login);
        password = findViewById(R.id.et_password_login);
        btn_login = findViewById(R.id.btn_login);
        tv_goToSignUp = findViewById(R.id.tv_signUpFromLogin);
        tv_guest = findViewById(R.id.tv_guestMode);
        logInPresenter = new LogInPresenter(this);


        tv_goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });


        tv_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGuest = true ;
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                signInWithEmailAndPassword(userEmail, userPassword);
            }
        });

    }


    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();

                            logInPresenter.getUserFavoriteMeals(mAuth.getUid())
                                    .addOnCompleteListener(new OnCompleteListener<List<MealsDetailes>>() {
                                        @Override
                                        public void onComplete(@NonNull Task<List<MealsDetailes>> task) {
                                            if (task.isSuccessful()) {
                                                list = task.getResult();
                                                if (list != null && !list.isEmpty()) {
                                                    logInPresenter.addallfavmealfromRemotDBToLocal(list);

                                                } else { // case no fav meals
                                                    Log.d("TAG", "No favorite found.");
                                                }
                                            } else { // case failute in retriving data
                                                Log.e("TAG", "Error retrieving meals", task.getException());
                                                Toast.makeText(LoginActivity.this, "Failed to retrieve meals.", Toast.LENGTH_SHORT).show();
                                            }


                                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                            logInPresenter.getUserPlaneMeals(mAuth.getUid())
                                    .addOnCompleteListener(new OnCompleteListener<List<PlanMealDetail>>() {
                                        @Override
                                        public void onComplete(@NonNull Task<List<PlanMealDetail>> task) {
                                            if (task.isSuccessful()) {
                                                Planlist = task.getResult();
                                                if (Planlist != null && !Planlist.isEmpty()) {
                                                    logInPresenter.addallPlanmealfromRemotDBToLocal(Planlist);
                                                     } else {
                                                    // case no Plan meals

                                                    Log.d("TAG", "No Plan meals found.");
                                                }
                                            } else {
                                                // case failute in retriving data
                                                Log.e("TAG", "Error retrieving meals", task.getException());
                                                Toast.makeText(LoginActivity.this, "Failed to retrieve meals.", Toast.LENGTH_SHORT).show();
                                            }


                                            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}