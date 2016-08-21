package app.startups.nitrr.ecell.ecellapp.splash_screen.presenter;

import app.startups.nitrr.ecell.ecellapp.splash_screen.model.SplashScreenProvider;
import app.startups.nitrr.ecell.ecellapp.splash_screen.model.data.SplashScreenData;
import app.startups.nitrr.ecell.ecellapp.splash_screen.view.SplashScreenView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by meghal on 6/8/16.
 */
public class SplashScreenPresenterImpl implements SplashScreenPresenter {

    SplashScreenView splashScreenView;
    SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashScreenView splashScreenView,
                                     SplashScreenProvider splashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void insertFcm(String fcm) {
        splashScreenView.showProgressBar(true);

        Observable<SplashScreenData> splashScreenDataObservable = splashScreenProvider.insertFcm(fcm);
        splashScreenDataObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Observer<SplashScreenData>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

                e.printStackTrace();
                splashScreenView.showMessage("Successful");

            }
            @Override
            public void onNext(SplashScreenData splashScreenData) {

                if(splashScreenData.isSuccess()){
                    splashScreenView.fcmInsertStatus(splashScreenData);
                    splashScreenView.showProgressBar(false);

                }else{
                    splashScreenView.showMessage(splashScreenData.getMessage());
                }

            }
        });

    }
}
