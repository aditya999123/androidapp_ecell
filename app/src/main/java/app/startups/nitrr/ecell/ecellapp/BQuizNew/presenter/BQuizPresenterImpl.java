package app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.OnBQuizDataResponse;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.BQuizProvider;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.view.BQuizView;

/**
 * Created by meghal on 6/8/16.
 */
public class BQuizPresenterImpl implements BQuizPresenter {

    BQuizView bQuizView;
    BQuizProvider bQuizProvider;

    public BQuizPresenterImpl(BQuizView bQuizView, BQuizProvider bQuizProvider) {
        this.bQuizView = bQuizView;
        this.bQuizProvider = bQuizProvider;
    }

    @Override
    public void getBquizData(String adminToken) {

        bQuizView.showProgressbar(true);

        bQuizProvider.requestBquizData(adminToken, new OnBQuizDataResponse() {
            @Override
            public void onSuccess(BQuizData bQuizData) {

                if (bQuizData.isSuccess()) {
                    bQuizView.showProgressbar(false);
                    bQuizView.setBquizData(bQuizData);
                }else{
                    bQuizView.showProgressbar(false);
                    bQuizView.showMessage(bQuizData.getMessage());
                    bQuizView.show_Image(bQuizData.getMessage_image_url());
                }
            }

            @Override
            public void onFailure() {
                bQuizView.showProgressbar(false);

            }
        });

    }
}
