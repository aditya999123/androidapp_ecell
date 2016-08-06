package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.RetrofitOtpProvider;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenter;
import app.startups.nitrr.ecell.ecellapp.send_otp.presenter.SendOtpPresenterImpl;

public class SendOtp extends AppCompatActivity implements SendOtpView {
    private ProgressBar progressBar;
    SendOtpPresenter sendOtpPresenter;
    String num1="",name="",lname="",email="",college="",branch="",sem="",otp="",token="",url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__verification);
        progressBar=(ProgressBar)findViewById(R.id.progressBar1);
        Button btn=(Button)findViewById(R.id.btn);
        Button btn1=(Button)findViewById(R.id.btn1);
        getIntents();
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        EditText num=(EditText)findViewById(R.id.num);
                        num1=num.getText().toString();
                        if(num1.length()!=10)
                        {
                            showMessage("ENTER CORRECT MOBILE NUMBER!");
                        }
                        else
                            sendOtpPresenter.sendOtp(num1,name,url);

                    }
                });

        sendOtpPresenter=new SendOtpPresenterImpl(new RetrofitOtpProvider(),this);
    }

    @Override
    public void showLoading(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(SendOtp.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtpSent() {

        EditText verify=(EditText)findViewById(R.id.verify);
        verify.setVisibility(View.VISIBLE);
        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setVisibility(View.VISIBLE);
//        Button btn=(Button)findViewById(R.id.btn);
//        btn.setText("Resend Otp");

    }
    public void getIntents()
    {
        name= getIntent().getExtras().getString("name").toString();
        lname= getIntent().getExtras().getString("lname").toString();
        email= getIntent().getExtras().getString("email").toString();
        token=getIntent().getExtras().getString("token").toString();
        college= getIntent().getExtras().getString("college").toString();
        branch= getIntent().getExtras().getString("branch").toString();
        sem= getIntent().getExtras().getString("sem").toString();
        EditText url1=(EditText)findViewById(R.id.url);
        url=url1.getText().toString();

    }
}
