package app.startups.nitrr.ecell.ecellapp.home.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.blogs.view.Blogs;
import app.startups.nitrr.ecell.ecellapp.home.model.MockHomeDetailsProvider;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeDetails;
import app.startups.nitrr.ecell.ecellapp.home.presenter.HomePresenter;
import app.startups.nitrr.ecell.ecellapp.home.presenter.HomePresenterImpl;


public class Home extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, HomeInterface {

    //    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private FragmentDrawer drawerFragment;
    private HomeDetailsAdapter homeDetailsAdapter;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);

        //    ButterKnife.bind(this);
        initialize();
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);


        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
        homePresenter.requestHomeData("1");
    }

    private void initialize() {
        homePresenter = new HomePresenterImpl(this, new MockHomeDetailsProvider());
        homeDetailsAdapter = new HomeDetailsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeDetailsAdapter);

    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        String title = getString(R.string.app_name);
        switch (position) {
            case 1:
                // Do nothing .
                break;
            case 2:
    /*            Intent profile = new Intent(Home.this, EditDetails.class);
                startActivity(profile);
    */
                break;
            case 3:
                Intent blogs = new Intent(Home.this, Blogs.class);
                startActivity(blogs);

                break;
            case 4:
           /*     Intent myOrders = new Intent(Home.this, MyOrders.class);
                startActivity(myOrders);
*/
                break;

            case 5:
  /*              PrefManager prefManager = new PrefManager(this);
                prefManager.logout();
                Intent in=new Intent(Home.this, WelcomeActivity.class);
                startActivity(in);
                finish();
  */
                break;
            default:
                break;
        }
    }

    @Override
    public void showProgressBar(boolean show) {

        if (show) {
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setData(List<HomeDetails> homeDetailsList) {

        homeDetailsAdapter.setData(homeDetailsList);

    }

    protected void showSubCategory(int subCategoryId) {
        //    Intent in = new Intent(Home.this, SubCategoryActivity.class);
        //    in.putExtra(Keys.KEY_CATEGORY_ID,subCategoryId);
        //    startActivity(in);

    }
}
