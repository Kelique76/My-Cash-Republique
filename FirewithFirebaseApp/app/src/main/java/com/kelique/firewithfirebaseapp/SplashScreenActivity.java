package com.kelique.firewithfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class SplashScreenActivity extends AppCompatActivity {
    SessionManager sesi;
    @InjectView(R.id.pulsator)
    PulsatorLayout pulsator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.inject(this);
        pulsator.start();
        sesi = new SessionManager(SplashScreenActivity.this);
        Handler penggangan = new Handler();
        penggangan.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sesi.isLogin()) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }


            }
        }, 2500);
    }
}
