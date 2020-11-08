package com.app.walk2earn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AmountPage extends AppCompatActivity {
TextView amount,date,startingTime,endingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_page);
         float result=stepcount.result;

        amount=findViewById(R.id.amount_txt);
        date=findViewById(R.id.date);
        startingTime=findViewById(R.id.st);
        endingTime=findViewById(R.id.et);

        if(result<20.0){
        amount.setText("AUD 0");
        }
        else if(result>20.0 && result<40.0){
            amount.setText("AUD 5");
        }
        else if(result>40.0 && result<100.0){
            amount.setText("AUD 15");
        }
        else if(result>100.0){
            amount.setText("AUD 25");
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String b=UserPage.b;
        DatabaseReference myRef = database.getReference(b);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
        String s=sdf2.format(cal.getTime());
        myRef.child(UserPage.a).child("End_Time").setValue(s);
        myRef.child(UserPage.a).child("Amount").setValue(amount.getText());
        endingTime.setText(s);
        date.setText(UserPage.sdf1.format(cal.getTime()));
        startingTime.setText(UserPage.s2);



    }
    @Override
    public void onBackPressed(){


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Are you sure,you want to logout");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), AmountPage.class));
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }


