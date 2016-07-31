package app.startups.nitrr.ecell.ecellapp.blogs.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.blogs.BlogsAdapter;
import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.blogs.model.RetrofitBlogsProvider;
import app.startups.nitrr.ecell.ecellapp.blogs.presenter.BlogsPresenter;
import app.startups.nitrr.ecell.ecellapp.blogs.presenter.BlogsPresenterImpl;

public class Blogs extends AppCompatActivity implements BlogsInterface {

    private static final String TAG = "Events";
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
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
        toolbar.setTitle("Blogs");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
