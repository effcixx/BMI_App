package com.example.ewaew.bmi_app;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ewa Lyko on 09.03.2018.
 */



public abstract class BMI extends AppCompatActivity {


    private final static double UNDER_WEIGHT = 18.0;
    private final static double OVER_WEIGHT = 25.0;
    private final static double OBESE = 30.0;


    static class HeightAndMassException extends IllegalArgumentException {
        HeightAndMassException()
        {
            super();
        }

    }
    static class HeightException extends IllegalArgumentException {
        HeightException()
        {
            super();
        }
    }
    static class MassException extends IllegalArgumentException {
        MassException()
        {
            super();
        }
    }


    abstract public double calculate() throws IllegalArgumentException;
    abstract public void check() throws IllegalArgumentException;

    public static BackgroundAndText setBackround(double result, int orientation)
    {

        int idBackground;
        if(result<UNDER_WEIGHT)
        {
            if(orientation == Configuration.ORIENTATION_LANDSCAPE)
                idBackground = R.drawable.zolty_poz;
            else
                idBackground = R.drawable.zolty_pion;
            return new BackgroundAndText(R.string.under_message,idBackground);
        }
        else if(result>OVER_WEIGHT && result<OBESE)
        {
            if(orientation == Configuration.ORIENTATION_LANDSCAPE)
                idBackground = R.drawable.pomaranczowy_poz;
            else
                idBackground= R.drawable.pomaranczowy_pion;
            return new BackgroundAndText(R.string.over_message,idBackground);
        }
        else if (result>OBESE)
        {
            if(orientation == Configuration.ORIENTATION_LANDSCAPE)
                idBackground = R.drawable.czerwony_poz;
            else
                idBackground= R.drawable.czerwony_pion;
            return new BackgroundAndText(R.string.obese_message,idBackground);
        }
        else
        {
            if(orientation == Configuration.ORIENTATION_LANDSCAPE)
               idBackground =R.drawable.zielony_poz;
            else
                idBackground = R.drawable.zielony_pion;
            return new BackgroundAndText(R.string.good_message,idBackground);
        }

    }


}