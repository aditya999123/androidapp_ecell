package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.RetrofitBquizProvider;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.RetrofitSubmitAnswerProvider;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.BQuizData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.model.data.SubmitAnswerData;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.BQuizPresenter;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.BQuizPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.SubmitAnswerPresenter;
import app.startups.nitrr.ecell.ecellapp.BQuizNew.presenter.SubmitAnswerPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BQuizActivity extends AppCompatActivity implements BQuizView {

    @BindView(R.id.question_text)
    TextView question_text;

    @BindView(R.id.question_image)
    ImageView question_image;

    @BindView(R.id.points)
    TextView points;

    @BindView(R.id.input_ans)
    EditText input_ans;

    @BindView(R.id.timer)
    TextView timer;

    @BindView(R.id.submit_button)
    Button submit_button;

    @BindView(R.id.rb1)
    RadioButton rb1;

    @BindView(R.id.rb2)
    RadioButton rb2;

    @BindView(R.id.rb3)
    RadioButton rb3;

    @BindView(R.id.rb4)
    RadioButton rb4;


    @BindView(R.id.progressBar)
    ProgressBar progressbar;

    @BindView(R.id.radio_group)
    RadioGroup radio_group;

    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.message_image)
    ImageView messageImage;

    @BindView(R.id.message_layout)
    LinearLayout messageLayout;

    @BindView(R.id.question_layout)
    LinearLayout questionLayout;

    int time;
    private BQuizPresenter bQuizPresenter;
    private ImageLoader imageLoader;
    private SubmitAnswerPresenter submitAnswerPresenter;
    private int questionId;
    private int data_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquiz_new);
        ButterKnife.bind(this);
        bQuizPresenter = new BQuizPresenterImpl(this, new RetrofitBquizProvider());
        bQuizPresenter.getBquizData("123412341234");

        submitAnswerPresenter = new SubmitAnswerPresenterImpl(this, new RetrofitSubmitAnswerProvider());
        imageLoader = new GlideImageLoader(this);

        radio_group = new RadioGroup(this);

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPrefs sharedPrefs=new SharedPrefs(BQuizActivity.this);
                submitAnswerPresenter.submitAnswer(questionId, getAnswer(),sharedPrefs.getAccessToken());
            }
        });
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(BQuizActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show)
            progressbar.setVisibility(View.VISIBLE);
        else
            progressbar.setVisibility(View.GONE);
    }

    @Override
    public void setBquizData(BQuizData bquizData) {

        questionId = bquizData.getQuestion_data().getQuestion_id();
        data_type = bquizData.getData_type();

        messageLayout.setVisibility(View.GONE);
        questionLayout.setVisibility(View.VISIBLE);
        switch (bquizData.getData_type()) {


            case 1:
                question_image.setVisibility(View.GONE);
                radio_group.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                break;
            case 2:
                question_image.setVisibility(View.GONE);
                input_ans.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                rb1.setText(bquizData.getQuestion_data().getOption1());
                rb2.setText(bquizData.getQuestion_data().getOption2());
                rb3.setText(bquizData.getQuestion_data().getOption3());
                rb4.setText(bquizData.getQuestion_data().getOption4());
                question_image.setVisibility(View.VISIBLE);
                imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);

                //add images in the activity
                break;
            case 3:
                radio_group.setVisibility(View.GONE);
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                break;
            case 4:
                question_text.setText(bquizData.getQuestion_data().getQuestion());
                rb1.setText(bquizData.getQuestion_data().getOption1());
                rb2.setText(bquizData.getQuestion_data().getOption2());
                rb3.setText(bquizData.getQuestion_data().getOption3());
                rb4.setText(bquizData.getQuestion_data().getOption4());
                imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                question_image.setVisibility(View.VISIBLE);
                time = bquizData.getQuestion_data().getQuestion_duration();
                countDown(time);
                //add images in the activity
                break;
            default:


        }
    }

    @Override
    public void answerSubmitted(SubmitAnswerData submitAnswerData) {
        if (submitAnswerData.isSuccess()) {

            questionLayout.setVisibility(View.GONE);
            messageLayout.setVisibility(View.VISIBLE);

            message.setText(submitAnswerData.getMessage());
            imageLoader.loadImage(submitAnswerData.getMessage_image(), messageImage);

        }
    }

    public void countDown(int time) {
        time *= 1000;
        new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 60000 + " : " + (millisUntilFinished / 1000) % 60 + "secs left");
            }

            public void onFinish() {


            }
        }.start();

    }

    private String getAnswer() {

        String answer = null;
        switch (data_type) {

            case 1:

                answer = input_ans.getText().toString();
                break;
            case 2:


                RadioButton radioButton = (RadioButton) findViewById(radio_group.getCheckedRadioButtonId());
                answer = radioButton.getText().toString();
                break;
            case 3:

                answer = input_ans.getText().toString();
                break;
            case 4:

                RadioButton radioButton1 = (RadioButton) findViewById(radio_group.getCheckedRadioButtonId());
                answer = radioButton1.getText().toString();
                break;
        }
        return answer;
    }


}
