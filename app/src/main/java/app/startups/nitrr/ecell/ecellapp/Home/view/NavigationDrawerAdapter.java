package app.startups.nitrr.ecell.ecellapp.home.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.custom_views.Shape;
import app.startups.nitrr.ecell.ecellapp.helper.SharedPrefs;
import app.startups.nitrr.ecell.ecellapp.home.model.NavDrawerItem;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Meghal on 5/28/2016.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_HEADER = 1;
    private static final int VIEW_BODY = 2;
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private List<Integer> iconList = new ArrayList<>();
    private SharedPrefs sharedPrefs;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        sharedPrefs = new SharedPrefs(context);
        getMockList();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            return VIEW_HEADER;
        } else {

            return VIEW_BODY;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case VIEW_HEADER:
                View view = inflater.inflate(R.layout.nav_drawer_header, parent, false);
                return new HeaderViewHolder(view);
            case VIEW_BODY:
                View view1 = inflater.inflate(R.layout.nav_drawer_row, parent, false);

                return new TitleViewHolder(view1);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        NavDrawerItem current = data.get(position);

        switch (holder.getItemViewType()) {

            case VIEW_HEADER:
                Random random = new Random();
                int red = random.nextInt(255);
                int blue = random.nextInt(255);
                int green = 100;
                String username=sharedPrefs.getUsername();
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.circle.setShapeColor(Color.rgb(red, blue, green));
                headerViewHolder.nameFirstWord.setText(username.substring(0,1));

                headerViewHolder.name.setText(username);
                headerViewHolder.name.append("\n");
                headerViewHolder.name.append(sharedPrefs.getEmail());

                break;
            case VIEW_BODY:
                TitleViewHolder viewHolder = (TitleViewHolder) holder;
                viewHolder.title.setText(current.getTitle());
                viewHolder.icon.setImageResource(iconList.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;

        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.circle)
        Shape circle;

        @BindView(R.id.nameFirstWord)
        TextView nameFirstWord;

        @BindView(R.id.name)
        TextView name;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void getMockList() {
        iconList.add(R.drawable.ic_account_box_black_18dp);
        iconList.add(R.drawable.ic_home_black_24dp);
        iconList.add(R.drawable.ic_account_box_black_18dp);
        iconList.add(0);
        iconList.add(R.drawable.ic_event_note_black_18dp);
        iconList.add(R.drawable.ic_monochrome_photos_black_18dp);
        iconList.add(0);
        iconList.add(R.drawable.ic_contact_mail_black_18dp);
        iconList.add(R.drawable.ic_group_about_us_black_18dp);
        iconList.add(R.drawable.ic_all_logout_black_18dp);

    }
}
