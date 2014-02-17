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
public class Temperature extends Activity{

    ArrayAdapter<String> conversionAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature_layout);

        ListView convertedUnits = (ListView) findViewById(R.id.temperature_unit_ListView);
        ArrayList<String> baseValues = convert(0, "C");
        conversionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, baseValues);
        convertedUnits.setAdapter(conversionAdapter);

        Spinner selectUnit = (Spinner) findViewById(R.id.temperature_select_spinner);
        ArrayAdapter<CharSequence> unitAdapter = ArrayAdapter.createFromResource(
                this, R.array.temperature_units,android.R.layout.simple_spinner_dropdown_item);
        selectUnit.setAdapter(unitAdapter);
        selectUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                processUnits();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        EditText editText = (EditText) findViewById(R.id.temperature_enterValue_editText);
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
        Spinner selectUnit = (Spinner) findViewById(R.id.temperature_select_spinner);
        String units = selectUnit.getSelectedItem().toString();
        EditText enterValue = (EditText) findViewById(R.id.temperature_enterValue_editText);
        String value = enterValue.getText().toString();
        Double baseValue = 0.0;
        if (value.length() > 0 && !value.equals("-"))
            baseValue = Double.parseDouble(value);
        ArrayList<String> newValues = convert(baseValue, units);
        conversionAdapter.clear();
        conversionAdapter.addAll(newValues);
    }

    private ArrayList<String> convert(double i, String units){
        ArrayList<String> converted = new ArrayList<String>();
        if (units.equals("C")){
            converted.add("C: " + i);
            converted.add("F: " + (9.0/5.0 * i + 32));
        } else {
            converted.add("C: " + ((i-32) * 5.0/9.0));
            converted.add("F: " + i);
        }
        return converted;
    }
}