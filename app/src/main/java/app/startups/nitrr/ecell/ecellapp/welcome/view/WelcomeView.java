package app.startups.nitrr.ecell.ecellapp.welcome.view;

/**
 * Created by meghal on 8/7/16.
 */
public interface WelcomeView {


    void showProgressDialog(boolean show);

    void isLoggedIn(boolean show);

    void showMessage(String message);
}
