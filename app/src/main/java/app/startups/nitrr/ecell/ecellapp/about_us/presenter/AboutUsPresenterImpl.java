package app.startups.nitrr.ecell.ecellapp.about_us.presenter;

import android.util.Log;

import app.startups.nitrr.ecell.ecellapp.about_us.model.AboutUsProvider;
import app.startups.nitrr.ecell.ecellapp.about_us.view.AboutUsData;
import app.startups.nitrr.ecell.ecellapp.about_us.view.AboutUsInterface;
import app.startups.nitrr.ecell.ecellapp.about_us.view.OnAboutusReceived;

/**
 * Created by Iket on 8/20/2016.
 */
public class AboutUsPresenterImpl implements AboutUsPresenter {
    private AboutUsProvider aboutUsProvider;
    private AboutUsInterface aboutUsInterface;

    public AboutUsPresenterImpl(AboutUsInterface aboutUsInterface,AboutUsProvider aboutUsProvider ) {
        this.aboutUsProvider = aboutUsProvider;
        this.aboutUsInterface = aboutUsInterface;
    }

    @Override
    public void requestData() {
        Log.d("Response","presenter 1");

        aboutUsInterface.showLoading(true);
        aboutUsProvider.requestData(new OnAboutusReceived() {
            @Override
            public void onSuccess(AboutUsData aboutUsData) {
                aboutUsInterface.showLoading(false);
                aboutUsInterface.setData(aboutUsData);
            }

            @Override
            public void onFailure() {
                aboutUsInterface.showLoading(false);
            }
        });

    }
}
