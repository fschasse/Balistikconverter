package com.chasseprecision.balstikconverter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.chasseprecision.balstikconverter.R;
import com.chasseprecision.balstikconverter.databinding.AboutBinding;

public class About extends Fragment {

    private AboutBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AboutBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(About.this)
                        .navigate(R.id.action_About_to_MenuHome);
            }
        });

        binding.webViewAbout.loadUrl("file:///android_asset/about.html");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}