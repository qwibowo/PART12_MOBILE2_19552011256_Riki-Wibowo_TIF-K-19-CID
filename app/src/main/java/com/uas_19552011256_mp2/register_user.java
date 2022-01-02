package com.uas_19552011256_mp2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register_user extends AppCompatActivity {
    EditText mTextUsername, mTextPassword, mTextCnfPassword, m_namalengkap, m_email;
    Button add,close;
    DBUser db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        db= new DBUser(this);
        m_namalengkap= findViewById(R.id.edittext_nama);
        mTextUsername= findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = findViewById(R.id.edittext_cinfir_pasword);
        m_email = findViewById(R.id.edittext_email);
        add = (Button)findViewById(R.id.add);
        close=(Button)findViewById(R.id.exit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =mTextUsername.getText().toString().trim();
                String password =mTextPassword.getText().toString().trim();
                String cnf_pwd =mTextCnfPassword.getText().toString().trim();
                String nama = m_namalengkap.getText().toString().trim();
                String email = m_email.getText().toString().trim();
                if (nama.equals("") || username.equals("") || password.equals("") || email.equals("")) {
                    Toast.makeText(register_user.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(cnf_pwd)) {
                        boolean val = db.tambahUser(username, email, password, nama);
                        if (val) {
                            Toast.makeText(register_user.this, "Anda Berhasil menambahkan user " + nama, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(register_user.this, "Anda gagal Registrasi user" + nama, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register_user.this, "Password tidak sama", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
