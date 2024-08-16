package com.example.foodplanner.hoomScreen.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.log_in.view.LoginActivity;
import com.example.foodplanner.Model.RandomMealOfTheDad;
import com.example.foodplanner.R;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MealOfTheDayViewHolder> {

    Context context;
    List<RandomMealOfTheDad> MealOFTheResponce;

   // OnFavClickListner onFavClickListner;

///////////////////////////////////// rakkez
    public HomePageAdapter(Context context, List<RandomMealOfTheDad> products /*el mafrod hadef hena ref l inteface el onFavClickListner */  ){
        this.context = context;
        this.MealOFTheResponce = products ;
          //  productsResponse = products ;
          // this.onFavClickListner = listner ;
    }

    @NonNull
    @Override
    public MealOfTheDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.daily_inspiration,parent,false);
        MealOfTheDayViewHolder viewHolder = new MealOfTheDayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealOfTheDayViewHolder holder, int position) {
        RandomMealOfTheDad meal = MealOFTheResponce.get(position);
        holder.title.setText(meal.getStrMeal());

        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.isGuest) {

                    new AlertDialog.Builder(context)
                            .setTitle("Sign Up Required")
                            .setMessage("Please sign up to proceed. Do you want to sign up now?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Intent intent = new Intent(context, LoginActivity.class);
                                    context.startActivity(intent);
                                    if (context instanceof Activity) {
                                        ((Activity) context).finish();
                                    }

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
                else {
                    HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                            HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(meal.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
                }
        });




    }

    @Override
    public int getItemCount() {
        return MealOFTheResponce.size();
    }

    public void updateProducts(List<RandomMealOfTheDad> mealOfTheDay) {
        this.MealOFTheResponce = mealOfTheDay;
        notifyDataSetChanged();
    }

    public class MealOfTheDayViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;

        ImageView imageView;



        public MealOfTheDayViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_mealOftheDay);
            title = itemView.findViewById(R.id.tv_titleOfDailyInspiration);

        }
    }
}
