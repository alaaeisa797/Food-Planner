package com.example.foodplanner.meal_by_ingrediant.view;

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
import com.example.foodplanner.Model.MealByIngredant;
import com.example.foodplanner.R;

import java.util.List;

public class MealByIngrediantAdapter extends RecyclerView.Adapter<MealByIngrediantAdapter.MealByIngrediantViewHolder> {
    Context context;
    List<MealByIngredant> MealByIngrediantResponce;

    public MealByIngrediantAdapter(Context context, List<MealByIngredant> mealByIngrediantResponce) {
        this.context = context;
        MealByIngrediantResponce = mealByIngrediantResponce;
    }

    @NonNull
    @Override
    public MealByIngrediantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.meal_ingrediant_item,parent,false);
        MealByIngrediantAdapter.MealByIngrediantViewHolder viewHolder = new MealByIngrediantAdapter.MealByIngrediantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealByIngrediantViewHolder holder, int position) {
        MealByIngredant mealByIngrediant =MealByIngrediantResponce.get(position);
        holder.tv_MealByIngrediantName.setText(mealByIngrediant.getStrMeal());

        Glide.with(context).load(mealByIngrediant.getStrMealThumb()).into(holder.img);

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
                else
                {
                    MealByIngrediantFragmentDirections.ActionMealByIngrediantFragmentToMealDetailsFragment action =
                            MealByIngrediantFragmentDirections.actionMealByIngrediantFragmentToMealDetailsFragment(mealByIngrediant.getStrMeal());
                    Navigation.findNavController(v).navigate(action);
                }
                }
        });
    }

    @Override
    public int getItemCount() {
        return MealByIngrediantResponce.size();
    }

    public class MealByIngrediantViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView tv_MealByIngrediantName;

        public MealByIngrediantViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_mealByIngrediantImgitem);
            tv_MealByIngrediantName = itemView.findViewById(R.id.tv_mealNameByIngrediantitem);
        }
    }
}
