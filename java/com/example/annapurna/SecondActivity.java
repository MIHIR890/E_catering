package com.example.annapurna;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
     Button confirmbutton;
     Button cancelbutton;
     EditText etpid;
     EditText etsid;
     EditText etcid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        cancelbutton = findViewById(R.id.cancelbutton);
        confirmbutton = findViewById(R.id.confirmbutton);
        etsid = findViewById(R.id.etsid);
        etcid = findViewById(R.id.etcid);
        etpid = findViewById(R.id.etpid);
        confirmbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                if (etsid.length()==0 || etcid.length()==0 || etpid.length()==0) {
                   Toast.makeText(SecondActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                    builder.setTitle("CONFIRMATION");
                    builder.setCancelable(false);
                    builder.setIcon(R.drawable.greentick);
                    builder.setMessage("Are you sure you want to confirm ?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Intent iintent = new Intent(SecondActivity.this, ThirdActivity.class);
                            iintent.putExtra("KEY_SENDER", etpid.getText().toString());
                            iintent.putExtra("SEAT_SENDER", etsid.getText().toString());
                            iintent.putExtra("COACH_SENDER", etcid.getText().toString());


                            startActivity(iintent);
                            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }








            }

        });


    cancelbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            //System.exit(0);
            //Intent intent = new Intent(SecondActivity.this);
            //startActivity(intent);
        }
    });

    }
}