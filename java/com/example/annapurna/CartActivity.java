package com.example.annapurna;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CartActivity extends AppCompatActivity {
    TextView usernameTV;
    TextView DescriptionTV;
    TextView CoachnumberTV;
    TextView SeatnumberTV;
    Button orderbtn;
    DatabaseReference reference;
    Integer taskNum= new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        orderbtn = findViewById(R.id.orderbtn);
        usernameTV = findViewById(R.id.usernameTV);
        CoachnumberTV = findViewById(R.id.CoachnumberTV);
        SeatnumberTV = findViewById(R.id.SeatnumberTV);

        //receivetext from foodandpassenger

        Intent intentp3 = getIntent();
        String receivedValuep3 = intentp3.getStringExtra("PASSENGER_NAME");
        usernameTV.setText(receivedValuep3);
        DescriptionTV = (TextView) findViewById(R.id.descriptionTV);
        Intent intentf3 = getIntent();
        String receivedValuef3 = intentf3.getStringExtra("FOOD_NAME");
        DescriptionTV.setText(receivedValuef3);
        Intent intentc3 = getIntent();
        String receivedValuec3 = intentc3.getStringExtra("COACH_SENDER");
        CoachnumberTV.setText(receivedValuec3);
        Intent intents3 = getIntent();
        String receivedValues3 = intents3.getStringExtra("SEAT_SENDER");
        SeatnumberTV.setText(receivedValues3);

    }

    //notification text

    public void headsUpNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(CartActivity.this,Utils.CHANNEL_ID)


                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(Utils.NOTI_TITLE)
                .setContentText(Utils.NOTI_DESC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setVibrate(new long[]{ 200,200,200,200 });

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(CartActivity.this);
        notificationManagerCompat.notify(Utils.NOTI_ID,builder.build());




        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this, "Click Again To Confirm ", Toast.LENGTH_SHORT).show();
                //insert task into database

                reference = FirebaseDatabase.getInstance().getReference().child("OrderData")
                        .child("task" + taskNum);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("FOOD").setValue(DescriptionTV.getText().toString());
                        snapshot.getRef().child("PASSENGER").setValue(usernameTV.getText().toString());
                        snapshot.getRef().child("COACHNUMBER").setValue(CoachnumberTV.getText().toString());
                        snapshot.getRef().child("SEATNUMBER").setValue(SeatnumberTV.getText().toString());









                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

}