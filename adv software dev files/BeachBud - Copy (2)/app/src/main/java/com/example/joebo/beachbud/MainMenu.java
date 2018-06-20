package com.example.joebo.beachbud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    private Button myAccount, weather1, compass, sosCall, maps, beachInfo;

    @Override                                               //main menu of application, the same as the beach info page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        myAccount = findViewById(R.id.myAccount);
        weather1 = findViewById(R.id.weather);
        compass = findViewById(R.id.compass);
        sosCall = findViewById(R.id.sosCall);
        maps = findViewById(R.id.map);
        beachInfo = findViewById(R.id.beachInfo);

        myAccount.setOnClickListener(this);
        weather1.setOnClickListener(this);
        compass.setOnClickListener(this);
        sosCall.setOnClickListener(this);
        maps.setOnClickListener(this);
        beachInfo.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
        case R.id.myAccount:
              startActivity(new Intent(this, ProfileActivity.class));
               break;
           case R.id.weather:
               startActivity(new Intent(this, CurrentWeather.class));
                break;
            case R.id.compass:
                startActivity(new Intent(this, Compass.class));
                break;
            case R.id.sosCall:
                startActivity(new Intent(this, SosCall.class));
                break;
            case R.id.map:
                startActivity(new Intent(this, Maps.class));
                break;
            case R.id.beachInfo:
                startActivity(new Intent(this, BeachInfo.class));
                break;
        }


    }

}
