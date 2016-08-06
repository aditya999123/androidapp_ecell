package app.startups.nitrr.ecell.ecellapp.splash_screen.view;

/**
 * Created by meghal on 6/8/16.
 */
public interface SplashScreenView {

    void showMessage(String message);

    void fcmInsertStatus(boolean successful);

    void showProgressBar(boolean show);
}
