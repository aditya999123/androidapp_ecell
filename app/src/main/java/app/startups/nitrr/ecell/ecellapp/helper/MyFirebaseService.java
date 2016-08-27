package app.startups.nitrr.ecell.ecellapp.helper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.view.BQuizActivity;
import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.about_us.view.AboutUsPage;
import app.startups.nitrr.ecell.ecellapp.blogs.view.Blogs;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.Contacts;
import app.startups.nitrr.ecell.ecellapp.events.view.ListOfEvents;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.Spons;


public class MyFirebaseService extends FirebaseMessagingService {
    int i;

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getData());
        String jsonStr=remoteMessage.getData().toString();
        try {
            JSONObject jsonRootObject = new JSONObject(jsonStr);
//             i=Integer.parseInt(jsonRootObject.optString("intent_id"));
            i=Integer.parseInt(remoteMessage.getData().get("intent_id"));

        }
        catch (JSONException e) {
            e.printStackTrace();
        }


        sendNotification(""+remoteMessage.getData().get("body"),""+
                remoteMessage.getData().get("title"));


        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    private void sendNotification(String messageBody,String title) {
        Intent intent;
       if(i==1)
       {
           intent = new Intent(this, Home.class);
       }
        else  if(i==2) {
             intent = new Intent(this, Blogs.class);
        }
        else if(i==3) {
            intent = new Intent(this, ListOfEvents.class);
        }
        else if(i==4) {
            intent = new Intent(this, BQuizActivity.class);
        }
        else if(i==5) {
            intent = new Intent(this, Spons.class);
        }
        else if(i==6) {
            intent = new Intent(this, Contacts.class);
        }
        else if(i==7) {
            intent = new Intent(this, AboutUsPage.class);
        }
        else {
             intent = new Intent(this,Home.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ecell_logo)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
//        notificationBuilder.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sound));

        notificationBuilder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}