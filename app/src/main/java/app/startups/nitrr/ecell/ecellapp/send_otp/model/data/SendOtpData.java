package app.startups.nitrr.ecell.ecellapp.send_otp.model.data;

/**
 * Created by meghal on 2/8/16.
 */
public class SendOtpData {

    private boolean success;
    private String message;

    public SendOtpData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public SendOtpData(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

}
