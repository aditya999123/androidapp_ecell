package app.startups.nitrr.ecell.ecellapp.events.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.events.model.RetrofitEventsProvider;
import app.startups.nitrr.ecell.ecellapp.events.presenter.EventPresenterImpl;
import app.startups.nitrr.ecell.ecellapp.events.presenter.EventsPresenter;

public class ListOfEvents extends AppCompatActivity implements EventsInterface{
    private static final String TAG = "Events";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EventsPresenter eventsPresenter;
    private Adapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_events);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        eventsPresenter = new EventPresenterImpl(this, new RetrofitEventsProvider());
        adapter = new Adapter(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        Log.d("Response","1st");
       recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

       // setSupportActionBar(toolbar);
       // toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp));
      // toolbar.setTitle("Events");

       /* toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        */

        requestEvents();



    }
    void requestEvents() {
        Log.d("Response","2");
        eventsPresenter.requestEvents();
        Log.d("Response","3");
    }

    @Override
    public void ShowProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            Log.d("Response","progress false");
        }

    }

    @Override
    public void SetData(List<EventsData> eventDataList) {
        Log.d("Response", "data 1");
        adapter.setData(eventDataList);
        Log.d("Response", "data 2");
        adapter.notifyDataSetChanged();
        Log.d("Response", "data 3");
        for (int i = 0; i <= 3; i++) {
            Log.i("Response", "View SetData Called : " + eventDataList.get(i).getEventName());
            Log.i("Response", "View SetData Called : " + eventDataList.get(i).getDate());
            Log.i("Response", "View SetData Called : " + eventDataList.get(i).getDescription());

        }
    }
}
