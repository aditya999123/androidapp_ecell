package app.startups.nitrr.ecell.ecellapp.SplashScreen.SplashScreenView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.startups.nitrr.ecell.ecellapp.ContactUs.ContactUs;
import app.startups.nitrr.ecell.ecellapp.Login.Login;
import app.startups.nitrr.ecell.ecellapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Button buttoncontact=(Button)findViewById(R.id.buttoncontact);
        buttoncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashScreen.this,ContactUs.class);
                startActivity(i);

            }
        });
        Button buttonlogin=(Button)findViewById(R.id.buttonlogin);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashScreen.this,Login.class);
                startActivity(i);

            }
        });
    }
}
