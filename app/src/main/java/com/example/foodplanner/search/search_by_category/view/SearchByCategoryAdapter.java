package com.example.foodplanner.search.search_by_category.view;

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
import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;
import com.example.foodplanner.search.SearchFragmentDirections;


import java.util.List;

public class SearchByCategoryAdapter extends RecyclerView.Adapter<SearchByCategoryAdapter.SearchByCategoryViewHolder>  {

    Context context;
    List<Category> CategoryResponce;

    public SearchByCategoryAdapter(Context context, List<Category> category /*el mafrod hadef hena ref l inteface el onFavClickListner */  ){
        this.context = context;
        this.CategoryResponce = category ;

    }

    @NonNull
    @Override
    public SearchByCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_by_category,parent,false);
        SearchByCategoryViewHolder viewHolder = new SearchByCategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByCategoryViewHolder holder, int position) {
        Category category =CategoryResponce.get(position);
        holder.tv_categoryName.setText(category.getStrCategory());

        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFToMealByCategoryFragment action =
                        SearchFragmentDirections.actionSearchFToMealByCategoryFragment(category.getStrCategory());
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CategoryResponce.size() ;
    }

    public void setData (List<Category> CategoryResponce )
    {
        this.CategoryResponce = CategoryResponce;
        notifyDataSetChanged();
    }


    public class SearchByCategoryViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img ;
        TextView tv_categoryName;

        public SearchByCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_categoryImg);
            tv_categoryName = itemView.findViewById(R.id.tv_NameOfCategory);
        }
    }}
