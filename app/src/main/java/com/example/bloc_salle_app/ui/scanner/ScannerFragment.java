package com.example.bloc_salle_app.ui.scanner;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bloc_salle_app.MainActivity;
import com.example.bloc_salle_app.R;
import com.example.bloc_salle_app.databinding.FragmentScannerBinding;
import com.example.bloc_salle_app.Scanner;


public class ScannerFragment extends Fragment {
    private ScannerViewModel scannerViewModel;
    private FragmentScannerBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        scannerViewModel = new ViewModelProvider(this).get(ScannerViewModel.class);
        binding = FragmentScannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textScanner;
        Button btn_scanner = root.findViewById(R.id.btn_scanner);
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Scanner.class);
                startActivity(intent);
            }
        });
        scannerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}