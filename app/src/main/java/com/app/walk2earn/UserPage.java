package com.app.walk2earn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserPage extends AppCompatActivity {

public static String b,s2;
public static String a;
public static  SimpleDateFormat sdf1,sdf;
private String userID;


    public static TextView resultTextView;
    public static Button scan_btn,proceed_btn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);


        resultTextView=(TextView)findViewById(R.id.result_text);
        scan_btn=(Button)findViewById(R.id.btn_scan);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_scan) {
                startActivity(new Intent(getApplicationContext(),ScanCodeActivity.class));
                    b=resultTextView.getText().toString();

            }

            }
        });
        proceed_btn=(Button)findViewById(R.id.btn_proceed);
        proceed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b=resultTextView.getText().toString();
                DatabaseReference myRef = database.getReference(b);
                 a = myRef.push().getKey();
                String userMail =MainActivity.mEmailField.getText().toString();

                    myRef.child(a).child("email").setValue(userMail);
                    Calendar cal = Calendar.getInstance();
                    sdf = new SimpleDateFormat("hh:mm:ss");
                sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                myRef.child(a).child("Date").setValue(sdf1.format(cal.getTime()));
                 s2=sdf.format(cal.getTime());
                    myRef.child(a).child("Time").setValue(s2);


                    startActivity(new Intent(getApplicationContext(), stepcount.class));


            }
        });



    }}

