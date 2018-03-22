package com.example.ewaew.bmi_app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void for_valid_data_BMIKGM_is_returned_correct()
    {
        BMIKGM bmiCounter = new BMIKGM(60,1.70);
        double bmi = bmiCounter.calculate();
        assertEquals(20.761,bmi,0.001);
    }
    @Test
    public void for_valid_data_BMIILBIN_is_returned_correct()
    {
        BMILBSIN bmiCounter = new BMILBSIN(172,72);
        double bmi = bmiCounter.calculate();
        assertEquals(23.3,bmi,0.1);
    }
    @Test(expected = BMI.HeightAndMassException.class)
    public void zero_mass_and_height_exception_BMIKGM()
    {
        BMIKGM bmiCounter = new BMIKGM(0,0);
        double bmi = bmiCounter.calculate();
        //assertEquals(20.761,bmi,0.001); gdy to na gorze zz wyjakatkiem to nie musimy tego miec
        //assertEquals("Wrong height and mass input","");
    }
    @Test(expected = BMI.HeightAndMassException.class)
    public void zero_mass_and_height_exception_BMIILBIN()
    {
        BMILBSIN bmiCounter = new BMILBSIN(0,0);
        double bmi = bmiCounter.calculate();
        //assertEquals(20.761,bmi,0.001); gdy to na gorze zz wyjakatkiem to nie musimy tego miec
        //powinnismy rzucic wyjatkiem i go wyrzej obsluzyc
    }
    @Test(expected = BMI.MassException.class)
    public void wrong_weihgt_exception_BMIKGM()
    {
        BMIKGM bmikgm = new BMIKGM(10000,1.79);
        bmikgm.calculate();
    }
    @Test(expected = BMI.MassException.class)
    public void wrong_weihgt_exception_BMIILBIN()
    {
        BMILBSIN bmiilbin = new BMILBSIN(1000,80);
        bmiilbin.calculate();
    }
    @Test(expected = BMI.HeightException.class)
    public void wrong_height_exception_BMIKGM()
    {
        BMIKGM bmikgm = new BMIKGM(80,179);
        bmikgm.calculate();
    }
    @Test(expected = BMI.HeightException.class)
    public void wrong_height_exception_BMIILBIN()
    {
        BMILBSIN bmiilbin = new BMILBSIN(200,200);
        bmiilbin.calculate();
    }
}
