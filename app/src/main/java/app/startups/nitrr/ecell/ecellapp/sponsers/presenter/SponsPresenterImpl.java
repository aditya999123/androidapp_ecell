package app.startups.nitrr.ecell.ecellapp.sponsers.presenter;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.sponsers.model.SponsProvider;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.OnSponsReceived;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.SponsData;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.SponsInterface;

/**
 * Created by Iket on 8/21/2016.
 */
public class SponsPresenterImpl implements SponsPresenter {
    private SponsInterface sponsInterface;
    private SponsProvider sponsProvider;

    public SponsPresenterImpl(SponsInterface sponsInterface, SponsProvider sponsProvider) {
        this.sponsInterface = sponsInterface;
        this.sponsProvider = sponsProvider;
    }

    @Override
    public void requestSpons() {
        sponsInterface.showLoading(true);
        sponsProvider.reqSpons(new OnSponsReceived() {
            @Override
            public void onFailure() {
                sponsInterface.showLoading(false);
                sponsInterface.showMessage("Try again in some time");
            }

            @Override
            public void onSuccess(List<SponsData> sponsDataList) {
                sponsInterface.setData(sponsDataList);
                sponsInterface.showLoading(false);
            }
        });
    }
}
