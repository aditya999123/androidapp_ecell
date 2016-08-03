package app.startups.nitrr.ecell.ecellapp.Bquiz.view;

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

import app.startups.nitrr.ecell.ecellapp.Bquiz.Sms_Verification;
import app.startups.nitrr.ecell.ecellapp.Bquiz.presenter.DataRequest;
import app.startups.nitrr.ecell.ecellapp.Bquiz.presenter.MyOnItemSelectedListener;
import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by Iket on 8/2/2016.
 */
public class LogInPage extends AppCompatActivity {
    String refreshedToken="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] ar = {1, 2, 3, 4, 5, 6, 7, 8};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //for combo box that  are used

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
                new View.OnClickListener() {
                    public void onClick(View view) {
                        DataRequest dR = new DataRequest();
                        EditText mEdit = (EditText) findViewById(R.id.name);
                        String name = mEdit.getText().toString();
                        name = dR.space(name);
                        EditText mEdit1 = (EditText) findViewById(R.id.last_name);
                        String lname = mEdit1.getText().toString();
                        lname = dR.space(lname);
                        EditText mEdit2 = (EditText) findViewById(R.id.email_id);
                        String email = mEdit2.getText().toString();
                        email = dR.space(email);
                        EditText mEdit3 = (EditText) findViewById(R.id.college);
                        String college = mEdit3.getText().toString();
                        college = dR.space(college);
                        String branch = spinner1.getSelectedItem().toString();
                        branch = dR.space(branch);
                        refreshedToken = FirebaseInstanceId.getInstance().getToken();
                        Log.d("Response", branch);

                        String sem = spinner.getSelectedItem().toString();
                        if (dR.emailInvalid(email)) {
                            Toast.makeText(LogInPage.this, "ENTER CORRECT EMAIL ID!",
                                    Toast.LENGTH_LONG).show();
                        } else


                        {
                            Intent i = new Intent(LogInPage.this, Sms_Verification.class);
                            i.putExtra("name", name);
                            i.putExtra("lname", lname);
                            i.putExtra("email", email);
                            i.putExtra("college", college);
                            i.putExtra("branch", branch);
                            i.putExtra("sem", sem);
                            i.putExtra("token", refreshedToken);
                            Log.d("Response", "" + name);
                            startActivity(i);
                        }
                    }
                });
    }
}
