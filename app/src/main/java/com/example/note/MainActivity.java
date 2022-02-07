package com.example.note;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    EditText txt_zoom;
    Button btn_clear;
    Button settingsButton;
    Button smallerFont;
    //Button biggerFont;
    Button textPageButton;
    Button notify_Btn;


    //supposed to be scroll zoom
    TextView zoomTextview;
    int mBaseDistance;
    float mRation = 1.0f;
    float mBaseRatio;
    final  static  float step = 200;
    static float size = 30.0f;
    static String text;

    RadioGroup radioGroup;
    RadioButton radio_Left;
    RadioButton radio_Centre;
    RadioButton radio_Right;

    private NotificationManagerCompat notificationManager;

    @Nullable
    @Override
    public String getAttributionTag() {
        return super.getAttributionTag();
    }

    //on Create
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zoomTextview = (TextView)findViewById(R.id.txt_zoom);

        //setting font size
        txt_zoom=findViewById(R.id.txt_zoom);
        txt_zoom.setTextSize(size);

        //clear text
        txt_zoom=findViewById(R.id.txt_zoom);
        btn_clear=findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_zoom.getText().clear();
            }
        });

        //font smaller
        txt_zoom=findViewById(R.id.txt_zoom);
        smallerFont=findViewById(R.id.smallerFont);
        smallerFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = (size-4f);
                txt_zoom=findViewById(R.id.txt_zoom);
                txt_zoom.setTextSize(size);
            }
        });
        //font bigger
        txt_zoom=findViewById(R.id.txt_zoom);
        smallerFont=findViewById(R.id.biggerFont);
        smallerFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = (size+4f);
                txt_zoom=findViewById(R.id.txt_zoom);
                txt_zoom.setTextSize(size);
            }
        });



//Notification
        notificationManager = NotificationManagerCompat.from(this);


        //settings button
        settingsButton=findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        //Fullscreen text button
        textPageButton=findViewById(R.id.textPageButton);
        textPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullscreen_text();
            }
        });

        //alignment
        radioGroup=findViewById(R.id.radioGroup);
        radioGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radio_Centre.isChecked())
                {
                    txt_zoom=findViewById(R.id.txt_zoom);
                    txt_zoom.setTextAlignment (View.TEXT_ALIGNMENT_CENTER);

            }
                else if (radio_Left.isChecked())
            {
                txt_zoom=findViewById(R.id.txt_zoom);
                txt_zoom.setTextAlignment (View.TEXT_ALIGNMENT_GRAVITY);
                }
                else if (radio_Right.isChecked())
            {
                return;

                }
            }
        });

    }


    //send notification
    public void sendOnChannel1(View v){

        Intent activityIntent = new Intent( this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", "test");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, AppStart.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_1)
                .setContentTitle("Note")
                .setContentText("Makes the app come back")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .addAction(R.mipmap.ic_launcher, "toast", actionIntent)
                .build();
        notificationManager.notify(1,notification);
    }

    public void openSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openFullscreen_text(){
        text = txt_zoom.getText().toString();
        Intent intent = new Intent(this, text_Fullscreen.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    //supposed to be scroll zoom
    public boolean onTouchEvent( MotionEvent event) {

        if(event.getPointerCount() ==2){
            int action = event.getAction();
            int pureAction = action & MotionEvent.ACTION_MASK;
            if(pureAction == MotionEvent.ACTION_POINTER_DOWN){
                mBaseDistance = getDistance(event);
                mBaseRatio = mRation;
            }
            else {
                float delta = (getDistance(event)-mBaseDistance)/step;
                float multi = (float)(Math.pow(2,delta));
                mRation = Math.min( 1024.0f,Math.max( 0.1f,multi * mBaseRatio));
                zoomTextview.setTextSize(mRation=13);
            }
        }
        return true;
    }
    //on touch method for scroll zoom
    int getDistance(MotionEvent motionEvent){
        int dx = (int)(motionEvent.getX(0)-motionEvent.getX(1));
        int dy = (int)(motionEvent.getY(0)-motionEvent.getY(1));
        return  (int)(Math.sqrt(dx * dx + dy * dy));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}