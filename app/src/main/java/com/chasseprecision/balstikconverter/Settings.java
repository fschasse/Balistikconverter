package com.chasseprecision.balstikconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.chasseprecision.balstikconverter.databinding.SettingsBinding;

public class Settings extends Fragment {

    private SettingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = SettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Settings.this)
                        .navigate(R.id.action_Settings_to_MenuHome);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.SetPrefValue("SCOPE_VALUE_UD", binding.FIELDValeurClicUD.getText().toString());
                    MainActivity.SetPrefValue("SCOPE_VALUE_LR", binding.FIELDValeurClicLR.getText().toString());
                    if (binding.toggleUnitUD.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_UD", binding.toggleUnitUD.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_UD", binding.toggleUnitUD.getTextOff().toString());
                    }
                    if (binding.toggleUnitRL.isChecked()) {
                        MainActivity.SetPrefValue("SCOPE_UNIT_LR", binding.toggleUnitRL.getTextOn().toString());
                    } else {
                        MainActivity.SetPrefValue("SCOPE_UNIT_LR", binding.toggleUnitRL.getTextOff().toString());
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
        binding.FIELDValeurClicUD.setText(MainActivity.GetPrefValue("SCOPE_VALUE_UD"));
        binding.FIELDValeurClicLR.setText(MainActivity.GetPrefValue("SCOPE_VALUE_LR"));
        binding.toggleUnitUD.setChecked(true);
        binding.toggleUnitRL.setChecked(true);
        if(MainActivity.GetPrefValue("SCOPE_UNIT_UD").equals("MOA"))
        {
            binding.toggleUnitUD.setChecked(false);
        }
        if(MainActivity.GetPrefValue("SCOPE_UNIT_LR").equals("MOA"))
        {
            binding.toggleUnitRL.setChecked(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}