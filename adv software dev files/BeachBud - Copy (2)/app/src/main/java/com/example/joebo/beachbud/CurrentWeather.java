package com.example.joebo.beachbud;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class CurrentWeather extends AppCompatActivity {

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;           //imported weather font to display clouds etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_current_weather);

        weatherFont = Typeface.createFromAsset(getAssets(), "fonts/weathericons-regular-webfont.ttf"); //imported weather font to display clouds etc.

        cityField = findViewById(R.id.city_field);         // finding elements by id
        updatedField = findViewById(R.id.updated_field);
        detailsField = findViewById(R.id.details_field);
        currentTemperatureField = findViewById(R.id.current_temperature_field);
        humidity_field = findViewById(R.id.humidity_field);
        pressure_field = findViewById(R.id.pressure_field);
        weatherIcon = findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);                   //setting font


        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);                  //to set the elements to the correct city etc
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("53.4084", "-2.9916"); //  asyncTask.execute("Latitude", "Longitude")  //default city of liverpool
    }
}



