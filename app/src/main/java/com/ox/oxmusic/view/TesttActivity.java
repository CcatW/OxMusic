package com.ox.oxmusic.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ox.oxmusic.R;

public class TesttActivity extends AppCompatActivity {
    boolean isChecked = false;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    isChecked = true;
                } else if(isChecked == false){
                    isChecked = false;
                }
            }
        });
    }
}