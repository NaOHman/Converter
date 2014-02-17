package com.example.Converter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by jeffrey on 2/16/14.
 */
public class Distance extends Activity{
    public static final double KILOMETER = 0.001;
    public static final double CENTIMETER = 100;
    public static final double MILLIMETER = 1000;
    public static final double FOOT = 39.3701;
    public static final double INCH = 3.28084;
    public static final double YARD = 1.09361;
    public static final double MILE = 0.000621371;
    ArrayAdapter<String> conversionAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distance_layout);

        ListView convertedUnits = (ListView) findViewById(R.id.distance_unit_ListView);
        ArrayList<String> baseValues = convert(0);
        conversionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, baseValues);
        convertedUnits.setAdapter(conversionAdapter);

        Spinner selectUnit = (Spinner) findViewById(R.id.distance_select_spinner);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
                this, R.array.distance_units,android.R.layout.simple_spinner_dropdown_item);
        selectUnit.setAdapter(unitAdapter);
        selectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                processUnits();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        EditText editText = (EditText) findViewById(R.id.distance_enterValue_editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                processUnits();
            }
        });
    }
    public void processUnits(){
        Spinner selectUnit = (Spinner) findViewById(R.id.distance_select_spinner);
        String units = selectUnit.getSelectedItem().toString();
        EditText enterValue = (EditText) findViewById(R.id.distance_enterValue_editText);
        String value = enterValue.getText().toString();
        Double baseValue = 0.0;
        if (value.length() > 0)
            baseValue = toMeters(Double.parseDouble(value), units);
        ArrayList<String> newValues = convert(baseValue);
        conversionAdapter.clear();
        conversionAdapter.addAll(newValues);
    }

    private ArrayList<String> convert(double i){
        ArrayList<String> converted = new ArrayList<String>();
        converted.add("Meters: " + i);
        converted.add("Millimeters: " + (i * MILLIMETER));
        converted.add("Centimeters: " + (i * CENTIMETER));
        converted.add("Kilometers: " + (i * KILOMETER));
        converted.add("Inches: " + (i * INCH));
        converted.add("Feet: " + (i * FOOT));
        converted.add("Yard: " + (i * YARD));
        converted.add("Mile: " + (i * MILE));
        return converted;
    }

    private double toMeters(double i, String unit){
        if (unit.equals("m")){
            return i;
        } else if (unit.equals("km")) {
            return i / KILOMETER;
        } else if  (unit.equals("mm")) {
            return i / MILLIMETER;
        } else if (unit.equals("in")) {
            return i / INCH;
        } else if (unit.equals("ft")){
            return i / FOOT;
        } else if (unit.equals("yd")){
            return i / YARD;
        } else {
            return i / MILE;
        }
    }
}