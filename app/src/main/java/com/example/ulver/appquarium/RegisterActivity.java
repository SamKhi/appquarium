package com.example.ulver.appquarium;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edenn on 07/07/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Créer un compte");
        final EditText etMail = (EditText) findViewById(R.id.etMail);
        final EditText etMdp = (EditText) findViewById(R.id.etMdp);
        final EditText etConfirmMdp = (EditText) findViewById(R.id.etConfirmMdp);
        final EditText etPseudo = (EditText) findViewById(R.id.etPseudo);
        final EditText etPays = (EditText) findViewById(R.id.etPays);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);
        final Button btCancel= (Button) findViewById(R.id.btCancel);
        final Button btRegister= (Button) findViewById(R.id.btRegister);

        btCancel.setOnClickListener(new View.OnClickListener()
            {
              @Override
              public void onClick(View v)
              {
                  RegisterActivity.this.finish();
              }
            }
        );

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                {
                    final String mail = etMail.getText().toString();
                    final String mdp = etMdp.getText().toString();
                    final String confirmMdp = etConfirmMdp.getText().toString();
                    final String pseudo = etPseudo.getText().toString();
                    final String pays = etPays.getText().toString();
                    final String address = etAddress.getText().toString();
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("L'enregistement a échoué")
                                            .setNegativeButton("Réessayer", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegisterRequest registerRequest = new RegisterRequest(mail, mdp,confirmMdp,pseudo,pays,address, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
    });
}
}