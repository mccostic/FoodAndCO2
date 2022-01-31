package com.example.foodandco2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodandco2.adapter.FoodAdapter;
import com.example.foodandco2.model.Food;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements FoodAdapter.setOnSelectedListener{

    protected RecyclerView recyclerView;
    List<Food> mFoodList;
    FoodAdapter foodAdapter;
    private Food mSelectedFood;

    public AppCompatTextView tvFoodCanReplace,tvC02ReducedBy,tvPercentageReduction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvFoodCanReplace = findViewById(R.id.tv_food_can_be_replaced_with);
        tvPercentageReduction =findViewById(R.id.tv_percentage_reduction);
        tvC02ReducedBy = findViewById(R.id.tv_co2_reduced_by);
        mFoodList = new ArrayList<>();

        getFoodList();
        foodAdapter = new FoodAdapter(mFoodList,getApplicationContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(foodAdapter);


    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSelected(Food food,int position) {
        if (food.isSelected()) {
            mFoodList.get(position).setSelected(false);


        }
        else {

            mFoodList.get(position).setSelected(true);
        }


        foodAdapter.notifyDataSetChanged();

        double c02Total = calculateC02(mFoodList);

        String totalC02 = "Total C02: "+new DecimalFormat("0.00").format(c02Total);
        tvC02ReducedBy.setText(totalC02);
        if(food.isSelected())
        findFoodReplacementForHighC02Food(food);

        if(c02Total ==0.0)
            findViewById(R.id.view_bottom_details).setVisibility(View.GONE);
        else findViewById(R.id.view_bottom_details).setVisibility(View.VISIBLE);

    }

    private void findFoodReplacementForHighC02Food(Food selected){
        List<Integer> integers = new ArrayList<>();
        for (int i =0; i<mFoodList.size();i++){
            if(selected.getId() < mFoodList.get(i).getId() && !mFoodList.get(i).isSelected()){
                integers.add(i);
            }
        }
        Collections.shuffle(integers);
        if(integers.size()>0) {
            Food lowestC02Food = mFoodList.get(integers.get(0));
            Log.d("Food", "This food" + lowestC02Food.toString());
            double reducedPercentage = ((selected.getC02KilosEquivalent() - lowestC02Food.getC02KilosEquivalent()) / (selected.getC02KilosEquivalent() + lowestC02Food.getC02KilosEquivalent())) * 100;
            String co2ReductionPercentage = new DecimalFormat("0.00").format(reducedPercentage) + "%";
            String formatted = "" + selected.getName() + " = " +
                    selected.getC02KilosEquivalent() + " can be replaced with " +
                    lowestC02Food.getName() + " (= " + lowestC02Food.getC02KilosEquivalent() + " ) to reduce the CO2 by " + co2ReductionPercentage;
            tvFoodCanReplace.setText(formatted);
            tvPercentageReduction.setText(co2ReductionPercentage);
        }

    }
    private double calculateC02(List<Food> foodList){
        double totalC02 = 0.0;
        for (Food food: foodList){
            if(food.isSelected())
            totalC02+=food.getC02KilosEquivalent();
        }

        return  totalC02;
    }

    private void getFoodList(){
        mFoodList.add(new Food(1, 1, "Lamb", 39.2, 91));
        mFoodList.add(new Food(2, 2, "Beef", 27.0, 63));
        mFoodList.add(new Food(3, 3, "Cheese", 13.5, 31));
        mFoodList.add(new Food(4, 4, "Pork", 12.1, 28));
        mFoodList.add(new Food(5, 5, "Turkey", 10.9, 25));
        mFoodList.add(new Food(6, 6, "Chicken", 6.9, 16));
        mFoodList.add(new Food(7, 7, "Tuna", 6.1, 14));
        mFoodList.add(new Food(8, 8, "Eggs", 4.8, 11));
        mFoodList.add(new Food(9, 8, "Potatoes", 2.9, 7));
        mFoodList.add(new Food(10, 10, "Rice", 2.7, 6));
        mFoodList.add(new Food(11, 11, "Nuts", 2.3, 5));
        mFoodList.add(new Food(12, 12, "Beans/tofu", 2.0, 4.5));
        mFoodList.add(new Food(13, 13, "Vegetables", 2.0, 4.5));
        mFoodList.add(new Food(14, 14, "Milk", 1.9, 4));
        mFoodList.add(new Food(15, 15, "Fruit", 1.1, 2.5));
        mFoodList.add(new Food(16, 16, "Lentils", 0.9, 2.0));

        Collections.sort(mFoodList, (o1, o2) -> Double.compare(o1.getC02KilosEquivalent() , o2.getC02KilosEquivalent()));
    }
}