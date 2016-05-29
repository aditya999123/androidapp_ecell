package app.startups.nitrr.ecell.ecellapp.SplashScreen.SplashScreenView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import app.startups.nitrr.ecell.ecellapp.Home.Home;
import app.startups.nitrr.ecell.ecellapp.Login.Login;
import app.startups.nitrr.ecell.ecellapp.R;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(SplashScreen.this, Login.class);
                startActivity(in);
                finish();
            }
        }, 3000);
    }
}
