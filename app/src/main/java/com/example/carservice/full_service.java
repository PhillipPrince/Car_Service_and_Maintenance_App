package com.example.carservice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class full_service extends Fragment {

    CheckBox oil;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.full_service, container, false);
        oil= view.findViewById(R.id.oil);

        oil.setOnCheckedChangeListener(new serviceChecked());
        return view;
    }
    class serviceChecked implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (oil.isChecked()) {
                oil.setText("Oil changed");
            }
        }
    }
}
