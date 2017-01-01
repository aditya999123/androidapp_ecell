package app.startups.nitrr.ecell.ecellapp.send_otp;

import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.VerifyOtpData;

/**
 * Created by Iket on 8/8/2016.
 */
public interface OnOtpVerify {
    void onSuccess(VerifyOtpData verifyOtpData);

    void onFailed();
}
