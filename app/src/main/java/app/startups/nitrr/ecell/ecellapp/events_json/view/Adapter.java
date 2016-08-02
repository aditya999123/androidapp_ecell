package app.startups.nitrr.ecell.ecellapp.events_json.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by Iket on 7/29/2016.
 */
public class Adapter  extends RecyclerView.Adapter<Adapter.MyHolder> {
    private ArrayList<EventsData> eventsData;
    private Context context;
    private LayoutInflater inflater;
    List<EventsData> data= Collections.emptyList();
    EventsData current;
    int currentPos=0;

    public Adapter(Context context, List<EventsData> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public android.view.View View;
        public ViewHolder(View v ) {
            super(v);
            View = v;
        }
    }



    @Override
    public Adapter.MyHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = inflater.from(parent.getContext())
                .inflate(R.layout.activity_events_row, parent, false);


        MyHolder vh = new MyHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        EventsData current=data.get(position);
        holder.event_name.setText(current.eventName);
        holder.date_time.setText(current.date);
      holder.venue.setText(current.venue);
        holder.description.setText(current.description);
    }



    @Override
    public int getItemCount() {
        return 0;
    }


    protected class MyHolder extends RecyclerView.ViewHolder {

        private TextView event_name, date_time, venue, description;

        // private ImageView imageView;


        private MyHolder(View itemView) {
            super(itemView);
            event_name = (TextView) itemView.findViewById(R.id.event_name);
            date_time = (TextView) itemView.findViewById(R.id.date_time);
            venue = (TextView) itemView.findViewById(R.id.venue);
            description = (TextView) itemView.findViewById(R.id.description);
            //  imageView = (ImageView) itemView.findViewById(R.id.imgview1);

        }

    }


}
