package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.recycler)
    RecyclerView recycler;
    DatabaseReference dadebs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

//        MobileAds.initialize(getApplicationContext(), "ca-app-pub-9672976856924793~8718135467");
//
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        dadebs = FirebaseDatabase.getInstance().getReference("Anggota Dagang");

        myRef.setValue("Hello, World!");

        ambilANggota();


    }

    private void ambilANggota() {
        dadebs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue(String.class);
                Log.d("proses", "Datanya adalah: " + value);

                ArrayList<Anggota2> data = new ArrayList<Anggota2>();
                for (DataSnapshot mm : dataSnapshot.getChildren()){
                    Anggota angg=mm.getValue(Anggota.class);
                    Anggota2 angg2=new Anggota2();

                    angg2.setKeyanggota(mm.getKey());
                    angg2.setNamaanggota(angg.namaanggota);
                    angg2.setAlamatanggota(angg.alamatanggota);
                    angg2.setKTPanggota(angg.ktpanggota);
                    angg2.setHPanggota(angg.hpanggota);

                    data.add(angg2);


                    AnggotaCustom adapter = new AnggotaCustom(MainActivity.this,data,recycler);
                    LinearLayoutManager lin = new LinearLayoutManager(MainActivity.this);
                    recycler.setLayoutManager(lin);
                    recycler.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("proses", "Gagal membaca data yang diminta.", error.toException());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tambah) {
            startActivity(new Intent(MainActivity.this, TambahAktifitas.class));

        }

        return super.onOptionsItemSelected(item);
    }

}
