package com.example.ewaew.bmi_app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import static java.lang.String.format;

public class Main3Activity extends AppCompatActivity {



    public static final String RESULT ="result";
    private TextView result;
    private ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initialize();
        setResult();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setResult() {
        Intent intent = this.getIntent();
        double resultD = intent.getDoubleExtra(RESULT, 0);
        String resultS;
        resultS = format("%.2f", resultD);
        result.setText(resultS);
        setBackgroundAndToast(resultD);
    }

    private void initialize() {
        result = findViewById(R.id.result_output);
        constraintLayout = findViewById(R.id.layout);
    }

    private void setBackgroundAndToast(double result) {
        int orientation = getResources().getConfiguration().orientation;
        BackgroundAndText backgroundAndText = BMI.setBackround(result, orientation);
        int idBackground = backgroundAndText.getIdBackground();
        int text = backgroundAndText.getIdText();
        constraintLayout.setBackgroundResource(idBackground);
        Toast.makeText(getApplicationContext(),getString(text),Toast.LENGTH_SHORT).show();
    }

    public static void start(Context context,double result) {
        Intent starter = new Intent(context, Main3Activity.class);
        starter.putExtra(RESULT,result);
        context.startActivity(starter);
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
