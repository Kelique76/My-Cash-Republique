package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.textMail)
    EditText textMail;
    @InjectView(R.id.textPass1)
    EditText textPass1;
    @InjectView(R.id.buttonDaftar)
    Button buttonDaftar;
    @InjectView(R.id.buttonDaftar2)
    Button buttonDaftar2;

    private FirebaseAuth mAuth;
    SessionManager sesi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mAuth = FirebaseAuth.getInstance();
        sesi = new SessionManager(LoginActivity.this);
    }

    @OnClick(R.id.buttonDaftar)
    public void onViewClicked() {
        String mail = textMail.getText().toString();
        String pass1 = textPass1.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            textMail.setError("Email Harus di isi!");
        } else if (TextUtils.isEmpty(pass1)) {
            textPass1.setError("Password Harus diisi");
        } else {

            mAuth.signInWithEmailAndPassword(mail, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Tanggapan", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("tanggapan", "Aktivitas Masuk gagal", task.getException());
                                Toast.makeText(LoginActivity.this, "Gagal Masuk",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                                Toast.makeText(LoginActivity.this, "Berhasil Masuk",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }


    }

    @OnClick(R.id.buttonDaftar2)
    public void onViewClicked(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        }


