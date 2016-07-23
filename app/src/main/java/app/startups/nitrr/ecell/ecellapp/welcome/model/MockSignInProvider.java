package app.startups.nitrr.ecell.ecellapp.welcome.model;

import app.startups.nitrr.ecell.ecellapp.welcome.model.data.SignInData;
import rx.Observable;

/**
 * Created by meghal on 18/7/16.
 */
public class MockSignInProvider implements SignInProvider {


    @Override
    public Observable<SignInData> requestSignIn(String userId, String username, String email,
                                                String profilePhoto, int signInType) {

        Observable<String> observable;
        observable=Observable.just("Ramya this is a string");
        // This is a mock method and we are currently returning true for login until our server end is not ready.
        return Observable.just(new SignInData(true, "Login Successful", null));
    }
}
