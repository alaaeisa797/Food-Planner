package com.example.foodplanner.meal_by_category.view;

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
import com.example.foodplanner.Model.MealByCategory;
import com.example.foodplanner.R;

import java.util.List;

public class MealsByCategoryAdapter extends RecyclerView.Adapter<MealsByCategoryAdapter.MealsByCategoryViewHolder> {
    Context context;
    List<MealByCategory> meals;

    public MealsByCategoryAdapter(Context context, List<MealByCategory> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_by_category_item,parent,false);
        MealsByCategoryViewHolder mealsByCategoryViewHolder=new MealsByCategoryViewHolder(view);
        return mealsByCategoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsByCategoryViewHolder holder, int position) {
        MealByCategory currntMealsByCategoryItem = meals.get(position);
        holder.title.setText(currntMealsByCategoryItem.getStrMeal());
        Glide.with(context).load(currntMealsByCategoryItem.getStrMealThumb()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //test
                // Toast.makeText(context,currntMealsByCategoryItem.getStrMeal(), Toast.LENGTH_SHORT).show();

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
                } else {
                    MealByCategoryFragmentDirections.ActionMealByCategoryFragmentToMealDetailsFragment action =
                            MealByCategoryFragmentDirections.actionMealByCategoryFragmentToMealDetailsFragment(currntMealsByCategoryItem.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    protected class MealsByCategoryViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView img;
        public MealsByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_mealNameByIngrediantitem);
            img = itemView.findViewById(R.id.iv_mealByIngrediantImgitem);
        }
    }
}
