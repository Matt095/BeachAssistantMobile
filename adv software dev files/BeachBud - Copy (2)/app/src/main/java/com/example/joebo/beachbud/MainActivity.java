package com.example.joebo.beachbud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{        //on click listener


    private EditText editTextFullName, editTextUsername, editTextEmail, editTextAge, editTextGender, editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;

    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){               //same as before, shared pref incase user already logged in
            finish();
            //startActivity(new Intent(this, ProfileActivity.class));
            startActivity(new Intent(this, MainMenu.class));
            return;
        }

        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextAge = findViewById(R.id.editTextAge);
        editTextGender = findViewById(R.id.editTextGender);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewLogin = findViewById(R.id.textViewLogin);

        buttonRegister = findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void registerUser() {       //code to register user.
        final String fullName = editTextFullName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String username = editTextUsername.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String gender = editTextGender.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();  //code to tae parameters from user & also trim

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,           //to call th method to register user and communicate with database
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullName", fullName);
                params.put("username", username);
                params.put("email", email);
                params.put("age", age);
                params.put("gender", gender);
                params.put("password", password);
                return params;     //gets parameters from user and put them into variables.
            }
        };


       RequestHandler.getInstance(this).addToRequestQueue(stringRequest);



    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
            registerUser();
        if(view == textViewLogin)
           startActivity(new Intent(this, loginActivity.class));
    }
}
