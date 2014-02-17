package com.example.Converter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by jeffrey on 2/16/14.
 */
public class UnitChoice extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_choice_layout);
    }
    public void launchWeight(View view){
        Intent intent = new Intent(this, Weight.class);
        startActivity(intent);
    }
    public void launchDistance(View view){
        Intent intent = new Intent(this, Distance.class);
        startActivity(intent);

    }
    public void launchTemperature(View view){
        Intent intent = new Intent(this, Temperature.class);
        startActivity(intent);
    }
}