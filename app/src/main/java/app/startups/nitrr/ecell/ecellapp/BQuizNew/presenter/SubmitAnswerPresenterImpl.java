package app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.SubmitAnswerProvider;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.view.BQuizView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by meghal on 9/8/16.
 */
public class SubmitAnswerPresenterImpl implements SubmitAnswerPresenter {

    BQuizView bQuizView;
    SubmitAnswerProvider submitAnswerProvider;

    public SubmitAnswerPresenterImpl(BQuizView bQuizView, SubmitAnswerProvider submitAnswerProvider) {
        this.bQuizView = bQuizView;
        this.submitAnswerProvider = submitAnswerProvider;
    }

    @Override
    public void submitAnswer(int questionId, String answer, String accessToken) {

        bQuizView.showProgressbar(true);
        Observable<SubmitAnswerData> submitAnswerDataObservable =
                submitAnswerProvider.submitQuestion(questionId, answer, accessToken);

        submitAnswerDataObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitAnswerData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SubmitAnswerData submitAnswerData) {
                        bQuizView.showProgressbar(false);

                        if (submitAnswerData.isSuccess()) {
                            bQuizView.answerSubmitted(submitAnswerData);
                        } else {
                            bQuizView.showMessage(submitAnswerData.getMessage());
                        }
                    }
                });

    }
}
