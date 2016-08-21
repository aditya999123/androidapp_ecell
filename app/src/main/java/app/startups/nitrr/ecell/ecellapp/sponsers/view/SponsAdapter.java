package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;

/**
 * Created by Iket on 8/21/2016.
 */
public class SponsAdapter extends RecyclerView.Adapter<SponsAdapter.MyViewHolder> {
private List<SponsData> sponsDataList= new ArrayList<>();
private Context context;
private LayoutInflater layoutInflater;
private ImageLoader imageLoader;

public SponsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

        imageLoader = new GlideImageLoader(context);
        }

public void setData(List<SponsData> sponsData) {
        this.sponsDataList=sponsData;
        }

@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.activity_spons_item, parent, false);
        return new MyViewHolder(itemView);
        }

@Override
public void onBindViewHolder(final MyViewHolder holder, int position) {

        SponsData sponsData=sponsDataList.get(position);
        imageLoader.loadImage(sponsData.getImage1(), holder.image1);
        imageLoader.loadImage(sponsData.getImage2(),holder.image2);
        }

@Override
public int getItemCount() {
        return this.sponsDataList.size();
        }

protected class MyViewHolder extends RecyclerView.ViewHolder {

    private ImageView image1,image2;


    private MyViewHolder(View itemView) {
        super(itemView);
        image1= (ImageView) itemView.findViewById(R.id.image1);
        image2= (ImageView) itemView.findViewById(R.id.image2);

    }

}
}
