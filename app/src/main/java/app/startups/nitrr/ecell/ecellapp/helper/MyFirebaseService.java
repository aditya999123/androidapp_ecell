package app.startups.nitrr.ecell.ecellapp.helper;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by meghal on 13/8/16.
 */
public class MyFirebaseService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Created");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d("Hello",remoteMessage.toString());
        super.onMessageReceived(remoteMessage);

    }
}
