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

public class GeneralSettings extends Fragment {

    private com.chasseprecision.balstikconverter.databinding.GeneralSettingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = com.chasseprecision.balstikconverter.databinding.GeneralSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(GeneralSettings.this)
                        .navigate(R.id.action_GeneralSettings_to_MenuHome);
            }
        });
        binding.switchUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.switchUnit.isChecked()) {
                    binding.switchUnit.setText(getResources().getString(R.string.imperial));
                } else {
                    binding.switchUnit.setText(getResources().getString(R.string.metric));
                }
            }
        });
        binding.switchUnitDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.switchUnitDistance.isChecked()) {
                    binding.switchUnitDistance.setText(getResources().getString(R.string.yards));
                } else {
                    binding.switchUnitDistance.setText(getResources().getString(R.string.meter));
                }
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (binding.switchUnit.isChecked()) {
                        MainActivity.SetGenPrefValue("SYSTEM_UNIT", "IMPERIAL");
                    } else {
                        MainActivity.SetGenPrefValue("SYSTEM_UNIT", "METRIC");
                    }
                    if (binding.switchUnitDistance.isChecked()) {
                        MainActivity.SetGenPrefValue("SYSTEM_DISTANCE", "YARDS");
                    } else {
                        MainActivity.SetGenPrefValue("SYSTEM_DISTANCE", "METERS");
                    }
                    Toast myToast = Toast.makeText(getActivity(), getString(R.string.configIsSave), Toast.LENGTH_LONG);
                    myToast.show();
                }
                catch(Exception e)
                {
                    Toast myToast = Toast.makeText(getActivity(),  getString(R.string.erreurSaisieChamps), Toast.LENGTH_LONG);
                    myToast.show();
                }
            }
        });

        //Ajout des paramètres
        binding.switchUnit.setChecked(false);
        binding.switchUnitDistance.setChecked(false);
        if(MainActivity.GetPrefValue("SYSTEM_UNIT").equals("IMPERIAL"))
        {
            binding.switchUnit.setChecked(true);
            binding.switchUnit.setText(getResources().getString(R.string.imperial));
        }
        if(MainActivity.GetPrefValue("SYSTEM_DISTANCE").equals("YARDS"))
        {
            binding.switchUnitDistance.setChecked(true);
            binding.switchUnitDistance.setText(getResources().getString(R.string.yards));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}