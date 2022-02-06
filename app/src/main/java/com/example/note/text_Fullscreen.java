package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class text_Fullscreen extends AppCompatActivity {

    EditText textFullscreen;
    Button biggerFontFullscreen;
    Button smallerFontFullscreen;
    EditText txt_zoom;




    static float sizeFullscreen = 30.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_fullscreen);

        //setting font size
        textFullscreen=findViewById(R.id.textFullscreen);
        textFullscreen.setTextSize(sizeFullscreen);


        textFullscreen.setText(MainActivity.text);

//text bigger
        textFullscreen=findViewById(R.id.textFullscreen);
        biggerFontFullscreen=findViewById(R.id.biggerFontFullscreen);
        biggerFontFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeFullscreen = (sizeFullscreen+4);
                textFullscreen=findViewById(R.id.textFullscreen);
                textFullscreen.setTextSize(sizeFullscreen);
            }
        });
//text smaller
        textFullscreen=findViewById(R.id.textFullscreen);
        smallerFontFullscreen=findViewById(R.id.smallerFontFulscreen);
        smallerFontFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sizeFullscreen = (sizeFullscreen-4);
                textFullscreen=findViewById(R.id.textFullscreen);
                textFullscreen.setTextSize(sizeFullscreen);
            }
        });

    }
}