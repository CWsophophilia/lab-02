package com.example.listycity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int selectedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);


        RelativeLayout overarchingLayout = findViewById(R.id.overarching_layout);

        Button addButton = findViewById(R.id.add_button);
        Button deleteButton = findViewById(R.id.delete_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View inputBar = inflater.inflate(R.layout.input_bar, overarchingLayout, false);
                overarchingLayout.addView(inputBar);

                EditText cityEditBar = findViewById(R.id.edit_text);
                Button confirmButton = findViewById(R.id.confirm_button);

                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.add(cityEditBar.getText().toString());
                        cityAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.content,dataList);
                        cityList.setAdapter(cityAdapter);
                        overarchingLayout.removeView(inputBar);
                    }
                });
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                selectedItem = position;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem == -1) return;
                dataList.remove(selectedItem);
                cityAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.content,dataList);
                cityList.setAdapter(cityAdapter);
                selectedItem = -1;
            }
        });
    }
}