package app.startups.nitrr.ecell.ecellapp.welcome.presenter;

import app.startups.nitrr.ecell.ecellapp.welcome.model.SignInProvider;
import app.startups.nitrr.ecell.ecellapp.welcome.model.data.SignInData;
import app.startups.nitrr.ecell.ecellapp.welcome.view.WelcomeView;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by meghal on 8/7/16.
 */
public class SignInPresenterImpl implements SignInPresenter {

    private Observable<SignInData> observable;
    private SignInProvider signInProvider;
    private WelcomeView welcomeView;
    private Subscription subscription;


    public SignInPresenterImpl(WelcomeView welcomeView, SignInProvider signInProvider) {
        this.signInProvider = signInProvider;
        this.welcomeView = welcomeView;
    }

    @Override
    public void requestSignIn(String userId, String userName, String email, String profilePhoto,
                              int signInType) {


        observable = signInProvider.requestSignIn(userId, userName, email, profilePhoto, signInType);


        subscription = observable.observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).subscribe(new Observer<SignInData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SignInData signInData) {

                welcomeView.isLoggedIn(true);
            }
        });

    }
}
