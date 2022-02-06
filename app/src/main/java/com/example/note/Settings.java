package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    Button fontSizeReset;
    EditText txt_zoom;
    EditText textFullscreen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //reset text size
        textFullscreen = findViewById(R.id.textFullscreen);
        txt_zoom = findViewById(R.id.txt_zoom);
        fontSizeReset = findViewById(R.id.fontSizeRest);
        fontSizeReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_zoom = findViewById(R.id.txt_zoom);
                MainActivity.size = 30;
                textFullscreen = findViewById(R.id.textFullscreen);
                text_Fullscreen.sizeFullscreen = 30;





            }
        });


    }
}