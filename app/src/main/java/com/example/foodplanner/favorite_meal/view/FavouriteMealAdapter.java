package com.example.foodplanner.favorite_meal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.R;

import java.util.List;

public class FavouriteMealAdapter extends RecyclerView.Adapter<FavouriteMealAdapter.FavouriteMealViewHolder>
{

    Context context;
    List<MealsDetailes> FavMealDetail;
    OnDeleteClickListner onDeleteClickListner ;

    public FavouriteMealAdapter(Context context, List<MealsDetailes> MealsDetails /*el mafrod hadef hena ref l inteface lessa ha3melo */ , OnDeleteClickListner p) {
        this.context = context;
        this.FavMealDetail = MealsDetails;
        this.onDeleteClickListner = p;

    }


    @NonNull
    @Override
    public FavouriteMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fav_meal_item, parent, false);
        FavouriteMealViewHolder viewHolder = new FavouriteMealViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMealViewHolder holder, int position) {
      MealsDetailes mealsDetailes =  FavMealDetail.get(position);

        holder.tv_categoryName.setText(mealsDetailes.getStrMeal());
        Glide.with(context).load(mealsDetailes.getStrMealThumb()).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavouriteMealsFragmentDirections.ActionFavouriteMealsFragmentToMealDetailsFragment action =
                        FavouriteMealsFragmentDirections.actionFavouriteMealsFragmentToMealDetailsFragment(mealsDetailes.getStrMeal());
                Navigation.findNavController(v).navigate(action);
            }

        });
        holder.delet_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListner.onDeleteClick(mealsDetailes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return FavMealDetail.size();
    }

    public class FavouriteMealViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView tv_categoryName;

        ImageView delet_image;

        public FavouriteMealViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_favMealImg);
            tv_categoryName = itemView.findViewById(R.id.tv_favMealName);
            delet_image = itemView.findViewById(R.id.iv_deleteFromMealFavouriteList);
        }
    }
}
