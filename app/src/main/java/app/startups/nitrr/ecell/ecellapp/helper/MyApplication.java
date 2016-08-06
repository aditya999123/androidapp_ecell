package app.startups.nitrr.ecell.ecellapp.helper;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by meghal on 18/7/16.
 */
public class MyApplication extends Application {

    public static String fcm_token;

    @Override
    public void onCreate() {
        super.onCreate();


        FacebookSdk.sdkInitialize(getApplicationContext());
        fcm_token = FirebaseInstanceId.getInstance().getToken();

    }
}
