package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpSent;

/**
 * Created by meghal on 2/8/16.
 */
public interface OtpProvider {

void sendOtp(String mobile, OnOtpSent onOtpSent);

}
