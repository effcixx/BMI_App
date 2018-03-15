package com.example.ewaew.bmi_app;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ewaew on 09.03.2018.
 */



public abstract class BMI extends AppCompatActivity {


    protected static class HeightAndMassException extends IllegalArgumentException {
        public HeightAndMassException()
        {
            super();
        }

    }
    protected static class HeightException extends IllegalArgumentException {
        public HeightException()
        {
            super();
        }
    }
    protected static class MassException extends IllegalArgumentException {
        public MassException()
        {
            super();
        }
    }


    abstract public double calculate() throws IllegalArgumentException;
    abstract public void check() throws IllegalArgumentException;


}