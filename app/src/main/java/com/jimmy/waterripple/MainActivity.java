package com.jimmy.waterripple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WaterRipple waterRipple = new WaterRipple(this);
        setContentView(waterRipple);
        waterRipple.startAnimation();
    }
}
