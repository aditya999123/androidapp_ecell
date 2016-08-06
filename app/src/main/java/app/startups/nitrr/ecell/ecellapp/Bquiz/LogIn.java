package app.startups.nitrr.ecell.ecellapp.Bquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.startups.nitrr.ecell.ecellapp.R;

public class LogIn extends AppCompatActivity {
    String refreshedToken="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] ar={1, 2, 3, 4, 5, 6, 7, 8};

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
//iket

        Button otp = (Button) findViewById(R.id.next_button);
           otp.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText mEdit = (EditText) findViewById(R.id.name);
                        String name=mEdit.getText().toString();
                        name=space(name);
                        EditText mEdit1 = (EditText) findViewById(R.id.last_name);
                        String lname=mEdit1.getText().toString();
                        lname=space(lname);
                        EditText mEdit2 = (EditText) findViewById(R.id.email_id);
                        String email=mEdit2.getText().toString();
                        email=space(email);
                        EditText mEdit3 = (EditText) findViewById(R.id.college);
                        String college=mEdit3.getText().toString();
                        college=space(college);
                        String branch=spinner1.getSelectedItem().toString();
                        branch=space(branch);
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
                            Intent i = new Intent(LogIn.this, Sms_Verification.class);
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
    boolean emailInvalid(String email)
    {
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
    String space(String name)
    {
        char ch;String w="";
        for(int i=0;i<name.length();i++)
        {
         ch=(char)name.charAt(i);
            int j=(int)ch;
            if(j!=32)
                w=w+ch;
            else
                w=w+"%20";

        }
        return w;
    }


    private class MyOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(parent.getContext(), "Item is " +
                    parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
