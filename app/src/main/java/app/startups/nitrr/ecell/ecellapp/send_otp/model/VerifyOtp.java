package app.startups.nitrr.ecell.ecellapp.send_otp.model;

import app.startups.nitrr.ecell.ecellapp.send_otp.OnOtpVerify;

/**
 * Created by Iket on 8/8/2016.
 */
public interface VerifyOtp {

    void verifyOtp(String name, String lname, String email, String college, String sem, String branch, String num, String otp, String fcm, OnOtpVerify onOtpVerify);
}
