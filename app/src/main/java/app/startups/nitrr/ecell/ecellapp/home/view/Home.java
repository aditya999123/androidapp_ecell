package app.startups.nitrr.ecell.ecellapp.home.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.facebook.login.LoginManager;

import app.startups.nitrr.ecell.ecellapp.BQuizNew.view.BQuizActivity;
import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.about_us.view.AboutUsFragment;
import app.startups.nitrr.ecell.ecellapp.blogs.view.BlogFragment;
import app.startups.nitrr.ecell.ecellapp.contact_us.view.ContactsFragment;
import app.startups.nitrr.ecell.ecellapp.events.view.EventsFragment;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.sponsers.view.SponsFragment;
import app.startups.nitrr.ecell.ecellapp.welcome.view.WelcomeActivity;


public class Home extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static final String TAG = "Home";
    Toolbar toolbar;
    private FragmentDrawer drawerFragment;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPrefs = new SharedPrefs(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        //    ButterKnife.bind(this);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        //  getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
        setFragment(new HomeFragment(),"Home");
    }
    @Override
    public void onBackPressed() {
        Fragment fragment=getSupportFragmentManager().findFragmentByTag("Home");
//        int i=getSupportFragmentManager().getBackStackEntryCount();
//
        Log.d("check",""+fragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(fragment==null)
        {
            setFragment(new HomeFragment(),"Home");
        }
        else{
            // super.onBackPressed();
            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("Exit ?");
            ad.setMessage("Do you really want to exit ?");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                }
            });
            ad.show();
        }

    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        switch (position) {
            case 1:
                setFragment(new HomeFragment(),"Home");
                break;
            case 2:
                setFragment(new BlogFragment(),"Blogs");
                break;
            case 3:
                setFragment(new EventsFragment() , "Events");
                break;

            case 4:
                Intent bquiz = new Intent(Home.this, BQuizActivity.class);
                startActivity(bquiz);
                break;
            case 5:
                setFragment(new SponsFragment(),"Sponsors");
                break;

            case 6:
                setFragment(new ContactsFragment(),"Contacts");
                break;
            case 7:
                setFragment(new AboutUsFragment(),"About Us");
                break;
            case 8:
                if (sharedPrefs.getLoginType() == 1) {
                    LoginManager.getInstance().logOut();
                }
                sharedPrefs.setLogin(false);
                sharedPrefs.setUsername("");
                sharedPrefs.setEmailId("");
                sharedPrefs.setPhotoUrl("");
                sharedPrefs.setUserId("");

                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }


    public void setFragment(Fragment fragment, String title) {

        if(title.equals("Home") && fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body,fragment,title);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);

        }
        else if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }


    }

    public void addFragment(Fragment fragment, String title) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }

    }



}
