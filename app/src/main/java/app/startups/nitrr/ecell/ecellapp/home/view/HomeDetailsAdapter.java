package app.startups.nitrr.ecell.ecellapp.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.home.model.data.HomeDetails;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Meghal on 6/19/2016.
 */
public class HomeDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_SLIDER = 0;
    private static final int VIEW_EVENTS = 1;
    private static final int VIEW_BLOGS = 2;
    private static final int VIEW_POST = 3;
    private static final int VIEW_WELCOME = 4;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<HomeDetails> homeDetailsList = new ArrayList<>();

    HomeDetailsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {

        switch (homeDetailsList.get(position).getType()) {
            case VIEW_WELCOME:
                return VIEW_WELCOME;
            case VIEW_SLIDER:
                return VIEW_SLIDER;
            case VIEW_BLOGS:
                return VIEW_BLOGS;
            case VIEW_EVENTS:
                return VIEW_EVENTS;
            case VIEW_POST:
                return VIEW_POST;
        }

        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_BLOGS:
                View blogs = layoutInflater.inflate(R.layout.home_blogs_item, parent, false);
                return new BlogsViewHolder(blogs);
            case VIEW_EVENTS:
                View events = layoutInflater.inflate(R.layout.home_events_item, parent, false);
                return new EventsViewHolder(events);
            case VIEW_WELCOME:
                View welcome = layoutInflater.inflate(R.layout.home_welcome_item, parent, false);
                return new WelcomeViewHolder(welcome);
            case VIEW_SLIDER:
                View slider = layoutInflater.inflate(R.layout.home_image_slider_item, parent, false);
                return new SliderViewHolder(slider);
            case VIEW_POST:
                View post = layoutInflater.inflate(R.layout.home_post_item, parent, false);
                return new PostsViewHolder(post);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeDetails homeDetails = homeDetailsList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_WELCOME:
                WelcomeViewHolder welcomeViewHolder = (WelcomeViewHolder) holder;
                welcomeViewHolder.name.setText(homeDetails.getTitle());
                welcomeViewHolder.quote.setText(homeDetails.getDiscription());

                break;
            case VIEW_BLOGS:
                BlogsViewHolder blogsViewHolder = (BlogsViewHolder) holder;
                blogsViewHolder.title.setText(homeDetails.getTitle());
                blogsViewHolder.body.setText(homeDetails.getDiscription());
                blogsViewHolder.date.setText(homeDetails.getTimestamp());
                blogsViewHolder.owner.setText(homeDetails.getOwner());
                Glide.with(context).load(homeDetails.getImage()).thumbnail(0.1f).into(blogsViewHolder.image);

                break;

        }

    }


    @Override
    public int getItemCount() {
        return homeDetailsList.size();
    }

    public void setData(List<HomeDetails> homeDetailsList) {
        this.homeDetailsList = homeDetailsList;
    }

    public class WelcomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameWelcome)
        TextView name;

        @BindView(R.id.quote)
        TextView quote;

        public WelcomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {

        public EventsViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class BlogsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.blogBody)
        TextView body;

        @BindView(R.id.blogDate)
        TextView date;

        @BindView(R.id.blogImage)
        ImageView image;

        @BindView(R.id.blogOwner)
        TextView owner;

        @BindView(R.id.blogTitle)
        TextView title;

        public BlogsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        public SliderViewHolder(View itemView) {
            super(itemView);
        }
    }


    public class PostsViewHolder extends RecyclerView.ViewHolder {

        public PostsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
