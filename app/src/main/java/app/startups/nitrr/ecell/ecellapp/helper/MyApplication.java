package app.startups.nitrr.ecell.ecellapp.helper;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by meghal on 18/7/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
