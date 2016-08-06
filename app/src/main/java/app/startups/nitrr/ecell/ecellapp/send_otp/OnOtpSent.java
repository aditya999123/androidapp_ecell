package app.startups.nitrr.ecell.ecellapp.send_otp;

import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;

/**
 * Created by meghal on 2/8/16.
 */
public interface OnOtpSent {


     void onSuccess(SendOtpData sendOtpData);

     void onFailed();
}
