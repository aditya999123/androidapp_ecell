package app.startups.nitrr.ecell.ecellapp.BQuizNew.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BQuizActivity extends AppCompatActivity implements BQuizView {

    @BindView(R.id.question_text)
    TextView question_text;

    @BindView(R.id.question_image)
    ImageView question_image;

    @BindView(R.id.input_ans)
    EditText input_ans;

    @BindView(R.id.submit_button)
    Button submit_button;

    @BindView(R.id.rb1)
    RadioButton rb1;

    @BindView(R.id.points)
    TextView points;

    @BindView(R.id.rb2)
    RadioButton rb2;

    @BindView(R.id.rb3)
    RadioButton rb3;

    @BindView(R.id.rb4)
    RadioButton rb4;

    @BindView(R.id.toolbar_bquiz)
    Toolbar toolbar;

    @BindView(R.id.toolbar1)
    Toolbar toolbar1;

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

    int time,i;
    private BQuizPresenter bQuizPresenter;
    private ImageLoader imageLoader;
    private SubmitAnswerPresenter submitAnswerPresenter;
    private int questionId;
    private int data_type;
    private CountDownTimer countDownTimer;
    SharedPrefs sharedPrefs;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bquiz__intro);
        ButterKnife.bind(this);
//            startService(new Intent(MyFirebaseService.class.getName()));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        toolbar.setTitle("B Quiz");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
            toolbar1.setTitle("B Quiz");
            toolbar1.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });



            bQuizPresenter = new BQuizPresenterImpl(this, new RetrofitBquizProvider());
            sharedPrefs = new SharedPrefs(this);
        bQuizPresenter.getBquizData(sharedPrefs.getAccessToken());

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

                SharedPrefs sharedPrefs = new SharedPrefs(BQuizActivity.this);
                submitAnswerPresenter.submitAnswer(questionId, getAnswer(), sharedPrefs.getAccessToken());
                i=0;
            }
        });
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        super.onBackPressed();
        if(i==1)
        {
            submitAnswerPresenter.submitAnswer(questionId, getAnswer(), sharedPrefs.getAccessToken());
            i=0;
        }


    }

    @Override
    protected void onStop()
    {
        super.onStop();
        countDownTimer.cancel();
        if(i==1)
        {
            submitAnswerPresenter.submitAnswer(questionId, getAnswer(), sharedPrefs.getAccessToken());
            i=0;
        }
    }
    @Override
    public void show_Image(String s)
    {
        question_image.setVisibility(View.VISIBLE);
        imageLoader.loadImage(s,question_image);
    }
    @Override
    public void showMessage(String message) {
        toolbar.setTitle("B Quiz");
        question_text.setVisibility(View.VISIBLE);
        question_text.setText(message);
//        Toast.makeText(BQuizActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show)
            progressbar.setVisibility(View.VISIBLE);
        else
            progressbar.setVisibility(View.GONE);
    }

    @Override
    public void setBquizData(final BQuizData bquizData) {


            questionId = bquizData.getQuestion_data().getQuestion_id();
            data_type = bquizData.getData_type();
            final Dialog dialog = new Dialog(BQuizActivity.this);
            dialog.setContentView(R.layout.activity_rules__dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            TextView rules5 = (TextView) dialog.findViewById(R.id.rules5);
            rules5.setText(bquizData.getRules().toString());

            dialog.setTitle("Rules");


            dialog.show();
            messageLayout.setVisibility(View.GONE);
            questionLayout.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);
        points.setText(" Points : "+bquizData.getQuestion_data().getPoints());
            switch (bquizData.getData_type()) {


                case 1:
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            i=1;
                            submit_button.setVisibility(View.VISIBLE);
                            question_image.setVisibility(View.GONE);
                            radio_group.setVisibility(View.GONE);
                            rb1.setVisibility(View.GONE);
                            rb2.setVisibility(View.GONE);
                            rb3.setVisibility(View.GONE);
                            rb4.setVisibility(View.GONE);
                            question_text.setVisibility(View.VISIBLE);
                            input_ans.setVisibility(View.VISIBLE);
                            question_text.setText(bquizData.getQuestion_data().getQuestion());
                            time = bquizData.getQuestion_data().getQuestion_duration();
                            countDown(time);
                            dialog.dismiss();



                        }
                    });

                    break;
                case 2:
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submit_button.setVisibility(View.VISIBLE);
                            question_image.setVisibility(View.GONE);
                            input_ans.setVisibility(View.GONE);
                            radio_group.setVisibility(View.VISIBLE);
                            question_text.setText(bquizData.getQuestion_data().getQuestion());
                            rb1.setText(bquizData.getQuestion_data().getOption1());
                            rb2.setText(bquizData.getQuestion_data().getOption2());
                            rb3.setText(bquizData.getQuestion_data().getOption3());
                            rb4.setText(bquizData.getQuestion_data().getOption4());
                            question_image.setVisibility(View.VISIBLE);
                            imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                            time = bquizData.getQuestion_data().getQuestion_duration();
                            countDown(time);
                            dialog.dismiss();
                            i=1;


                        }
                    });
                    break;
                case 3:
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submit_button.setVisibility(View.VISIBLE);
                            radio_group.setVisibility(View.GONE);
                            question_text.setVisibility(View.VISIBLE);
                            question_text.setText(bquizData.getQuestion_data().getQuestion());
                            input_ans.setVisibility(View.VISIBLE);
                            question_image.setVisibility(View.VISIBLE);
                            time = bquizData.getQuestion_data().getQuestion_duration();
                            imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                            countDown(time);
                            dialog.dismiss();
                            i=1;


                        }
                    });
                    break;
                case 4:
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submit_button.setVisibility(View.VISIBLE);
                            radio_group.setVisibility(View.VISIBLE);
                            question_text.setText(bquizData.getQuestion_data().getQuestion());
                            rb1.setText(bquizData.getQuestion_data().getOption1());
                            rb2.setText(bquizData.getQuestion_data().getOption2());
                            rb3.setText(bquizData.getQuestion_data().getOption3());
                            rb4.setText(bquizData.getQuestion_data().getOption4());
                            imageLoader.loadImage(bquizData.getQuestion_data().getImage_url(), question_image);
                            question_image.setVisibility(View.VISIBLE);
                            time = bquizData.getQuestion_data().getQuestion_duration();
                            countDown(time);
                            dialog.dismiss();
                            i=1;
                        }
                    });

                    break;
                default:
            }
        }



    @Override
    public void answerSubmitted(SubmitAnswerData submitAnswerData) {
        if (submitAnswerData.isSuccess()) {
            toolbar.setVisibility(View.VISIBLE);
            toolbar.setTitle("B Quiz");
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            questionLayout.setVisibility(View.GONE);
            LinearLayout answer_layout=(LinearLayout)findViewById(R.id.answer_layout);
            answer_layout.setVisibility(View.VISIBLE);
            messageLayout.setVisibility(View.VISIBLE);
            message.setText(submitAnswerData.getMessage_display());
            imageLoader.loadImage(submitAnswerData.getMessage_image(), messageImage);

        }
    }

    public void countDown(int time) {
        time *= 1000;
           countDownTimer=new CountDownTimer(time, 1000) {
            public void onTick(long millisUntilFinished) {

                toolbar.setTitle("Remaining Time "+millisUntilFinished / 60000 + " : " + (millisUntilFinished / 1000) % 60);

            }

            public void onFinish() {
                submitAnswerPresenter.submitAnswer(questionId, getAnswer(), sharedPrefs.getAccessToken());
                i=0;
                countDownTimer.cancel();
                toolbar.setTitle("B Quiz");
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
                toolbar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                if(i==0)
                {
                    Intent in=new Intent(BQuizActivity.this,Home.class);
                    startActivity(in);
                }

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
                Log.d("BQuizActivity",""+radio_group.getCheckedRadioButtonId());
                answer = radioButton != null ? radioButton.getText().toString() : null;
                break;
            case 3:
                answer = input_ans.getText().toString();
                break;
            case 4:

                RadioButton radioButton1 = (RadioButton) findViewById(radio_group.getCheckedRadioButtonId());
                Log.d("BQuizActivity",""+radioButton1);

                answer = radioButton1 != null ? radioButton1.getText().toString() : null;
                break;
        }
        return answer;
    }

}
