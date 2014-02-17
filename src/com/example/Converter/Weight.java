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
public class Weight extends Activity{
    public static final double KILOGRAM = 0.001;
    public static final double MILLIGRAM = 1000;
    public static final double OUNCE=0.035274;
    public static final double POUND=0.00220462;

    ArrayAdapter<String> conversionAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_layout);

        ListView convertedUnits = (ListView) findViewById(R.id.weight_unit_ListView);
        ArrayList<String> baseValues = convert(0);
        conversionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, baseValues);
        convertedUnits.setAdapter(conversionAdapter);

        Spinner selectUnit = (Spinner) findViewById(R.id.weight_select_spinner);
        String[] unitTypes = new String[]{"g","kg","mg","oz","lb"};
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitTypes);
        selectUnit.setAdapter(unitAdapter);
        selectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                processUnits();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        EditText editText = (EditText) findViewById(R.id.weight_enterValue_editText);
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
        Spinner selectUnit = (Spinner) findViewById(R.id.weight_select_spinner);
        String units = selectUnit.getSelectedItem().toString();
        EditText enterValue = (EditText) findViewById(R.id.weight_enterValue_editText);
        String value = enterValue.getText().toString();
        Double baseValue = 0.0;
        if (value.length() > 0)
            baseValue = toGrams(Double.parseDouble(value), units);
        ArrayList<String> newValues = convert(baseValue);
        conversionAdapter.clear();
        conversionAdapter.addAll(newValues);
    }

    private ArrayList<String> convert(double i){
        ArrayList<String> converted = new ArrayList<String>();
        converted.add("Grams: " + i);
        converted.add("Kilograms: " + (i * KILOGRAM));
        converted.add("Milligrams: " + (i * MILLIGRAM));
        converted.add("Ounces: " + (i * OUNCE));
        converted.add("Pounds: " + (i * POUND));
        return converted;
    }

    private double toGrams(double i, String unit){
        if (unit == "g"){
            return i;
        } else if (unit == "kg") {
            return i / KILOGRAM;
        } else if  (unit == "mg") {
            return i / MILLIGRAM;
        } else if (unit == "oz") {
            return i / OUNCE;
        } else {
            return i / POUND;
        }
    }
}