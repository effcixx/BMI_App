package com.example.ewaew.bmi_app;

/**
 * Created by Ewa Lyko on 22.03.2018.
 */

class BackgroundAndText {

    private int idText;
    private int idBackground;
    BackgroundAndText(int idText, int idBackground)
    {
        this.idText=idText;
        this.idBackground=idBackground;
    }
    int getIdBackground()
    {
        return idBackground;
    }
    int getIdText() { return idText; }
}
