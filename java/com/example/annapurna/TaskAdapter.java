package com.example.annapurna;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.annapurna.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter <TaskAdapter.MyViewHolder>{
    Context context;
    ArrayList<MyTaskModel>myTaskModels;

    public TaskAdapter(Context c, ArrayList<MyTaskModel> tasks){
        context = c;
        myTaskModels = tasks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int positon) {
        //tasktitle  string //tvTasktitle Textview
        myViewHolder.FOOD.setText(myTaskModels.get(positon).FOOD);

        myViewHolder.RATE.setText(myTaskModels.get(positon).RATE);
    }

    @Override
    public int getItemCount() {
        return myTaskModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView FOOD,  RATE;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //textview
            FOOD = itemView.findViewById(R.id.FOOD);

            RATE = itemView.findViewById(R.id.RATE);
        }
    }

}