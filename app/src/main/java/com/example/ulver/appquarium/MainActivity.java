package com.example.ulver.appquarium;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Connexion");

        final EditText etMail= (EditText) findViewById(R.id.etMail);
        final EditText etMdp= (EditText) findViewById(R.id.etMdp);
        final Button btLogin= (Button) findViewById(R.id.btLogin);
        final Button btForgotMdp= (Button) findViewById(R.id.btForgotMdp);
        final Button btRegisterLink= (Button) findViewById(R.id.btRegisterLink);

        btRegisterLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        }
        );

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String mail = etMail.getText().toString();
                final String mdp = etMdp.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                int id = jsonResponse.getInt("id");

                                Intent accueilIntent = new Intent(MainActivity.this, AccueilActivity.class);
                                accueilIntent.putExtra("id", id);
                                MainActivity.this.startActivity(accueilIntent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Erreur de connexion")
                                        .setNegativeButton("Réessayer", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(mail, mdp, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
