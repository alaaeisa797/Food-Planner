package com.example.foodplanner.meal_detailes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.MealsDetailes;
import com.example.foodplanner.R;

import java.util.List;

public class MealDetailsAdapter extends RecyclerView.Adapter<MealDetailsAdapter.MealDetailesViewHolder>
{

    Context context ;
    List<MealsDetailes> mealsDetailesResponce;

    private static final String URL = "https://www.themealdb.com/images/ingredients/";
    private static final String END_POINT = "-Small.png";

    public MealDetailsAdapter(Context context, List<MealsDetailes> mealsDetailesResponce) {
        this.context = context;
        this.mealsDetailesResponce = mealsDetailesResponce;
    }

    @NonNull
    @Override
    public MealDetailesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.igrediant_item,parent,false);
        MealDetailesViewHolder mealDetailesViewHolder=new MealDetailesViewHolder(view);
        return mealDetailesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealDetailesViewHolder holder, int position) {
        MealsDetailes currntMealsDetailes = mealsDetailesResponce.get(position);
       if(!currntMealsDetailes.getStrIngredient1().isEmpty()||!currntMealsDetailes.getStrIngredient1().equals(null))
       {
           Glide.with(context)
                   .load(URL+currntMealsDetailes.getStrIngredient1()+END_POINT)
                   .into(holder.iv_ingrediantImg1);
           holder.tv_ingradientName1.setText(currntMealsDetailes.getStrIngredient1());
       }
       else
       {
           holder.cardView1.setVisibility(ViewGroup.GONE);
       }

        if(!currntMealsDetailes.getStrIngredient2().isEmpty()||!currntMealsDetailes.getStrIngredient2().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient2()+END_POINT)
                    .into(holder.iv_ingrediantImg2);
            holder.tv_ingradientName2.setText(currntMealsDetailes.getStrIngredient1());
        }
        else
        {
            holder.cardView2.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient3().isEmpty()||!currntMealsDetailes.getStrIngredient3().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient3()+END_POINT)
                    .into(holder.iv_ingrediantImg3);
            holder.tv_ingradientName3.setText(currntMealsDetailes.getStrIngredient3());
        }
        else
        {
            holder.cardView3.setVisibility(ViewGroup.GONE);
        }
        if(!currntMealsDetailes.getStrIngredient4().isEmpty()||!currntMealsDetailes.getStrIngredient4().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient4()+END_POINT)
                    .into(holder.iv_ingrediantImg4);
            holder.tv_ingradientName4.setText(currntMealsDetailes.getStrIngredient4());
        }
        else
        {
            holder.cardView4.setVisibility(ViewGroup.GONE);
        }
        if(!currntMealsDetailes.getStrIngredient5().isEmpty()||!currntMealsDetailes.getStrIngredient5().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient5()+END_POINT)
                    .into(holder.iv_ingrediantImg5);
            holder.tv_ingradientName5.setText(currntMealsDetailes.getStrIngredient5());
        }
        else
        {
            holder.cardView5.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient6().isEmpty()||!currntMealsDetailes.getStrIngredient6().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient6()+END_POINT)
                    .into(holder.iv_ingrediantImg6);
            holder.tv_ingradientName6.setText(currntMealsDetailes.getStrIngredient6());
        }
        else
        {
            holder.cardView6.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient7().isEmpty()||!currntMealsDetailes.getStrIngredient7().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient7()+END_POINT)
                    .into(holder.iv_ingrediantImg7);
            holder.tv_ingradientName7.setText(currntMealsDetailes.getStrIngredient7());
        }
        else
        {
            holder.cardView7.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient8().isEmpty()||!currntMealsDetailes.getStrIngredient8().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient8()+END_POINT)
                    .into(holder.iv_ingrediantImg8);
            holder.tv_ingradientName8.setText(currntMealsDetailes.getStrIngredient8());
        }
        else
        {
            holder.cardView8.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient9().isEmpty()||!currntMealsDetailes.getStrIngredient9().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient9()+END_POINT)
                    .into(holder.iv_ingrediantImg9);
            holder.tv_ingradientName9.setText(currntMealsDetailes.getStrIngredient9());
        }
        else
        {
            holder.cardView9.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient10().isEmpty()||!currntMealsDetailes.getStrIngredient10().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient10()+END_POINT)
                    .into(holder.iv_ingrediantImg10);
            holder.tv_ingradientName10.setText(currntMealsDetailes.getStrIngredient10());
        }
        else
        {
            holder.cardView10.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient11().isEmpty()||!currntMealsDetailes.getStrIngredient11().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient11()+END_POINT)
                    .into(holder.iv_ingrediantImg11);
            holder.tv_ingradientName11.setText(currntMealsDetailes.getStrIngredient11());
        }
        else
        {
            holder.cardView11.setVisibility(ViewGroup.GONE);
        }

        if(!currntMealsDetailes.getStrIngredient12().isEmpty()||!currntMealsDetailes.getStrIngredient12().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient12()+END_POINT)
                    .into(holder.iv_ingrediantImg12);
            holder.tv_ingradientName12.setText(currntMealsDetailes.getStrIngredient12());
        }
        else
        {
            holder.cardView12.setVisibility(ViewGroup.GONE);
        }
        if(!currntMealsDetailes.getStrIngredient13().isEmpty()||!currntMealsDetailes.getStrIngredient13().equals(null))
        {
            Glide.with(context)
                    .load(URL+currntMealsDetailes.getStrIngredient13()+END_POINT)
                    .into(holder.iv_ingrediantImg13);
            holder.tv_ingradientName13.setText(currntMealsDetailes.getStrIngredient13());
        }
        else
        {
            holder.cardView13.setVisibility(ViewGroup.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return mealsDetailesResponce.size();
    }

    public class MealDetailesViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_ingradientName1;
        TextView tv_ingradientName2;
        TextView tv_ingradientName3;
        TextView tv_ingradientName4;
        TextView tv_ingradientName5;
        TextView tv_ingradientName6;
        TextView tv_ingradientName7;
        TextView tv_ingradientName8;
        TextView tv_ingradientName9;
        TextView tv_ingradientName10;
        TextView tv_ingradientName11;
        TextView tv_ingradientName12;
        TextView tv_ingradientName13;
        TextView tv_ingradientName14;
        TextView tv_ingradientName15;
        ImageView iv_ingrediantImg1;
        ImageView iv_ingrediantImg2;
        ImageView iv_ingrediantImg3;
        ImageView iv_ingrediantImg4;
        ImageView iv_ingrediantImg5;
        ImageView iv_ingrediantImg6;
        ImageView iv_ingrediantImg7;
        ImageView iv_ingrediantImg8;
        ImageView iv_ingrediantImg9;
        ImageView iv_ingrediantImg10;
        ImageView iv_ingrediantImg11;
        ImageView iv_ingrediantImg12;
        ImageView iv_ingrediantImg13;

        CardView cardView1;
        CardView cardView2;
        CardView cardView3;
        CardView cardView4;
        CardView cardView5;
        CardView cardView6;
        CardView cardView7;
        CardView cardView8;
        CardView cardView9;
        CardView cardView10;
        CardView cardView11;
        CardView cardView12;
        CardView cardView13;
        CardView cardView14;
        CardView cardView15;

        public MealDetailesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingradientName1 = itemView.findViewById(R.id.ingredient_details);
            iv_ingrediantImg1 = itemView.findViewById(R.id.iv_searchIngrediantImg);
            cardView1 = itemView.findViewById(R.id.cd_ingrdeiantSearchCard1);
            /////////////////////////////////////////////////////
            cardView2 = itemView.findViewById(R.id.cardView_2);
            tv_ingradientName2 = itemView.findViewById(R.id.ingredient_details2);
            iv_ingrediantImg2 = itemView.findViewById(R.id.imageView2);


            ////////////////////////////////////////////////////////

            cardView3 = itemView.findViewById(R.id.cardView_3);

            tv_ingradientName3 = itemView.findViewById(R.id.ingredient_details3);
            iv_ingrediantImg3 = itemView.findViewById(R.id.imageView3);
            ///////////////////////////////////////

            cardView4 = itemView.findViewById(R.id.cardView_4);
            tv_ingradientName4 = itemView.findViewById(R.id.ingredient_details4);
            iv_ingrediantImg4 = itemView.findViewById(R.id.imageView4);


            ////////////////////////////////////
            cardView5 = itemView.findViewById(R.id.cardView_5);
            tv_ingradientName5 = itemView.findViewById(R.id.ingredient_details5);
            iv_ingrediantImg5 = itemView.findViewById(R.id.imageView5);

            ////////////////////////////////////
            cardView6 = itemView.findViewById(R.id.cardView_6);
            tv_ingradientName6 = itemView.findViewById(R.id.ingredient_details6);
            iv_ingrediantImg6 = itemView.findViewById(R.id.imageView6);

            ///////////////////////////////////
            cardView7 = itemView.findViewById(R.id.cardView_7);
            tv_ingradientName7 = itemView.findViewById(R.id.ingredient_details7);
            iv_ingrediantImg7 = itemView.findViewById(R.id.imageView7);

            ///////////////////////////////////
            cardView8 = itemView.findViewById(R.id.cardView_8);
            tv_ingradientName8 = itemView.findViewById(R.id.ingredient_details8);
            iv_ingrediantImg8 = itemView.findViewById(R.id.imageView8);
            ///////////////////////////////////
            cardView9 = itemView.findViewById(R.id.cardView_9);
            tv_ingradientName9 = itemView.findViewById(R.id.ingredient_details9);
            iv_ingrediantImg9 = itemView.findViewById(R.id.imageView9);
            ///////////////////////////////////
            cardView10 = itemView.findViewById(R.id.cardView_10);
            tv_ingradientName10 = itemView.findViewById(R.id.ingredient_details10);
            iv_ingrediantImg10 = itemView.findViewById(R.id.imageView10);
            ///////////////////////////////////
            cardView11 = itemView.findViewById(R.id.cardView_11);
            tv_ingradientName11 = itemView.findViewById(R.id.ingredient_details11);
            iv_ingrediantImg11 = itemView.findViewById(R.id.imageView11);
            ///////////////////////////////////
            cardView12 = itemView.findViewById(R.id.cardView_12);
            tv_ingradientName12 = itemView.findViewById(R.id.ingredient_details12);
            iv_ingrediantImg12 = itemView.findViewById(R.id.imageView12);
            ///////////////////////////////////
            cardView13 = itemView.findViewById(R.id.cardView_13);
            tv_ingradientName13 = itemView.findViewById(R.id.ingredient_details13);
            iv_ingrediantImg13 = itemView.findViewById(R.id.imageView13);
            ///////////////////////////////////
//            cardView14 = itemView.findViewById(R.id.cardView_14);
//            tv_ingradientName14 = itemView.findViewById(R.id.ingredient_details14);
//            iv_ingrediantImg14 = itemView.findViewById(R.id.imageView14);
//            ////////////////////////////////////
//            cardView15 = itemView.findViewById(R.id.cardView_15);
//            tv_ingradientName15 = itemView.findViewById(R.id.ingredient_details15);
//            iv_ingrediantImg15 = itemView.findViewById(R.id.imageView15);


        }
    }

}
