package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpSent;
import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;

/**
 * Created by meghal on 2/8/16.
 */
public class RetrofitOtpProvider implements OtpProvider {
    @Override
    public void sendOtp(String mobile, OnOtpSent onOtpSent) {


        SendOtpData sendOtpData=new SendOtpData(true);
        onOtpSent.onSuccess(sendOtpData);
        onOtpSent.onFailed();
    }
}
