package com.example.annapurna;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SixthActivity extends AppCompatActivity {
    Button confirmbutton;
    ImageView next;
    ImageView cross;
    TextView tv6;
    TextView tv6a;
    TextView tv6b;
    TextView fooddetails;
    Button cartbutton;
    DatabaseReference reference;
    RecyclerView rvMyTaskList;
    ArrayList<MyTaskModel> list;
    TaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        confirmbutton = findViewById(R.id.confirmbutton);
        next = findViewById(R.id.next);
        cross = findViewById(R.id.cross);
        fooddetails = findViewById(R.id.fooddetails);
        cartbutton = findViewById(R.id.cartbutton);

        tv6 = findViewById(R.id.tv6);
        tv6a = findViewById(R.id.tv6a);
        tv6b = findViewById(R.id.tv6b);

        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("KEY_SENDER");
        String receivedValue1 = intent.getStringExtra("SEAT_SENDER");
        String receivedValue2 = intent.getStringExtra("COACH_SENDER");
        tv6a.setText(receivedValue1);
        tv6b.setText(receivedValue2);
        tv6.setText(receivedValue);
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SixthActivity.this);
                builder.setTitle("ORDER CONFIRMATION");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.greentick);
                builder.setMessage("ARE YOU SURE YOU WANT TO PLACE AN ORDER ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent intent = new Intent(SixthActivity.this, CartActivity.class);
                        intent.putExtra("PASSENGER_NAME",tv6.getText().toString());
                        intent.putExtra("FOOD_NAME",fooddetails.getText().toString());
                        intent.putExtra("COACH_SENDER",tv6b.getText().toString());
                        intent.putExtra("SEAT_SENDER",tv6a.getText().toString());

                        startActivity(intent);

                    }

                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SixthActivity.this);
                        builder.setTitle("CANCELLATION");
                        builder.setMessage("ORDER HAS BEEN CANCELLED");
                        builder.setIcon(R.drawable.greentick);
                        builder.show();

                    }


                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SixthActivity.this, SecondActivity.class);
                startActivity(intent1);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SixthActivity.this,SeventhActivity.class);
                intent2.putExtra("KEY_SENDER",tv6.getText().toString());
                intent2.putExtra("COACH_SENDER",tv6b.getText().toString());
                intent2.putExtra("SEAT_SENDER",tv6a.getText().toString());

                startActivity(intent2);
            }
        });
        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SixthActivity.this, CartActivity.class);
                intent.putExtra("PASSENGER_NAME",tv6.getText().toString());
                intent.putExtra("FOOD_NAME",fooddetails.getText().toString());
                intent.putExtra("COACH_SENDER",tv6b.getText().toString());
                intent.putExtra("SEAT_SENDER",tv6a.getText().toString());

                startActivity(intent);

            }
        });

        //will start workinhg on Database

        rvMyTaskList = findViewById(R.id.rvMyTaskList);
        rvMyTaskList.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTaskModel>();


        // Lets fetch the data from firebase

        reference = FirebaseDatabase.getInstance().getReference().child("annapurna3");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // to codes to retrive data and replace the layout of mainactivity

                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    MyTaskModel taskModel = dataSnapshot.getValue(MyTaskModel.class);
                    list.add(taskModel);
                }
                taskAdapter = new TaskAdapter(SixthActivity.this, list);
                rvMyTaskList.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // to show the error
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();

            }
        });



    }

}