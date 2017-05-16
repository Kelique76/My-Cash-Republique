package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
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

import static android.R.attr.id;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.textMail)
    EditText textMail;
    @InjectView(R.id.textPass1)
    EditText textPass1;
    @InjectView(R.id.textPass2)
    EditText textPass2;
    @InjectView(R.id.buttonDaftar)
    Button buttonDaftar;

    private FirebaseAuth mAuth;
    SessionManager sesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        mAuth = FirebaseAuth.getInstance();
        sesi = new SessionManager(RegisterActivity.this);
    }

    @OnClick(R.id.buttonDaftar)
    public void onViewClicked() {
        String mail = textMail.getText().toString();
        String pass1 = textPass1.getText().toString();
        String pass2 = textPass2.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            textMail.setError("Email Harus di isi!");
        } else if (TextUtils.isEmpty(pass1)) {
            textPass1.setError("Password Harus diisi");

        } else if (TextUtils.isEmpty(pass2)) {
            textPass2.setError("Password kedua harus di isi sama!");
        } else if (!pass2.equals(pass1)) {
            textPass2.setError("Password anda beda");
        } else {
            mAuth.createUserWithEmailAndPassword(mail, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("tanggapan", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Pendaftaran Gagal",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                Toast.makeText(RegisterActivity.this, "Pendaftaran berhasil",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (id == R.id.listOut) {
            mAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            return true;}
            return super.onOptionsItemSelected(item);
        }

}