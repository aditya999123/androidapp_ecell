package app.startups.nitrr.ecell.ecellapp.Blogs.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.Blogs.BlogsAdapter;
import app.startups.nitrr.ecell.ecellapp.Blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.Blogs.model.RetrofitBlogsProvider;
import app.startups.nitrr.ecell.ecellapp.Blogs.presenter.BlogsPresenter;
import app.startups.nitrr.ecell.ecellapp.Blogs.presenter.BlogsPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.R;

public class Blogs extends AppCompatActivity implements BlogsInterface {

    private static final String TAG = "Blogs";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private BlogsPresenter blogsPresenter;
    private BlogsAdapter blogsAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        Initialize();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestBlogs();
    }

    void requestBlogs() {
        blogsPresenter.requestBlogs();
    }

    void Initialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        blogsPresenter = new BlogsPresenterImpl(this, new RetrofitBlogsProvider());
        blogsAdapter = new BlogsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(blogsAdapter);

    }

    @Override
    public void ShowProgressBar(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void SetData(List<BlogData> blogDataList) {

        blogsAdapter.setData(blogDataList);
        blogsAdapter.notifyDataSetChanged();
        Log.i(TAG, "View SetData Called : " + blogDataList.get(0).getBlogTitle());

    }

}
