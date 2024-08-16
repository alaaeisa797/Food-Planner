package com.example.foodplanner.search.search_by_area.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.AreaName;
import com.example.foodplanner.Model.Ingrediant;
import com.example.foodplanner.R;
import com.example.foodplanner.search.SearchFragmentDirections;

import java.util.List;

public class SearchByAreaAdapter  extends RecyclerView.Adapter<SearchByAreaAdapter.SearchByAreaViewHolder>{

    Context context;
    List<AreaName> AreaNameResponce;

    public SearchByAreaAdapter(Context context, List<AreaName> areaNameResponce) {
        this.context = context;
        AreaNameResponce = areaNameResponce;
    }

    @NonNull
    @Override
    public SearchByAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.area_item,parent,false);
        SearchByAreaAdapter.SearchByAreaViewHolder viewHolder = new SearchByAreaAdapter.SearchByAreaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByAreaViewHolder holder, int position) {
        AreaName areaName =AreaNameResponce.get(position);
        holder.tv_AreaName.setText(areaName.getStrArea());
        holder.tv_AreaName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFToMealByAreaFragment action = SearchFragmentDirections.actionSearchFToMealByAreaFragment(areaName.getStrArea() );
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    public void setData ( List<AreaName> AreaNameResponce )
    {
        this.AreaNameResponce = AreaNameResponce;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return AreaNameResponce.size();
    }

    public class SearchByAreaViewHolder extends RecyclerView.ViewHolder {

        TextView tv_AreaName;

        public SearchByAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            //img = itemView.findViewById(R.id.iv_categoryImg);
            tv_AreaName = itemView.findViewById(R.id.tv_areaName);
        }
    }
}
