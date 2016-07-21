package app.startups.nitrr.ecell.ecellapp.sign_in.model;

import android.os.Handler;

/**
 * Created by Meghal on 6/24/2016.
 */
public class RetrofitSignInProvider implements SignInProvider {


    @Override
    public void requestSignIn(String userId) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Say login is successful providing access token
            }
        }, 1000);
    }
}

