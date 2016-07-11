package app.startups.nitrr.ecell.ecellapp.welcome.model.data;

/**
 * Created by meghal on 8/7/16.
 */
public class SignInData {

    private boolean success;
    private String message;
    private UserData userData;

    public SignInData(boolean success, String message, UserData userData) {
        this.success = success;
        this.message = message;
        this.userData = userData;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public UserData getUserData() {
        return userData;
    }
}
