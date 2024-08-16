package com.example.foodplanner.search.search_by_ingrediants.view;

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
import com.example.foodplanner.Model.Ingrediant;
import com.example.foodplanner.R;
import com.example.foodplanner.search.SearchFragmentDirections;
import com.example.foodplanner.search.search_by_area.view.SearchByAreaAdapter;

import java.util.List;

public class IngrediantSearchAdapter extends RecyclerView.Adapter<IngrediantSearchAdapter.SearchByIngrediantViewHolder>    {
    Context context;
    List<Ingrediant> IngrediantResponce;

    private static final String URL = "https://www.themealdb.com/images/ingredients/";
    private static final String END_POINT = "-Small.png";

    public IngrediantSearchAdapter(Context context, List<Ingrediant> ingrediantResponce) {
        this.context = context;
        IngrediantResponce = ingrediantResponce;
    }

    @NonNull
    @Override
    public SearchByIngrediantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.ingrediant_search_item,parent,false);
        IngrediantSearchAdapter.SearchByIngrediantViewHolder viewHolder = new IngrediantSearchAdapter.SearchByIngrediantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByIngrediantViewHolder holder, int position) {

        Ingrediant CurrentIngrediant =IngrediantResponce.get(position);
        holder.tv_Ingredantname.setText(CurrentIngrediant.getStrIngredient());

        Glide.with(context).load(URL+CurrentIngrediant.getStrIngredient()+END_POINT).into(holder.iv_img);


        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFToMealByIngrediantFragment action =
                        SearchFragmentDirections.actionSearchFToMealByIngrediantFragment(CurrentIngrediant.getStrIngredient());
                Navigation.findNavController(v).navigate(action);
            }
        });



    }

    public void setData ( List<Ingrediant> IngrediantResponce )
    {
        this.IngrediantResponce = IngrediantResponce;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return IngrediantResponce.size();
    }

    public class SearchByIngrediantViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Ingredantname;
        ImageView iv_img;

        public SearchByIngrediantViewHolder(@NonNull View itemView) {
            super(itemView);
            //img = itemView.findViewById(R.id.iv_categoryImg);
            tv_Ingredantname = itemView.findViewById(R.id.ingredient_details);
            iv_img = itemView.findViewById(R.id.iv_searchIngrediantImg);
        }
    }
}
