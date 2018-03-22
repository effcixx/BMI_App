package com.example.ewaew.bmi_app;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.Toast;

/**
 * Created by Ewa Lyko on 09.03.2018.
 */

public class BMILBSIN extends BMI
{
    private double mass;
    private double height;


    private final static double MIN_HEIGHT = 19.7;
    private final static double MAX_HEIGHT = 118.11;
    private final static double MIN_MASS = 11.02;
    private final static double MAX_MASS = 440.93;



    public BMILBSIN(double mass,double height)
    {
        this.mass=mass;
        this.height=height;
    }
    public double calculate()
    {
        check();
        return (mass / (height*height))*703;
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

