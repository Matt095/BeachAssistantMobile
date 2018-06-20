package com.example.joebo.beachbud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
                    //beach info class to handle all the on click listeners of the menu when a speific beach is clicked
public class BeachInfo extends AppCompatActivity implements View.OnClickListener{

    private Button formby, ainsdale, southport, newBrighton, moreton, hoylake, westKirby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_info);

              formby  = findViewById(R.id.formby);      //finding elements by id
               ainsdale = findViewById(R.id.ainsdale);
                southport = findViewById(R.id.southport);
                newBrighton = findViewById(R.id.newBrighton);
                moreton = findViewById(R.id.moreton);
                hoylake = findViewById(R.id.hoylake);
                westKirby = findViewById(R.id.westKirby);

                formby.setOnClickListener(this);            //set on click listener for each option
                southport.setOnClickListener(this);
                newBrighton.setOnClickListener(this);
                moreton.setOnClickListener(this);
                hoylake.setOnClickListener(this);
                ainsdale.setOnClickListener(this);
                westKirby.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {             //switch statement to select correct corresponding class to launch
            case R.id.formby:
                startActivity(new Intent(this, Formby.class));
                break;
            case R.id.ainsdale:
                startActivity(new Intent(this, Ainsdale.class));
                break;
            case R.id.southport:
                startActivity(new Intent(this, Southport.class));
                break;
            case R.id.newBrighton:
                startActivity(new Intent(this, NewBrighton.class));
                break;
            case R.id.moreton:
                startActivity(new Intent(this, Moreton.class));
                break;
            case R.id.hoylake:
                startActivity(new Intent(this, Hoylake.class));
                break;
            case R.id.westKirby:
                startActivity(new Intent(this, WestKirby.class));
                break;
        }
    }}
