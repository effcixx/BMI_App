package com.example.ewaew.bmi_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    public static final String RESULT ="result";
    private TextView result;
    private ConstraintLayout cl;

    private final static double UNDER_WEIGHT = 18.0;
    private final static double OVER_WEIGHT = 25.0;
    private final static double OBESE = 30.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        result = findViewById(R.id.result_output);
        cl = findViewById(R.id.layout);


        Intent intent = this.getIntent();
        double outPut = intent.getDoubleExtra(RESULT,0);
        String bmi_result;
        bmi_result=String.format("%.2f",outPut);
        result.setText(bmi_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(outPut<UNDER_WEIGHT)
        {
            //cl.setBackgroundColor(Color.YELLOW);
            int ot = getResources().getConfiguration().orientation;
            if(ot== Configuration.ORIENTATION_LANDSCAPE)
            {
                cl.setBackgroundResource(R.drawable.zolty_poz);
            }
            else
            {
                cl.setBackgroundResource(R.drawable.zolty_pion);
            }

            Toast.makeText(getApplicationContext(),getString(R.string.under_message),Toast.LENGTH_LONG).show();
        }
        else if(outPut>OVER_WEIGHT && outPut<OBESE)
        {
            //cl.setBackgroundColor(Color.parseColor("#FF4500"));
            int ot = getResources().getConfiguration().orientation;
            if(ot== Configuration.ORIENTATION_LANDSCAPE)
            {
                cl.setBackgroundResource(R.drawable.pomaranczowy_poz);
            }
            else
            {
                cl.setBackgroundResource(R.drawable.pomaranczowy_pion);
            }
            Toast.makeText(getApplicationContext(),getString(R.string.over_message),Toast.LENGTH_LONG).show();
        }
        else if (outPut>OBESE)
        {
           // cl.setBackgroundColor(Color.RED);
            int ot = getResources().getConfiguration().orientation;
            if(ot== Configuration.ORIENTATION_LANDSCAPE)
            {
                cl.setBackgroundResource(R.drawable.czerwony_poz);
            }
            else
            {
                cl.setBackgroundResource(R.drawable.czerwony_pion);
            }
            Toast.makeText(getApplicationContext(),getString(R.string.obese_message),Toast.LENGTH_LONG).show();
        }
        else
        {
            //cl.setBackgroundColor(Color.GREEN);
            int ot = getResources().getConfiguration().orientation;
            if(ot== Configuration.ORIENTATION_LANDSCAPE)
            {
                cl.setBackgroundResource(R.drawable.zielony_poz);
            }
            else
            {
                cl.setBackgroundResource(R.drawable.zielony_pion);

            }
            Toast.makeText(getApplicationContext(),getString(R.string.good_message),Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);

    }
}
