package com.example.foodplanner.sing_up.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.hoomScreen.view.HomePageActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class RegisterActivity extends AppCompatActivity {

    EditText email ;
    EditText password ;
    Button btn_signUp;
    ImageView btn_google;

    FirebaseAuth mAuth;

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.et_email_login);
        password = findViewById(R.id.et_password_login);
        btn_signUp = findViewById(R.id.btn_login);


        btn_google = findViewById(R.id.btn_google);
        FirebaseApp.initializeApp(this);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();


        googleSignInClient = GoogleSignIn.getClient(RegisterActivity.this, options);
        mAuth = FirebaseAuth.getInstance();

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(intent);
            }
        });



        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String mail , pass ;
                mail = String.valueOf(email.getText()) ;
                pass = password.getText().toString();

                if (TextUtils.isEmpty(mail))
                {
                    Toast.makeText( RegisterActivity.this , "Enter Email " , Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass))
                {
                    Toast.makeText( RegisterActivity.this , "Enter Password " , Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication Sussefully.",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this, HomePageActivity.class));
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
    }
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
            try {
                GoogleSignInAccount googleSignInAccount = accountTask.getResult(ApiException.class);
                String idToken = googleSignInAccount.getIdToken();
                Log.d("Auth_pages", "Google ID Token: " + idToken); // Log the ID token for debugging
                AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken, null);
                mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication success."+ mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, HomePageActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.e("Auth_pages", "Authentication failed", task.getException()); // Log the exception for debugging
                        }
                    }
                });
            } catch (ApiException e) {
                e.printStackTrace();
                Log.e("Auth_pages", "Google sign in failed", e); // Log the exception for debugging
            }
        }
    });
}