package com.example.foodplanner.category.view;

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
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;
import com.example.foodplanner.hoomScreen.view.HomeFragmentDirections;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>  {

    Context context;
    List<Category> CategoryResponce;

    public CategoryAdapter(Context context, List<Category> category /*el mafrod hadef hena ref l inteface el onFavClickListner */  ){
        this.context = context;
        this.CategoryResponce = category ;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.catigores,parent,false);
        CategoryAdapter.CategoryViewHolder viewHolder = new CategoryAdapter.CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category =CategoryResponce.get(position);
        holder.tv_categoryName.setText(category.getStrCategory());

        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToMealByCategoryFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMealByCategoryFragment(category.getStrCategory());
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CategoryResponce.size() ;
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView tv_categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_categoryImg);
            tv_categoryName = itemView.findViewById(R.id.tv_NameOfCategory);
        }
    }}
