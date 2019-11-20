package com.example.canvasakki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    fingureGesture fg;
    mainThreadCanvas mtc;
    bgThreadCanvas btc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fg=new fingureGesture(this);
//        setContentView(fg);

//        mtc = new mainThreadCanvas(this);
//        setContentView(mtc);

//        btc = new bgThreadCanvas(this);
//        setContentView(btc);
    }
}
