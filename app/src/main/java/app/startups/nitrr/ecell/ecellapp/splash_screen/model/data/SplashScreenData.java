package app.startups.nitrr.ecell.ecellapp.splash_screen.model.data;

/**
 * Created by meghal on 6/8/16.
 */
public class SplashScreenData {

    private boolean success;
    private String message;
    private int version;

    public SplashScreenData(boolean success, String message, int version) {
        this.success = success;
        this.message = message;
        this.version = version;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getVersion() {
        return version;
    }
}
