package app.startups.nitrr.ecell.ecellapp.welcome.presenter;

import app.startups.nitrr.ecell.ecellapp.welcome.OnSignInCallback;
import app.startups.nitrr.ecell.ecellapp.welcome.model.SignInProvider;
import app.startups.nitrr.ecell.ecellapp.welcome.view.WelcomeView;

/**
 * Created by meghal on 8/7/16.
 */
public class SignInPresenterImpl implements SignInPresenter {

    private SignInProvider signInProvider;
    private WelcomeView welcomeView;

    public SignInPresenterImpl(WelcomeView welcomeView, SignInProvider signInProvider) {
        this.signInProvider = signInProvider;
        this.welcomeView = welcomeView;
    }

    @Override
    public void requestSignIn(String userId, String userName, String email, String profilePhoto,
                              int signInType) {

        signInProvider.requestSignIn(userId, userName, email, profilePhoto, signInType
                , new OnSignInCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFailed() {

                    }
                });

    }
}
