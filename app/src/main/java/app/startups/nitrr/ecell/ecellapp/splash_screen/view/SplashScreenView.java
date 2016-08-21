package app.startups.nitrr.ecell.ecellapp.splash_screen.view;

import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;

/**
 * Created by meghal on 6/8/16.
 */
public interface SplashScreenView {

    void showMessage(String message);

    void fcmInsertStatus(SplashScreenData splashScreenData);

    void showProgressBar(boolean show);
}
