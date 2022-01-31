package com.example.foodandco2.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandco2.R;
import com.example.foodandco2.model.Food;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private List<Food> mFoodList;

    private Context mContext;
    private setOnSelectedListener listener;
    public FoodAdapter(List<Food> mFoodList, Context mContext,setOnSelectedListener listener) {
        this.mFoodList = mFoodList;

        this.mContext = mContext;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new FoodViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        Food food = mFoodList.get(position);
        holder.tvFoodName.setText(food.getName());
        String co2 = "CO2 Kilos Equivalent: <b>"+food.getC02KilosEquivalent() + "</b>";
        String carMiles = "Car Miles Equivalent: <b>"+ food.getCarMilesEquivalent()+"</b>";
        holder.tvC02KilosEquivalent.setText(Html.fromHtml(co2));
        holder.tvCarMilesEquivalent.setText(Html.fromHtml(carMiles));
        holder.listItem.setOnClickListener(v -> listener.onSelected(food, position));

        if(food.isSelected()) {
            holder.compatCheckBox.setVisibility(View.VISIBLE);
            holder.compatCheckBox.setChecked(true);
            holder.listItem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.selected_state));
        }
        else {
            holder.listItem.setBackgroundColor(ContextCompat.getColor(mContext, R.color.normal_state));
            holder.compatCheckBox.setChecked(false);
            holder.compatCheckBox.setVisibility(View.GONE);
        }
    }



    @Override
    public int getItemCount() {
        return mFoodList == null ? 0 : mFoodList.size();
    }

    public interface setOnSelectedListener{
        void onSelected(Food food,int position);
    }

    protected static class FoodViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvFoodName,tvC02KilosEquivalent,tvCarMilesEquivalent;
        MaterialCardView listItem;
        AppCompatCheckBox compatCheckBox;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            listItem = itemView.findViewById(R.id.item_food_card);
            tvC02KilosEquivalent = itemView.findViewById(R.id.tv_co2_kilos_equivalent);
            tvCarMilesEquivalent = itemView.findViewById(R.id.tv_car_miles_equivalent);
            compatCheckBox = itemView.findViewById(R.id.ckb_selected);


        }
    }
}
