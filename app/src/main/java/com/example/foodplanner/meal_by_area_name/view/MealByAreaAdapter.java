package com.example.foodplanner.meal_by_area_name.view;

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
import com.example.foodplanner.Model.MealByArea;
import com.example.foodplanner.R;

import java.util.List;

public class MealByAreaAdapter extends RecyclerView.Adapter<MealByAreaAdapter.MealByAreaViewHolder> {
    Context context;
    List<MealByArea> MealByAreaResponce;

    public MealByAreaAdapter(Context context, List<MealByArea> mealByAreaResponce) {
        this.context = context;
        MealByAreaResponce = mealByAreaResponce;
    }

    @NonNull
    @Override
    public MealByAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_by_area_item,parent,false);
        MealByAreaAdapter.MealByAreaViewHolder viewHolder = new MealByAreaAdapter.MealByAreaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealByAreaViewHolder holder, int position) {
        MealByArea mealByArea =MealByAreaResponce.get(position);
        holder.tv_MealByAreaName.setText(mealByArea.getStrMeal());

        Glide.with(context).load(mealByArea.getStrMealThumb()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
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
                    MealByAreaFragmentDirections.ActionMealByAreaFragmentToMealDetailsFragment action =
                            MealByAreaFragmentDirections.actionMealByAreaFragmentToMealDetailsFragment(mealByArea.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return MealByAreaResponce.size();
    }


    public class MealByAreaViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView tv_MealByAreaName;

        public MealByAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_mealByIngrediantImgitem);
            tv_MealByAreaName = itemView.findViewById(R.id.tv_mealNameByIngrediantitem);
        }
    }
}
