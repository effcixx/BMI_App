package com.example.ewaew.bmi_app;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText massInput;
    private EditText heightInput;
    private Button showResult;
    private Switch switch_button;


    private final static String SHARED_PREFERENCES = "shared preferences";
    private final static String MASS_SAVE = "mass";
    private final static String HEIGHT_SAVE =" height";
    private final static String SWITCH = "switch";
    private final static String HINT_M = "hintm";
    private final static String HINT_H ="hinth";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initialize();

        buttonSetOnClick();

        loadDataAndUpdate();
    }

    private void buttonSetOnClick() {
        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSwitch();
            }
        });
        showResult.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onClickShowResult();
            }
        });
    }

    private void initialize() {

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        massInput = findViewById(R.id.mass_input);
        heightInput = findViewById(R.id.height_input);
        switch_button = findViewById(R.id.switch_input);
        showResult = findViewById(R.id.count_button);

    }


    private void checkSwitch()
    {
        if(switch_button.isChecked())
        {
            massInput.setHint(R.string.kilograms);
            heightInput.setHint(R.string.meters);
        }
        else
        {
            massInput.setHint(R.string.pounds);
            heightInput.setHint(R.string.inches);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String mass_hint = massInput.getHint().toString();
        String height_hint = heightInput.getHint().toString();
        outState.putString(MASS_SAVE,mass_hint);
        outState.putString(HEIGHT_SAVE,height_hint);
        outState.putBoolean(SWITCH,switch_button.isChecked());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        String mass_hint = savedInstanceState.getString(MASS_SAVE);
        String height_hint = savedInstanceState.getString(HEIGHT_SAVE);
        boolean on_off = savedInstanceState.getBoolean(SWITCH);
        massInput.setHint(mass_hint);
        heightInput.setHint(height_hint);
        switch_button.setChecked(on_off);
    }
    private void onClickShowResult() {


        if (isEmptyInput())
        {
            Toast.makeText(getApplicationContext(),getString(R.string.no_input),Toast.LENGTH_LONG).show();
        }
        else
        {
            try
            {
                double mass = Double.parseDouble(massInput.getText().toString());
                double height = Double.parseDouble(heightInput.getText().toString());
                calculateBMI(mass,height);
            }
            catch (RuntimeException e)
            {
                Toast.makeText(getApplicationContext(),getString(R.string.wrong_input),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void calculateBMI(double mass, double height) {
        boolean switchState = switch_button.isChecked();
        double result;
        if (switchState)
        {
            BMIKGM bmi_kg_m = new BMIKGM(mass,height);
            try
            {
                result = bmi_kg_m.calculate();
                Main3Activity.start(this,result);
            }
            catch(IllegalArgumentException e)
            {
                checkException(e);
            }
        }
        else
        {
            BMILBSIN bmi_lbs_in = new BMILBSIN(mass,height);

            try
            {
                result = bmi_lbs_in.calculate();
                Main3Activity.start(this,result);
            }
            catch(IllegalArgumentException e)
            {
                checkException(e);
            }
        }

    }



    private void checkException(IllegalArgumentException e)
    {
        if(e.getClass().equals(BMI.HeightAndMassException.class))
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_mass_and_height), Toast.LENGTH_LONG).show();
        else if (e.getClass().equals(BMI.MassException.class))
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_mass), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),getString(R.string.wrong_height), Toast.LENGTH_LONG).show();
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.author:
                final Intent intent = new Intent(this,FullscreenActivity.class);
                startActivity(intent);
                return true;
            case R.id.save_button:
                saveData();
                return true;
            case R.id.erase_button:
                eraseData();
                return true;
        }
        return true;
    }

    private void eraseData() {
        massInput.setText("");
        heightInput.setText("");
        editor.clear();
        editor.apply();
        Toast.makeText(getApplicationContext(),getString(R.string.erased_data),Toast.LENGTH_SHORT).show();
    }

    private void saveData()
    {
        editor.putString(MASS_SAVE,massInput.getText().toString());
        editor.putString(HEIGHT_SAVE,heightInput.getText().toString());
        editor.putBoolean(SWITCH,switch_button.isChecked());
        editor.putString(HINT_M,massInput.getHint().toString());
        editor.putString(HINT_H,heightInput.getHint().toString());
        editor.apply();
        Toast.makeText(getApplicationContext(),getString(R.string.saved_data),Toast.LENGTH_LONG).show();

    }
    private void loadDataAndUpdate()
    {
        massInput.setText(sharedPreferences.getString(MASS_SAVE,""));
        heightInput.setText(sharedPreferences.getString(HEIGHT_SAVE,""));
        boolean on_off = sharedPreferences.getBoolean(SWITCH,false);
        massInput.setHint(sharedPreferences.getString(HINT_M,getString(R.string.pounds)));
        heightInput.setHint(sharedPreferences.getString(HINT_H,getString(R.string.inches)));
        switch_button.setChecked(on_off);
    }

    public boolean isEmptyInput() {
        return massInput.getText().toString().equals("") || heightInput.getText().toString().equals("");
    }
}
