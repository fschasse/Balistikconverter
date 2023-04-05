package com.example.balstikconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balstikconverter.databinding.CorrectionLunetteBinding;

import java.text.DecimalFormat;

public class CorrectionLunette extends Fragment {

    private CorrectionLunetteBinding binding;
    private String units = "cm";
    private String distanceUnits = "m";
    private String clicValueUD = "0.25";
    private String clicValueLR = "0.25";
    private String clicUnitUD = "MOA";
    private String clicUnitLR = "MOA";

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CorrectionLunetteBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private double milToMoa(double value)
    {
        return value*3.438;
    }

    private double yardsToM(double yards)
    {
        return yards * 0.9144;
    }

    private double inchtoCm(double inch)
    {
        return inch * 2.54;
    }

    private double cmToInch(double cm)
    {
        return cm / 2.54;
    }

    private double degreeToRadian(double degree)
    {
        return (degree*(Math.PI/180));
    }

    private double moa_m_Calcul(double ecartXFonction, double distanceFonction, String clicUnit)
    {
        double result = 0.0;
        if(clicUnit.equals("MIL"))
        {
            ecartXFonction = milToMoa(ecartXFonction);
        }
        result = (Math.tan(degreeToRadian(ecartXFonction/60))*distanceFonction)*100;
        return result;
    }

    private double valueOfClic(double value, String tourelle)
    {
        double result = 0.0;
        if(tourelle.equals("LR"))
        {
            result = value * Double.parseDouble(clicValueLR);
        }
        else {
            result = value * Double.parseDouble(clicValueUD);
        }
        return result;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CorrectionLunette.this)
                        .navigate(R.id.action_CorrectionLunette_to_MenuHome);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    binding.imageDown.setVisibility(View.INVISIBLE);
                    binding.imageUp.setVisibility(View.INVISIBLE);
                    binding.imageLeft.setVisibility(View.INVISIBLE);
                    binding.imageRight.setVisibility(View.INVISIBLE);
                    double ecartXFonction = Double.parseDouble(binding.FIELDEcartX.getText().toString());
                    double ecartYFonction = Double.parseDouble(binding.FIELDEcartY.getText().toString());
                    ecartXFonction = valueOfClic(ecartXFonction, "LR");
                    ecartYFonction = valueOfClic(ecartYFonction, "UD");
                    double distanceFonction = Double.parseDouble(binding.FIELDDistanceTir.getText().toString());
                    if (units.equals("cm") && distanceUnits.equals("m")) {
                        double numberClicX = moa_m_Calcul(ecartXFonction, distanceFonction, clicUnitLR);
                        double numberClicY = moa_m_Calcul(ecartYFonction, distanceFonction, clicUnitUD);
                        binding.txtClickXValue.setText(df.format(numberClicX) + "cm");
                        binding.txtClickValueY.setText(df.format(numberClicY) + "cm");
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else if (units.equals("inch") && distanceUnits.equals("yards")) {
                        double numberClicX = (moa_m_Calcul(inchtoCm(ecartXFonction), yardsToM(distanceFonction), clicUnitLR));
                        double numberClicY = (moa_m_Calcul(inchtoCm(ecartYFonction), yardsToM(distanceFonction), clicUnitUD));
                        binding.txtClickXValue.setText(df.format(cmToInch(numberClicX)) + "\"");
                        binding.txtClickValueY.setText(df.format(cmToInch(numberClicY)) + "\"");
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else if (units.equals("inch") && distanceUnits.equals("m")) {
                        double numberClicX = (moa_m_Calcul(inchtoCm(ecartXFonction), distanceFonction, clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (moa_m_Calcul(inchtoCm(ecartYFonction), distanceFonction, clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(df.format(cmToInch(numberClicX)) + "\"");
                        binding.txtClickValueY.setText(df.format(cmToInch(numberClicY)) + "\"");
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else {
                        double numberClicX = (moa_m_Calcul(ecartXFonction, yardsToM(distanceFonction), clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (moa_m_Calcul(ecartYFonction, yardsToM(distanceFonction), clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(df.format(numberClicX));
                        binding.txtClickValueY.setText(df.format(numberClicY));
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    }
                }
                catch (Exception e)
                {
                    Toast myToast = Toast.makeText(getActivity(),  getString(R.string.erreurSaisieChamps), Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });

        //Ajout des paramètres
        if (MainActivity.GetPrefValue("SYSTEM_UNIT").equals("IMPERIAL")) {
            units = "inch";
        }
        if (MainActivity.GetPrefValue("SYSTEM_DISTANCE").equals("YARDS")) {
            distanceUnits = "yards";
        }
        clicValueLR = MainActivity.GetPrefValue("SCOPE_VALUE_LR").toString();
        clicValueUD = MainActivity.GetPrefValue("SCOPE_VALUE_UD").toString();
        clicUnitUD = MainActivity.GetPrefValue("SCOPE_UNIT_UD").toString();
        clicUnitLR = MainActivity.GetPrefValue("SCOPE_UNIT_LR").toString();
        binding.txtUnitDistance.setText(distanceUnits);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}