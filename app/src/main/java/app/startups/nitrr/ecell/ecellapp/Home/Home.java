package app.startups.nitrr.ecell.ecellapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import app.startups.nitrr.ecell.ecellapp.ContactUs.ContactUs;
import app.startups.nitrr.ecell.ecellapp.Login.Login;
import app.startups.nitrr.ecell.ecellapp.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button buttoncontact = (Button) findViewById(R.id.contactUs);
        buttoncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ContactUs.class);
                startActivity(i);

            }
        });
        Button buttonlogin = (Button) findViewById(R.id.login);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Login.class);
                startActivity(i);

            }
        });
    }
}
