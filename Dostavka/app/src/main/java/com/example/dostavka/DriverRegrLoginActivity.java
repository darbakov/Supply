package com.example.dostavka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverRegrLoginActivity extends AppCompatActivity {

    TextView driverStatus, question;
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;

    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_regr_login);

        driverStatus = (TextView) findViewById(R.id.statusDriver);
        question = (TextView) findViewById(R.id.accountCreate);
        signInBtn = (Button) findViewById(R.id.signInDriver);
        signUpBtn = (Button) findViewById(R.id.signUpdrier);
        emailET = (EditText) findViewById(R.id.driverEmail);
        passwordET = (EditText) findViewById(R.id.driverPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        signUpBtn.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(false);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                driverStatus.setText("Регистрация для поставщиков");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterDriver(email, password);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                signInDriver(email, password);
            }
        });
    }

    private void signInDriver(String email, String password)
    {
        loadingBar.setTitle("Вход поставщика");
        loadingBar.setMessage("Пожалуйста, дождидесь загрузки");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DriverRegrLoginActivity.this, "Вход прошёл успешно", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent driverIntent = new Intent(DriverRegrLoginActivity.this, DriversMapActivity.class);
                    startActivity(driverIntent);
                }
                else {
                    Toast.makeText(DriverRegrLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterDriver(String email, String password)
    {
        loadingBar.setTitle("Регистрация поставщика");
        loadingBar.setMessage("Пожалуйста, дождидесь загрузки");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DriverRegrLoginActivity.this, "Регистрация прошла успешна", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Intent driverIntent = new Intent(DriverRegrLoginActivity.this, DriversMapActivity.class);
                    startActivity(driverIntent);
                }
                else {
                    Toast.makeText(DriverRegrLoginActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}