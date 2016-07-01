package app.startups.nitrr.ecell.ecellapp.blogs;

/**
 * Created by Meghal on 5/30/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.blogs.data.BlogData;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;


/**
 * Created by Meghal on 5/17/2016.
 */
public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.MyViewHolder> {

    private static final String TAG = "BlogAdapter";
    private List<BlogData> blogDataList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;

    public BlogsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = new GlideImageLoader(context);
    }

    public void setData(List<BlogData> blogDataList) {
        this.blogDataList = blogDataList;
        if (blogDataList == null) {
            Log.e(TAG, "null data set");
        } else {
            Log.i(TAG, "data size: " + blogDataList.size());
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.blog_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        BlogData blogData = blogDataList.get(position);

        Log.i(TAG, "Adapter : " + blogDataList.get(0).getBlogTitle());
        holder.blogTitle.setText(blogData.getBlogTitle());
        holder.blogBody.setText(blogData.getBlogBody());
        holder.blogDate.setText(blogData.getBlogDate());

        holder.blogOwner.setText(blogData.getBlogOwner());

        imageLoader.loadImage(blogData.getBlogImage(), holder.blogImage);
    }

    @Override
    public int getItemCount() {


        return this.blogDataList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView blogDate;
        private TextView blogTitle;
        private ImageView imageView;
        private TextView blogBody;
        private ImageView blogImage;
        private TextView blogOwner;

        private MyViewHolder(View itemView) {
            super(itemView);
            blogTitle = (TextView) itemView.findViewById(R.id.blogTitle);
            blogDate = (TextView) itemView.findViewById(R.id.blogDate);
            blogBody = (TextView) itemView.findViewById(R.id.blogBody);
            blogImage = (ImageView) itemView.findViewById(R.id.blogImage);
            blogOwner = (TextView) itemView.findViewById(R.id.blogOwner);
        }
    }
}
