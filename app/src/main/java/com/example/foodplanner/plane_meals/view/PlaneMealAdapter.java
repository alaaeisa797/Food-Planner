package com.example.foodplanner.plane_meals.view;

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
import com.example.foodplanner.Model.PlanMealDetail;
import com.example.foodplanner.R;
import com.example.foodplanner.hoomScreen.view.HomeFragmentDirections;

import java.util.List;

public class PlaneMealAdapter extends RecyclerView.Adapter<PlaneMealAdapter.PlanMealViewHolder>  {
    Context context;
    List<PlanMealDetail>planMealDetailList;
    SetOnDeleteFromPlanListner setOnDeleteFromPlanListner ;

    public PlaneMealAdapter(Context context, List<PlanMealDetail> planMealDetailList ,SetOnDeleteFromPlanListner  setOnDeleteFromPlanListner ) {
        this.context = context;
        this.planMealDetailList = planMealDetailList;
        this.setOnDeleteFromPlanListner = setOnDeleteFromPlanListner ;
    }

    @NonNull
    @Override
    public PlanMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.plane_meal_item,parent,false);
        PlanMealViewHolder planMealViewHolder = new PlanMealViewHolder(view);
        return planMealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealViewHolder holder, int position) {
        PlanMealDetail currntPlanMealDetail = planMealDetailList.get(position);
        holder.planeMealName.setText(currntPlanMealDetail.getStrMeal());
        Glide.with(context).load(currntPlanMealDetail.getStrMealThumb()).into(holder.PlaneMealImg);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnDeleteFromPlanListner.onDeleteClick(currntPlanMealDetail);
            }
        });

        holder.PlaneMealImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CalendarFragmentDirections.ActionCalendarFragmentToMealDetailsFragment action =
                        CalendarFragmentDirections.actionCalendarFragmentToMealDetailsFragment(currntPlanMealDetail.getStrMeal());
                Navigation.findNavController(v).navigate(action);
            }
        });


    }

    @Override
    public int getItemCount() {
        return planMealDetailList.size();
    }

    public class PlanMealViewHolder extends RecyclerView.ViewHolder
    {
        ImageView delete;
        TextView planeMealName;
        ImageView PlaneMealImg;

        public PlanMealViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.iv_deleteFromMealPlane);
            PlaneMealImg = itemView.findViewById(R.id.iv_planeMealImg);
            planeMealName = itemView.findViewById(R.id.tv_planeMealName);

        }
    }
}
