package com.example.joebo.beachbud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{  //on click listener implemented

    private EditText editTextUsername, editTextPassword;    //declaring items
    private Button buttonLogin;
    private Button buttonRegister;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){       //to check if the peron is logged in using shared preferences
            finish();
           // startActivity(new Intent(this, ProfileActivity.class));
            startActivity(new Intent(this, MainMenu.class));
            return;
        }

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");            //progress dial incase you dont get a response immpediatly from server

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);        //on click listeners

    }
    private void userLogin() {   //user log in method
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,                        //post to mysql server to log in
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(!obj.getBoolean("error")){                           //get instance of shared prefernces if logged in
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("email"),
                                                obj.getString("fullName"),
                                                obj.getString("age"),
                                                obj.getString("gender")
                                        );
                                //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                startActivity(new Intent(getApplicationContext(), MainMenu.class));
                                finish();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);                       //gets pass and username
                params.put("password", password);
                return params;

            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin){
            userLogin();
        }else if (view == buttonRegister){                  //registers the clicks inase register is selected instead of log in
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
