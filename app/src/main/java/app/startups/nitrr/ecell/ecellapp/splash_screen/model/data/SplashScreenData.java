package app.startups.nitrr.ecell.ecellapp.splash_screen.model.data;

/**
 * Created by meghal on 6/8/16.
 */
public class SplashScreenData {

    private boolean success;
    private String message;

    public SplashScreenData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
