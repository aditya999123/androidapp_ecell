package app.startups.nitrr.ecell.ecellapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import app.startups.nitrr.ecell.ecellapp.Blogs.view.Blogs;
import app.startups.nitrr.ecell.ecellapp.ContactUs.ContactUs;
import app.startups.nitrr.ecell.ecellapp.Login.Login;
import app.startups.nitrr.ecell.ecellapp.R;

public class Home extends AppCompatActivity {
    private Button buttonlogin;
    private Button buttoncontact;
    private Toolbar toolbar;
    private Button blogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttoncontact = (Button) findViewById(R.id.buttoncontact);
        buttonlogin= (Button) findViewById(R.id.buttonlogin);
        blogs=(Button)findViewById(R.id.blogs);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttoncontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, ContactUs.class);
                startActivity(i);

            }
        });
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Login.class);
                startActivity(i);

            }
        });

        blogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Home.this, Blogs.class);
                startActivity(in);

            }
        });
    }

}

