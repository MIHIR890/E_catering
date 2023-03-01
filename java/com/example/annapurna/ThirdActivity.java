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

public class ThirdActivity extends AppCompatActivity {
    Button confirmbutton;
    ImageView next;
    ImageView cross;
    TextView tv3;
    TextView tv3a;
    TextView tv3b;
    TextView fooddetails;

    Button cartbutton;
    DatabaseReference reference;
    RecyclerView rvMyTaskList;
    ArrayList<MyTaskModel> list;
    TaskAdapter taskAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        confirmbutton = findViewById(R.id.confirmbutton);
        next = findViewById(R.id.next);
        cross = findViewById(R.id.cross);

        fooddetails=findViewById(R.id.fooddetails);
        cartbutton = findViewById(R.id.cartbutton);
        tv3 = findViewById(R.id.tv3);
        tv3a = findViewById(R.id.tv3a);
        tv3b = findViewById(R.id.tv3b);

        //receive name from profile

        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("KEY_SENDER");
        String receivedValue1 = intent.getStringExtra("SEAT_SENDER");
        String receivedValue2 = intent.getStringExtra("COACH_SENDER");
        tv3a.setText(receivedValue1);
        tv3b.setText(receivedValue2);
        tv3.setText(receivedValue);

        //Alertdialog code


        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                builder.setTitle("ORDER CONFIRMATION");
                builder.setCancelable(false);
                builder.setIcon(R.drawable.greentick);
                builder.setMessage("ARE YOU SURE YOU WANT TO PLACE AN ORDER ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent intent = new Intent(ThirdActivity.this, CartActivity.class);
                        intent.putExtra("PASSENGER_NAME",tv3.getText().toString());
                        intent.putExtra("FOOD_NAME",fooddetails.getText().toString());
                        intent.putExtra("COACH_SENDER",tv3b.getText().toString());
                        intent.putExtra("SEAT_SENDER",tv3a.getText().toString());

                        startActivity(intent);

                    }

                });


                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
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

        //back code
    cross.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent1 = new Intent(ThirdActivity.this, SecondActivity.class);
            startActivity(intent1);
        }
    });

    //codefornextpage

    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent2 = new Intent(ThirdActivity.this,FourthActivity.class);
            intent2.putExtra("KEY_SENDER",tv3.getText().toString());
            intent2.putExtra("COACH_SENDER",tv3b.getText().toString());
            intent2.putExtra("SEAT_SENDER",tv3a.getText().toString());


            startActivity(intent2);
        }
    });

    //addtocartbutton
    cartbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ThirdActivity.this, CartActivity.class);
            intent.putExtra("PASSENGER_NAME",tv3.getText().toString());
            intent.putExtra("FOOD_NAME",fooddetails.getText().toString());
            intent.putExtra("COACH_SENDER",tv3b.getText().toString());
            intent.putExtra("SEAT_SENDER",tv3a.getText().toString());


            startActivity(intent);

        }
    });

        //will start workinhg on Database

        rvMyTaskList = findViewById(R.id.rvMyTaskList);
        rvMyTaskList.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTaskModel>();


        // Lets fetch the data from firebase

        reference = FirebaseDatabase.getInstance().getReference().child("annapurna");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // to codes to retrive data and replace the layout of mainactivity

                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    MyTaskModel taskModel = dataSnapshot.getValue(MyTaskModel.class);
                    list.add(taskModel);
                }
                taskAdapter = new TaskAdapter(ThirdActivity.this, list);
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