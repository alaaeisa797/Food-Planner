package com.example.foodplanner.settings.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.log_in.view.LoginActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.hoomScreen.view.HomePageActivity;
import com.example.foodplanner.settings.presenter.SettingPresenter;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment {

    CardView cd_logOut ;
    FirebaseAuth auth;
    SettingPresenter settingPresenter;

    public SettingsFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cd_logOut = view.findViewById(R.id.cv_logOut);
        auth = FirebaseAuth.getInstance();
        settingPresenter = new SettingPresenter(getContext());
        cd_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

            }
        });
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (LoginActivity.isGuest) {

                    new AlertDialog.Builder(getContext())
                            .setTitle("Sign Up Required")
                            .setMessage("Please sign up to proceed. Do you want to sign up now?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    getContext().startActivity(intent);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getContext(), HomePageActivity.class);
                                    getContext().startActivity(intent);

                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
                else if(auth.getCurrentUser()==null)
                {
                    settingPresenter.deleteAllFavMeals();
                    settingPresenter.deleteAllPlanMeals();
                    if (getContext() != null) {
                        getActivity().finish();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    } else {
                        Log.e("AccountFragment", "Context is null");
                    }

                }
            }
        });
    }
}