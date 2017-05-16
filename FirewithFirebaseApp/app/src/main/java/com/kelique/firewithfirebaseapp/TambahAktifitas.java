package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TambahAktifitas extends AppCompatActivity {

    @InjectView(R.id.textView2)
    EditText textView2;
    @InjectView(R.id.textView3)
    EditText textView3;
    @InjectView(R.id.textView4)
    EditText textView4;
    @InjectView(R.id.textView5)
    EditText textView5;
    @InjectView(R.id.button2)
    Button button2;

    DatabaseReference databaserf;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_aktifitas);
        ButterKnife.inject(this);
        //new child
        databaserf = FirebaseDatabase.getInstance().getReference("Anggota Dagang");
        id=databaserf.push().getKey();



    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
        String nama = textView2.getText().toString();
        String alamat = textView3.getText().toString();
        String ktp = textView4.getText().toString();
        String hp = textView5.getText().toString();

        if (TextUtils.isEmpty(nama)){
            textView2.setError("Nama Harap Diisi");
        }else if(TextUtils.isEmpty(alamat)){
            textView3.setError("Alamat Harap Diisi");
        }else if(TextUtils.isEmpty(alamat)){
            textView4.setError("KTP Harap Diisi");
        }else if(TextUtils.isEmpty(alamat)){
            textView5.setError("HP Harap Diisi");
        }

        Anggota ang= new Anggota(nama,alamat,ktp,hp);
        databaserf.child(id).setValue(ang);
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        Toast.makeText(getApplicationContext(),"Penambahan Anggota Sukses",Toast.LENGTH_SHORT).show();
    }
}
