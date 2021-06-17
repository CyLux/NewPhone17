package com.example.newphone17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sign_up extends AppCompatActivity {
    Button btnSignUp;
    EditText edUsername, edPassword, edFirstname, edSecondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = findViewById(R.id.button);
        edUsername = findViewById(R.id.username2);
        edPassword = findViewById(R.id.password2);
        edFirstname = findViewById(R.id.firstname);
        edSecondName = findViewById(R.id.secondname);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edFirstname.getText().toString()) || TextUtils.isEmpty(edSecondName.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    Toast.makeText(Sign_up.this, "Поля пустые", Toast.LENGTH_LONG).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setEmail(edUsername.getText().toString());
                    registerRequest.setFirstName(edFirstname.getText().toString());
                    registerRequest.setLastName(edSecondName.getText().toString());
                    registerRequest.setPassword(edPassword.getText().toString());

                    SignUpUsers(registerRequest);
                }
            }
        });
    }

    public void SignUpUsers(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Sign_up.this, "Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Sign_up.this, SignIn.class));
                    finish();
                } else {
                    Toast.makeText(Sign_up.this, "Возникла ощибка, повторите позже...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(Sign_up.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init() {
        btnSignUp = findViewById(R.id.button);
        edUsername = findViewById(R.id.username2);
        edPassword = findViewById(R.id.password2);
        edFirstname = findViewById(R.id.firstname);
        edSecondName = findViewById(R.id.secondname);
    }
}

