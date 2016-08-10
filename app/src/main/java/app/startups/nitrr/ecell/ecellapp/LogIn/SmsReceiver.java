package app.startups.nitrr.ecell.ecellapp.LogIn;

/**
 * Created by Iket on 8/4/2016.
 */
/*public class SmsReceiver extends BroadcastReceiver {

    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();

        Object[] pdus = (Object[]) data.get("pdus");

        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();


            if (sender.toLowerCase().endsWith("mecell")) {
                String messageBody = smsMessage.getMessageBody();

                //Pass on the text to our listener.
                mListener.messageReceived(messageBody);
            }
        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
*/