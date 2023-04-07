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
import com.chasseprecision.balstikconverter.databinding.MenuHomeBinding;

public class MenuHome extends Fragment {

    private MenuHomeBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = MenuHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonMoaToMil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MoaToMil);
            }
        });
        binding.buttonMilToMoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MilToMoa);
            }
        });
        binding.buttonMoaToDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MoaToDegree);
            }
        });
        binding.buttonMoaToRadians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MoaToRadians);
            }
        });
        binding.buttonMilToDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MilToDegree);
            }
        });
        binding.buttonMilToRadians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_MilToRadians);
            }
        });
        binding.buttonCorrectionCible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_CorrectionCible);
            }
        });
        binding.buttonCorrectionLunette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuHome.this)
                        .navigate(R.id.action_MenuHome_to_CorrectionLunette);
            }
        });
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(),  getString(R.string.linksite), Toast.LENGTH_LONG);
                myToast.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}