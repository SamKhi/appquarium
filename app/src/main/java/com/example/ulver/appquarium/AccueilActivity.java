package com.example.ulver.appquarium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Edenn on 07/07/2017.
 */

public class AccueilActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearMain);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int nb = /*NOMBRE D'AQUARIUMS*/;

        for(int j=0;j<=nb;j++)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView
            TextView aquarium = new TextView(this);
            aquarium.setText(/*NOM DE L'AQUARIUM*/);
            ll.addView(aquarium);

            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setId(/*ID DE L'AQUARIUM*/);
            btn.setText("Détails");
            // set the layoutParams on the button

            // Set click listener for button
            btn.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    int id = btn.getId();

                    Intent accueilIntent = new Intent(AccueilActivity.this, AquariumActivity.class);
                    accueilIntent.putExtra("id", id);
                    AccueilActivity.this.startActivity(accueilIntent);

                }
            });

            //Add button to LinearLayout
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }
    }
}