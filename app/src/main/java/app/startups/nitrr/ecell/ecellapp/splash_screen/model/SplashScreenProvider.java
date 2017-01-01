package app.startups.nitrr.ecell.ecellapp.splash_screen.model;

import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
import rx.Observable;

/**
 * Created by meghal on 6/8/16.
 */
public interface SplashScreenProvider {

    Observable<SplashScreenData> insertFcm(String fcm);
}
