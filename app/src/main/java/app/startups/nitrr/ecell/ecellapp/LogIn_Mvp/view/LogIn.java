package app.startups.nitrr.ecell.ecellapp.LogIn_Mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.send_otp.view.SendOtp;

/**
 * Created by Iket on 8/8/2016.
 */

public class LogIn extends AppCompatActivity implements LogIn_View{
    String refreshedToken="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.sem_ar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        final Spinner spinner1 = (Spinner) findViewById(R.id.Spinner02);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.branch_ar, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("Log In");

        Button otp = (Button) findViewById(R.id.next_button);
        otp.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText mEdit = (EditText) findViewById(R.id.name);
                        String name=mEdit.getText().toString();

                        EditText mEdit1 = (EditText) findViewById(R.id.last_name);
                        String lname=mEdit1.getText().toString();

                        EditText mEdit2 = (EditText) findViewById(R.id.email_id);
                        String email=mEdit2.getText().toString();

                        EditText mEdit3 = (EditText) findViewById(R.id.college);
                        String college=mEdit3.getText().toString();

                        String branch=spinner1.getSelectedItem().toString();

                        refreshedToken = FirebaseInstanceId.getInstance().getToken();
                        Log.d("ResponseOtp",branch);

                        String sem=spinner.getSelectedItem().toString();
                        if(emailInvalid(email))
                        {
                            Toast.makeText(LogIn.this, "ENTER CORRECT EMAIL ID!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else


                        {
                            Intent i = new Intent(LogIn.this,SendOtp.class);
                            i.putExtra("name", name);
                            i.putExtra("lname", lname);
                            i.putExtra("email", email);
                            i.putExtra("college", college);
                            i.putExtra("branch", branch);
                            i.putExtra("sem", sem);
                            i.putExtra("token",refreshedToken);
                            Log.d("ResponseOtp", "" + name);
                            startActivity(i);
                        }
                    }
                });

    }


    @Override
    public boolean emailInvalid(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean a=matcher.matches();
        return !a;
    }
}
