package com.app.walk2earn;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class stepcount extends AppCompatActivity implements SensorEventListener {
    public static int i=0;
    public static float a,result;
    SensorManager sensorManager;
    TextView tv_steps;
    boolean running=false;
    Button btn_chck,bu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepcount);
        tv_steps=(TextView)findViewById(R.id.tv_steps);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        btn_chck=findViewById(R.id.chck_btn);
        bu=findViewById(R.id.bt);
        btn_chck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AmountPage.class));
                i=0;

            }
        });
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(stepcount.this,slide.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume(){
        super.onResume();
        running=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sensorManager.registerListener(this,countSensor,sensorManager.SENSOR_DELAY_UI);

        }
        else{
            Toast.makeText(this,"Sensor not found", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Sensor Not found");
            alertDialogBuilder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });



            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
    @Override
    protected void onPause(){
        super.onPause();
        running=false;
        //i=0;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(i==0) {
            tv_steps.setText(String.valueOf(event.values[0]));
              a=event.values[0];
            i=1;
        }
        tv_steps.setText(String.valueOf(event.values[0]-a));
        result=event.values[0]-a;



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
