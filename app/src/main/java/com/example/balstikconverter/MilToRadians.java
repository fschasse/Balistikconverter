package com.example.balstikconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.balstikconverter.databinding.MilToRadiansBinding;

public class MilToRadians extends Fragment {

    private MilToRadiansBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = MilToRadiansBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MilToRadians.this)
                        .navigate(R.id.action_MilToRadians_to_MenuHome);
            }
        });

        //Clear quand on focus le edittext
        binding.ETNMOA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.ETNMOA.setText("");
                }
            }
        });

        //Bouton convertir
        binding.BTCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String valeurMIL = binding.ETNMOA.getText().toString();
                    if (valeurMIL.isEmpty()) {
                        Toast myToast = Toast.makeText(getActivity(), "La valeur MIL n' est pas saisie !", Toast.LENGTH_LONG);
                        myToast.show();
                    } else {
                        float floatValeurMIL = Float.parseFloat(valeurMIL);
                        CalculBalistique calculBalistik = new CalculBalistique();
                        calculBalistik.SetMilValue(floatValeurMIL);
                        if (calculBalistik.Calculate("MIL_TO_RADIANS")) {
                            binding.editTextNumber2.setText(calculBalistik.GetResult());
                            binding.ETNMOA.clearFocus();
                        } else {
                            Toast myToast = Toast.makeText(getActivity(), calculBalistik.GetError(), Toast.LENGTH_LONG);
                            myToast.show();
                        }
                    }
                }
                catch(Exception e)
                {
                    Toast myToast = Toast.makeText(getActivity(),  getString(R.string.erreurSaisieChamps), Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}