package com.example.mapsexample_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMap,btnLocation, btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMap=findViewById(R.id.btnMap);
        btnLocation=findViewById(R.id.btnMapLocation);
        btnSearch=findViewById(R.id.btnMapSearch);
        Intent maps= new Intent(getApplicationContext(), MapsActivity.class);
        Intent location= new Intent(getApplicationContext(), LocationActivity.class);
        Intent searchView= new Intent(getApplicationContext(), SearchViewMapActivity.class);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(maps);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(location);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(searchView);
            }
        });

    }
}