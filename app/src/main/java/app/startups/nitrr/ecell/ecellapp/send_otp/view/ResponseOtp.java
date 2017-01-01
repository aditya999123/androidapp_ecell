package app.startups.nitrr.ecell.ecellapp.send_otp.view;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.send_otp.model.data.SendOtpData;

/**
 * Created by Iket on 8/6/2016.
 */
public class ResponseOtp {

    private List<SendOtpData> success;

    public ResponseOtp(List<SendOtpData> success) {
        this.success = success;
    }

    public List<SendOtpData> getSuccess() {
        return success;
    }
}
