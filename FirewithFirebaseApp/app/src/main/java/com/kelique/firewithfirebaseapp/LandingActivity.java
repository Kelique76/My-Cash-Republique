package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity{


    @InjectView(R.id.btnTambahpsrt)
    Button btnTambahpsrt;
    @InjectView(R.id.btnEcash)
    Button btnEcash;
    @InjectView(R.id.btnLiatpserta)
    Button btnLiatpserta;
    @InjectView(R.id.btnBukatbank)
    Button btnBukatbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.inject(this);

//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.WadahLaci);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.open, R.string.closed);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.NapView);
//        navigationView.setNavigationItemSelectedListener(null);
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.WadahLaci);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.tambah) {
//            startActivity(new Intent(LandingActivity.this, TambahAktifitas.class));
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    @SuppressWarnings("StatementWithEmptyBody")
//
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_Tamang) {
//            FragmentTamang fragmentatu = new FragmentTamang();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentatu).commit();
//            // Handle the camera action
//        } else if (id == R.id.nav_ecasha) {
//            FragmenteCash fragmentdua = new FragmenteCash();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentdua).commit();
//
//        } else if (id == R.id.nav_tBank) {
//            FragmentBank fragmentiga = new FragmentBank();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentiga).commit();
//
//
//        } else if (id == R.id.nav_liatAngg) {
//            FragmentLiang fragmentpat = new FragmentLiang();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentpat).commit();
//
//        } else if (id == R.id.nav_telp) {
//            FragmenTelp fragmentlima = new FragmenTelp();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentlima).commit();
//
//        } else if (id == R.id.nav_sms) {
//            FragmentPesan fragmentnam = new FragmentPesan();
//            FragmentManager fm = getSupportFragmentManager();
//            fm.beginTransaction().replace(R.id.WadahLaci, fragmentnam).commit();
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.WadahLaci);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @OnClick({R.id.btnTambahpsrt, R.id.btnEcash, R.id.btnLiatpserta, R.id.btnBukatbank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTambahpsrt:
                startActivity(new Intent(LandingActivity.this, TambahAktifitas.class));
                break;
            case R.id.btnEcash:
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ptdam.emoney");
                if (intent != null) {
                    // We found the activity now start the activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "com.ptdam.emoney"));
                    startActivity(intent);
                }
                break;
            case R.id.btnLiatpserta:
                startActivity(new Intent(LandingActivity.this, MainActivity.class));
                break;
            case R.id.btnBukatbank:
                Intent brintent = getPackageManager().getLaunchIntentForPackage("bri.delivery.brimobile");
                if (brintent != null) {
                    // We found the activity now start the activity
                    brintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(brintent);
                } else {
                    // Bring user to the market or let them choose an app?
                    brintent = new Intent(Intent.ACTION_VIEW);
                    brintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    brintent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "bri.delivery.brimobile"));
                    startActivity(brintent);
                }
                break;
        }

    }
}
