package app.startups.nitrr.ecell.ecellapp.send_otp.model.data;

/**
 * Created by Iket on 8/8/2016.
 */
public class VerifyOtpData {
    private boolean success;
    private String message;
    private String access_token;

    public VerifyOtpData(boolean success, String message, String access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
