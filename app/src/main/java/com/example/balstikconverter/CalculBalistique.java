package com.example.balstikconverter;

import java.text.DecimalFormat;

public class CalculBalistique {
    private float moaValue;
    private static final DecimalFormat df = new DecimalFormat("0.000");
    private float milValue;
    private double pi = Math.PI;
    private float result;
    private float distanceValue;
    private float correctionCibleValue;
    private String errorMessage;

    public void SetMoaValue(float MOA)
    {
        moaValue = MOA;
    }

    public float GetMoaValue()
    {
        return moaValue;
    }

    public void SetMilValue(float MIL)
    {
        milValue = MIL;
    }

    public void SetDecimalFormat(String format)
    {
        df.applyPattern(format);
    }

    public float GetMilValue()
    {
        return milValue;
    }

    public String GetError()
    {
        if(errorMessage.isEmpty()) {
            errorMessage = "Aucune erreur identifié !";
        }
        return errorMessage;
    }

    public String GetResult()
    {
        String resultString = String.valueOf(df.format(result));
        return resultString;
    }

    private boolean CalculMoaToMil()
    {
        boolean resultFonction = false;
        if(moaValue > 0.0) {
            result = (float) (moaValue / 3.438);
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MOA est égale à 0 !";
        }
        return resultFonction;
    }

    private boolean CalculMoaToDegree()
    {
        boolean resultFonction = false;
        if(moaValue > 0.0) {
            result = (float) (moaValue / 60);
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MOA est égale à 0 !";
        }
        return resultFonction;
    }

    private boolean CalculMoaToRadians()
    {
        boolean resultFonction = false;
        if(moaValue > 0.0) {
            result = (float) (moaValue / 3438);
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MOA est égale à 0 !";
        }
        return resultFonction;
    }

    private boolean CalculMilToDegree()
    {
        boolean resultFonction = false;
        if(milValue > 0.0) {
            result = (float) ((180 / pi) * (milValue/1000));
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MIL est égale à 0 !";
        }
        return resultFonction;
    }

    private boolean CalculMilToRadians()
    {
        boolean resultFonction = false;
        if(milValue > 0.0) {
            result = (float) (milValue / 1000);
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MIL est égale à 0 !";
        }
        return resultFonction;
    }

    private boolean CalculMilToMoa()
    {
        boolean resultFonction = false;
        if(milValue > 0.0) {
            result = (float) (milValue * 3.438);
            resultFonction = true;
        }
        else {
            errorMessage = "La valeur de MIL est égale à 0 !";
        }
        return resultFonction;
    }

    public boolean Calculate(String fonctionCalcul)
    {
        boolean resultFonction = false;
        switch (fonctionCalcul) {
            case "MOA_TO_MIL":
                resultFonction = CalculMoaToMil();
                break;
            case "MIL_TO_MOA":
                resultFonction = CalculMilToMoa();
                break;
            case "MOA_TO_DEGREE":
                resultFonction = CalculMoaToDegree();
                break;
            case "MOA_TO_RADIANS":
                resultFonction = CalculMoaToRadians();
                break;
            case "MIL_TO_DEGREE":
                resultFonction = CalculMilToDegree();
                break;
            case "MIL_TO_RADIANS":
                resultFonction = CalculMilToRadians();
                break;
            case "CORRECTION_CIBLE":
                break;
            case "CORRECTION_LUNETTE":
                break;
        }
        return resultFonction;
    }
}
