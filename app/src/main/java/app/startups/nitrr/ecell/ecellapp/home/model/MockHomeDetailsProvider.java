package app.startups.nitrr.ecell.ecellapp.home.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.home.OnHomeDataRequest;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeData;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeDetails;

/**
 * Created by Meghal on 6/20/2016.
 */
public class MockHomeDetailsProvider implements HomeDetailsProvider {


    @Override
    public void requestHomeData(String userId, OnHomeDataRequest onHomeDataRequest) {


        List<HomeDetails> homeDetailsList = new ArrayList<>();
        HomeDetails welcome = new HomeDetails(4, null, "E-cell team welcomes you."
                , "We hope you'r doing well", null, null, null, null);
        homeDetailsList.add(welcome);
        HomeDetails blog = new HomeDetails(2, null, "Finance and technology – a tango over the decades"
                , "The month is beginning and you’re eagerly awaiting that ping, which announces " +
                "your salary has been credited. By the fifth of the month, you’re busy in a meeting" +
                " and a small ping notifies you that the EMI amount has been debited from your savings " +
                "account and has been credited into your loan or credit card account.\n" +
                "Without even realising it, the way we deal with money and transactions has" +
                " drastically changed. Bank transfers, credit card payments, loan clearances" +
                " and bill payments, today, everything has gone online. Technology has been disrupting" +
                " different aspects of the financial landscape long before we realised it."
                , "http://d152j5tfobgaot.cloudfront.net/wp-content/uploads/2016/06/FINTECH.png", null, "25th June 2016"
                , "SINDHU KASHYAP");

        HomeDetails post = new HomeDetails(3, null, "Finance and technology – a tango over the decades"
                , "The month is beginning and you’re eagerly awaiting that ping, which announces " +
                "your salary has been credited. By the fifth of the month, you’re busy in a meeting" +
                " and a small ping notifies you that the EMI amount has been debited from your savings " +
                "account and has been credited into your loan or credit card account.\n" +
                "Without even realising it, the way we deal with money and transactions has" +
                " drastically changed. Bank transfers, credit card payments, loan clearances" +
                " and bill payments, today, everything has gone online. Technology has been disrupting" +
                " different aspects of the financial landscape long before we realised it."
                , "http://d152j5tfobgaot.cloudfront.net/wp-content/uploads/2016/06/FINTECH.png", null, "25th June 2016"
                , "SINDHU KASHYAP");
        HomeDetails event = new HomeDetails(4, null, "Finance and technology – a tango over the decades"
                , "The month is beginning and you’re eagerly awaiting that ping, which announces " +
                "your salary has been credited. By the fifth of the month, you’re busy in a meeting" +
                " and a small ping notifies you that the EMI amount has been debited from your savings " +
                "account and has been credited into your loan or credit card account.\n" +
                "Without even realising it, the way we deal with money and transactions has" +
                " drastically changed. Bank transfers, credit card payments, loan clearances" +
                " and bill payments, today, everything has gone online. Technology has been disrupting" +
                " different aspects of the financial landscape long before we realised it."
                , "http://d152j5tfobgaot.cloudfront.net/wp-content/uploads/2016/06/FINTECH.png", null, "25th June 2016"
                , "SINDHU KASHYAP");
        homeDetailsList.add(blog);
        homeDetailsList.add(event);
//        homeDetailsList.add(post);

        HomeData homeData = new HomeData(true, "Successful", homeDetailsList);

        onHomeDataRequest.onSuccess(homeData);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        }, 1000);

    }


}
