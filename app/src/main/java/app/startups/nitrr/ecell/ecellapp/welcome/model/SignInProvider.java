package app.startups.nitrr.ecell.ecellapp.welcome.model;

import app.startups.nitrr.ecell.ecellapp.welcome.OnSignInCallback;
import app.startups.nitrr.ecell.ecellapp.welcome.model.data.SignInData;
import rx.Observable;

/**
 * Created by meghal on 8/7/16.
 */
public interface SignInProvider {
    // Signin type is 0 for facebook and 1 for Google plus .
    Observable<SignInData> requestSignIn(String userId, String username, String email, String profilePhoto
            , int signInType);

}
