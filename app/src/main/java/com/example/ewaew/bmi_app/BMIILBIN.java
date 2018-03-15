package com.example.ewaew.bmi_app;

import android.content.Context;

/**
 * Created by ewaew on 09.03.2018.
 */

public class BMIILBIN extends BMI
{
    private double mass;
    private double height;


    private final static double MIN_HEIGHT = 19.7;
    private final static double MAX_HEIGHT = 118.11;
    private final static double MIN_MASS = 11.02;
    private final static double MAX_MASS = 440.93;



    public BMIILBIN(double mass,double height)
    {
        this.mass=mass;
        this.height=height;
    }
    public double calculate()
    {
        check();
        double result = (mass / (height*height))*703;
        return result;
    }

    @Override
    public void check() throws HeightAndMassException, HeightException, MassException {
        if((height <=MIN_HEIGHT || height>MAX_HEIGHT)&& (mass<=MIN_MASS || mass>=MAX_MASS))
            throw new HeightAndMassException();
        else if (height <=MIN_HEIGHT || height>MAX_HEIGHT)
            throw new HeightException();
        else if(mass<=MIN_MASS || mass>=MAX_MASS)
            throw new MassException();

    }
}

