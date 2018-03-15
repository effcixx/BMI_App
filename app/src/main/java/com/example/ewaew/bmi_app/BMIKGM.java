package com.example.ewaew.bmi_app;

/**
 * Created by ewaew on 09.03.2018.
 */

public class BMIKGM extends BMI {

    private double mass;
    private double height;

    private final static double MIN_HEIGHT = 0.5;
    private final static double MAX_HEIGHT = 3;
    private final static double MIN_MASS = 5;
    private final static double MAX_MASS = 500;


    public BMIKGM(double mass,double height)
    {
        this.mass=mass;
        this.height=height;
    }
    public double calculate() throws IllegalArgumentException {
        check() ;
        double result = mass / (height*height);
        return result;
    }

    @Override
    public void check() throws IllegalArgumentException{
        if((height <=MIN_HEIGHT || height>MAX_HEIGHT)&& (mass<=MIN_MASS || mass>=MAX_MASS))
            throw new HeightAndMassException();
        else if (height <=MIN_HEIGHT || height>MAX_HEIGHT)
            throw new HeightException();
        else if(mass<=MIN_MASS || mass>=MAX_MASS)
            throw new MassException();

    }
}
