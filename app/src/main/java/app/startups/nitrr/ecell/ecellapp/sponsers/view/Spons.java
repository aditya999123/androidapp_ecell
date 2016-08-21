package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.sponsers.model.RetrofitSponsProvider;
import app.startups.nitrr.ecell.ecellapp.sponsers.presenter.SponsPresenter;
import app.startups.nitrr.ecell.ecellapp.sponsers.presenter.SponsPresenterImpl;

public class Spons extends AppCompatActivity implements SponsInterface{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SponsPresenter sponsPresenter;
    private SponsAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spons);

        toolbar = (Toolbar) findViewById(R.id.toolbar_spons);
        toolbar.setTitle("Sponsers");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_spons);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_spons);
        sponsPresenter=new SponsPresenterImpl(this,new RetrofitSponsProvider());

        adapter = new SponsAdapter(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        sponsPresenter.requestSpons();

    }

    @Override
    public void showLoading(boolean show) {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
            progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(Spons.this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(List<SponsData> sponsDataList) {
        adapter.setData(sponsDataList);
        adapter.notifyDataSetChanged();


    }
}
