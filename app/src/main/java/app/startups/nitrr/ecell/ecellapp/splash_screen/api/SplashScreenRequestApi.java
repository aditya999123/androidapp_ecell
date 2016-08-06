package app.startups.nitrr.ecell.ecellapp.splash_screen.api;

import app.startups.nitrr.ecell.ecellapp.helper.Urls;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by meghal on 6/8/16.
 */
public interface SplashScreenRequestApi {

    @POST(Urls.BASE_URL_A)
    Observable<SplashScreenData> insertFcm(String fcm);


}
