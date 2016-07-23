package app.startups.nitrr.ecell.ecellapp.welcome.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.home.view.Home;
import app.startups.nitrr.ecell.ecellapp.sign_in.view.SignInActivity;
import app.startups.nitrr.ecell.ecellapp.welcome.model.MockSignInProvider;
import app.startups.nitrr.ecell.ecellapp.welcome.presenter.SignInPresenter;
import app.startups.nitrr.ecell.ecellapp.welcome.presenter.SignInPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, WelcomeView {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.layoutDots)
    LinearLayout dotsLayout;

    @BindView(R.id.login_button)
    LoginButton facebookLoginButton;

    @BindView(R.id.picture)
    ProfilePictureView profilePictureView;

    @BindView(R.id.share)
    Button share;

    @BindView(R.id.details)
    Button details;


    private SignInPresenter signInPresenter;
    private Timer swipeTimer;
    private int NUM_PAGES = 4;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;
    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "Google Sign in";
    private GoogleApiClient mGoogleApiClient;

    private TextView name;
    private SharedPrefs sharedPrefs;

    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ProfilePictureView profile;
    Dialog details_dialog;
    TextView details_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = new SharedPrefs(this);
        sharedPrefs.setFirstTimeLaunch(true);
        if (!sharedPrefs.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_welcome);


        ButterKnife.bind(this);
        signInPresenter = new SignInPresenterImpl(this, new MockSignInProvider());

        FacebookSdk.sdkInitialize(getApplicationContext());


        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        };

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
        // adding bottom dots
        addBottomDots(0);
        // making notification bar transparent
        changeStatusBarColor();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() + 1 == NUM_PAGES) {
                            viewPager.setCurrentItem(-1);
                            //  currentPage = 0;
                        } else {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                        }
                    }
                });
            }
        }, 500, 3000);

        sharedPrefs = new SharedPrefs(this);
        name = (TextView) findViewById(R.id.name);
        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();

        if (signInButton != null) {
            signInButton.setSize(SignInButton.SIZE_WIDE);
            signInButton.setScopes(gso.getScopeArray());
        }

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.


// Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        assert signInButton != null;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        facebookLoginButton.setReadPermissions("public_profile email");
        details_dialog = new Dialog(this);
        details_dialog.setContentView(R.layout.fb_dialog_details);
        details_dialog.setTitle("Details");
        details_txt = (TextView) details_dialog.findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details_dialog.show();
            }
        });

/*
        if (AccessToken.getCurrentAccessToken() != null) {
            RequestData();
            share.setVisibility(View.VISIBLE);
            details.setVisibility(View.VISIBLE);
        }
*/
        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() != null) {
                 /*   share.setVisibility(View.INVISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    profile.setProfileId(null);
              */
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent content = new ShareLinkContent.Builder().build();
                shareDialog.show(content);

            }
        });
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                if (AccessToken.getCurrentAccessToken() != null) {
                    RequestData();
                    share.setVisibility(View.VISIBLE);
                    details.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
            }
        });

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            //    dots[i].setTextColor(Color.WHITE);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            //          dots[currentPage].setTextColor(ContextCompat.getColor(this,R.color.white));
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }


    private void launchHomeScreen() {
        sharedPrefs.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, SignInActivity.class));
        // finish();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

        swipeTimer.cancel();

    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    public void showProgressDialog(boolean show) {

        if (show) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading .\n Please Wait . . .");
            progressDialog.setTitle("Setting up few things - Login");
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {

        }


    }

    @Override
    public void isLoggedIn(boolean login) {

        if (login) {
            Intent in = new Intent(this, Home.class);
            startActivity(in);
            finish();
        } else {
            sharedPrefs.setLogin(false);
            sharedPrefs.setUsername("");
            sharedPrefs.setUserId("");
            sharedPrefs.setEmailId("");
            sharedPrefs.setPhotoUrl("");

            // do nothing
        }

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(WelcomeActivity.this, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Log.i(TAG, "Google plus signin ");
        } else {

            callbackManager.onActivityResult(requestCode, resultCode, data);
            Log.i(TAG, "Facebook sign in");

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            Log.d(TAG, "Success");


            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            signInPresenter.requestSignIn(acct.getId(), acct.getDisplayName(), acct.getEmail(), null, 1);
            sharedPrefs.setEmailId(acct.getEmail());
            sharedPrefs.setUserId(acct.getId());
            sharedPrefs.setUsername(acct.getDisplayName());
            sharedPrefs.setLogin(true);
/*
            Intent intent = new Intent(WelcomeActivity.this, Home.class);
            startActivity(intent);
            finish();
*/
        } else {

            Log.d(TAG, "Failed");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // Do nothing for now !

    }

    public void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {
                        String text = "<b>Name :</b> " + json.getString("name") + "<br><br><b>Email :</b> " + json.getString("email") + "<br><br><b>Profile link :</b> " + json.getString("link");
                        details_txt.setText(Html.fromHtml(text));
                        //          profile.setProfileId(json.getString("id"));

                        signInPresenter.requestSignIn(json.getString("id"), json.getString("name"),
                                json.getString("email"), "https://graph.facebook.com/" + json.getString("id") + "/picture?type=large", 1);

                        sharedPrefs.setEmailId(json.getString("email"));
                        sharedPrefs.setUserId(json.getString("id"));
                        sharedPrefs.setUsername(json.getString("name"));
                        sharedPrefs.setLogin(true);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
