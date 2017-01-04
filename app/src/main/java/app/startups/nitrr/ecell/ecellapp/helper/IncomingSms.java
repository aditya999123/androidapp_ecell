package app.startups.nitrr.ecell.ecellapp.helper;

/**
 * Created by iket on 21/12/16.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import app.startups.nitrr.ecell.ecellapp.send_otp.view.SendOtp;

public class IncomingSms extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d("Try","Start");

        final Bundle bundle = intent.getExtras();
        try {
            Log.d("Try","1st");
            if (bundle != null)
            {
                Log.d("Try","2nd");
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj .length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])                                                                                                    pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber ;
                    String message = currentMessage .getDisplayMessageBody();
                    int len=message.length();
                    message=message.substring(len-6,len);

                    Log.d("Try",""+message);

                    try
                    {
                        if (senderNum.endsWith("VegWrd"))
                        {
                            Log.d("Try","Success");
                            SendOtp Sms = new SendOtp();
                            Sms.recivedSms(message);
                        }
                    }
                    catch(Exception e){
                        Log.d("Try","Fail 1");
                        e.printStackTrace();
                    }

                }
            }

        } catch (Exception e)
        {
            Log.d("Try","Fail 1.1");
            e.printStackTrace();


        }
    }

}

