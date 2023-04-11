package com.chasseprecision.balstikconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.chasseprecision.balstikconverter.R;
import com.chasseprecision.balstikconverter.databinding.CorrectionCibleBinding;

public class CorrectionCible extends Fragment {

    private CorrectionCibleBinding binding;
    private String units = "cm";
    private String distanceUnits = "m";
    private String clicValueUD = "0.25";
    private String clicValueLR = "0.25";
    private String clicUnitUD = "MOA";
    private String clicUnitLR = "MOA";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CorrectionCibleBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private double convertMOAToMIL(double MOA)
    {
        double result = (MOA / 3.438);
        return result;
    }

    private double radianToDegree(double angle)
    {
        angle = (angle*(180/Math.PI));
        return angle;
    }

    private double cm_m_Calcul(double ecart, double distance, String clicUnit) {
        //On converti la distance en cm
        distance = distance*100;
        double angle = Math.atan(ecart/(distance));
        double result = radianToDegree(angle)*60;
        if(clicUnit.equals("MIL")) {
            result = convertMOAToMIL(result);
        }
        result = (result*-1.0);
        return result;
    }

    private double yardsToM(double yards)
    {
        return yards * 0.9144;
    }

    private double inchtoCm(double inch)
    {
        return inch * 2.54;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CorrectionCible.this)
                        .navigate(R.id.action_CorrectionCible_to_MenuHome);
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
                    double distanceFonction = Double.parseDouble(binding.FIELDDistanceTir.getText().toString());
                    if (units.equals("cm") && distanceUnits.equals("m")) {
                        double numberClicX = (cm_m_Calcul(ecartXFonction, distanceFonction, clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (cm_m_Calcul(ecartYFonction, distanceFonction, clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(Double.toString(Math.round(numberClicX)));
                        binding.txtClickValueY.setText(Double.toString(Math.round(numberClicY)));
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else if (units.equals("inch") && distanceUnits.equals("yards")) {
                        double numberClicX = (cm_m_Calcul(inchtoCm(ecartXFonction), yardsToM(distanceFonction), clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (cm_m_Calcul(inchtoCm(ecartYFonction), yardsToM(distanceFonction), clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(Double.toString(Math.round(numberClicX)));
                        binding.txtClickValueY.setText(Double.toString(Math.round(numberClicY)));
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else if (units.equals("inch") && distanceUnits.equals("m")) {
                        double numberClicX = (cm_m_Calcul(inchtoCm(ecartXFonction), distanceFonction, clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (cm_m_Calcul(inchtoCm(ecartYFonction), distanceFonction, clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(Double.toString(Math.round(numberClicX)));
                        binding.txtClickValueY.setText(Double.toString(Math.round(numberClicY)));
                        if(Math.round(numberClicX) > 0.0)
                        {
                            binding.imageRight.setVisibility(View.VISIBLE);
                        } else {binding.imageLeft.setVisibility(View.VISIBLE);}
                        if(Math.round(numberClicY) > 0.0)
                        {
                            binding.imageUp.setVisibility(View.VISIBLE);
                        } else {binding.imageDown.setVisibility(View.VISIBLE);}
                    } else {
                        double numberClicX = (cm_m_Calcul(ecartXFonction, yardsToM(distanceFonction), clicUnitLR) / Double.parseDouble(clicValueLR));
                        double numberClicY = (cm_m_Calcul(ecartYFonction, yardsToM(distanceFonction), clicUnitUD) / Double.parseDouble(clicValueUD));
                        binding.txtClickXValue.setText(Double.toString(Math.round(numberClicX)));
                        binding.txtClickValueY.setText(Double.toString(Math.round(numberClicY)));
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
        binding.txtUnitX.setText(units);
        binding.txtUnitY.setText(units);
        binding.txtUnitDistance.setText(distanceUnits);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}