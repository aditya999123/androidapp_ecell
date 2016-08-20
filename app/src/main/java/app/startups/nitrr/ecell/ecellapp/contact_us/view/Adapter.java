package app.startups.nitrr.ecell.ecellapp.contact_us.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.GlideImageLoader;
import app.startups.nitrr.ecell.ecellapp.helper.image_loaders.ImageLoader;

/**
 * Created by Iket on 8/20/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<ContactsData> ContactDataList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;

    public Adapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = new GlideImageLoader(context);
    }

    public Adapter(List<ContactsData> contactDataList) {
        ContactDataList = contactDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.activity_contactus_cardview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        ContactsData ContactData = ContactDataList.get(position);

        holder.name_p.setText(ContactData.getName());
        holder.email_p.setText(ContactData.getEmail());
        holder.dsgn_p.setText(ContactData.getDesignation());
        holder.number_p.setText(ContactData.getPhone());
        imageLoader.load_circular_image(ContactData.getImage(), holder.image_p);

    }

    @Override
    public int getItemCount() {
        return this.ContactDataList.size();

    }

    public void setData(List<ContactsData> ContactDataList) {
        this.ContactDataList = ContactDataList;
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_p;
        TextView name_p, dsgn_p, number_p, email_p;

        private MyViewHolder(View itemView) {
            super(itemView);
            image_p = (ImageView) itemView.findViewById(R.id.contactus_cardview_viewimage);
            name_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewname);
            dsgn_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewdsgn);
            number_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewnum);
            email_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewemail);
            Typeface tf = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Black.ttf");
            name_p.setTypeface(tf, Typeface.BOLD);
        }

    }
}

