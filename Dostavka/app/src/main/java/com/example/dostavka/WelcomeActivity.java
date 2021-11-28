package com.example.dostavka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button driverbutton, helbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        driverbutton = (Button)findViewById(R.id.driverbutton);
        helbutton = (Button)findViewById(R.id.helbutton);

        driverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent driverIntent = new Intent(WelcomeActivity.this, DriverRegrLoginActivity.class);
                startActivity(driverIntent);
            }
        });

        helbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helIntent = new Intent(WelcomeActivity.this, CustomerRegLoginActivity.class);
                startActivity(helIntent);
            }
        });
    }
}