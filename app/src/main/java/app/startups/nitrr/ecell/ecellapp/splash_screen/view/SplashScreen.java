package app.startups.nitrr.ecell.ecellapp.splash_screen.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.welcome.view.WelcomeActivity;

public class SplashScreen extends AppCompatActivity {

    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        sharedPrefs = new SharedPrefs(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPrefs.isLoggedIn()) {
                    Intent in = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(in);
                    finish();
                } else {
                    Intent signIn = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(signIn);
                    finish();

                }
            }
        }, 4000);

    }
}
