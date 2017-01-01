package app.startups.nitrr.ecell.ecellapp.welcome.presenter;

/**
 * Created by meghal on 8/7/16.
 */
public interface SignInPresenter {

    void requestSignIn(String userId, String userName, String email, String profilePhoto, int signInType);

}
