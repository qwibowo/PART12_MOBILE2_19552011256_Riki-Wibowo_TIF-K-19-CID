package com.uas_19552011256_mp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class loginActivity extends AppCompatActivity {

    //Pendefinisian Variable
    EditText loginUsername , loginPassword;
    Button loginButton, exitButton;
    DBUser myDb;
    TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.edittext_username);
        loginPassword = findViewById(R.id.edittext_password);
        loginButton = findViewById(R.id.btlogin);
        exitButton = findViewById(R.id.btexit);
        reg = findViewById(R.id.reg);

        myDb = new DBUser(this);

        loginUser();
    }

    private void loginUser(){
        loginButton.setOnClickListener(v -> {
            boolean var = myDb.periksaUser(loginUsername.getText().toString() , loginPassword.getText().toString());
            if (var){
                String nama = loginUsername.getText().toString().trim();
                Toast.makeText(loginActivity.this, "Login Berhasil, Selamat Datang  "+nama, Toast.LENGTH_SHORT).show();
                Intent keHal2 = new Intent(loginActivity.this, MainActivity.class);
                startActivity(keHal2);
            }else{
                Toast.makeText(loginActivity.this, "Login Gagal !, Periksa Kembali User & Password Anda...", Toast.LENGTH_SHORT).show();
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this , register_user.class));
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
