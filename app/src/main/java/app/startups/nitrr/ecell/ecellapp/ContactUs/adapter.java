package app.startups.nitrr.ecell.ecellapp.ContactUs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Random;

import app.startups.nitrr.ecell.ecellapp.R;

/**
 * Created by aditya on 17/5/16.
 */
public class adapter extends RecyclerView.Adapter<adapter.contactViewHolder>{
Context context,context2;
    int flag=0;
private ArrayList<datavar> item;
    private Button test;
    private Button callbtn;
    private PopupWindow popupwindow;;
    private LayoutInflater layoutInflater;

    public adapter(Context applicationContext, ArrayList<datavar> item) {
        this.item = item;this.context=applicationContext;
    }
View viewanime;
    @Override
    public adapter.contactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_contactus_cardview,viewGroup,false);
viewanime=v;
contactViewHolder datavar =new contactViewHolder(v);


        return datavar;
    }
    @Override
    public void onBindViewHolder(adapter.contactViewHolder contactViewHolder, int i) {

        contactViewHolder.name_p.setText(item.get(i).getName_p());
        contactViewHolder.dsgn_p.setText(item.get(i).getDsgn_p());
        contactViewHolder.number_p.setText(item.get(i).getNumber_p());
        contactViewHolder.email_p.setText(item.get(i).getEmail_p());
        //contactViewHolder.image_p.setImageResource(item.get(i).getImage_p());

        Picasso.with(context).load("http://adityaagr.tk/" + item.get(i).getEmail_p() + ".jpg").transform(new CircleTransform()).into(contactViewHolder.image_p);
        setAnimation(contactViewHolder.itemView,i);

    }

    private int lastPosition = -1;

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }
    //.
    @Override
    public int getItemCount() {
        return item.size();
    }

    public class contactViewHolder extends RecyclerView.ViewHolder {
        ImageView image_p;
        TextView name_p, dsgn_p, number_p, email_p;
        ImageButton imgbtn, emailbtn;
        RelativeLayout relativeLayout;
        RecyclerView re1;



        public contactViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.getRootView().findViewById(R.id.contactus_cardview_r1);
            image_p = (ImageView) itemView.findViewById(R.id.contactus_cardview_viewimage);
            name_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewname);
            dsgn_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewdsgn);
            number_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewnum);
            email_p = (TextView) itemView.findViewById(R.id.contactus_cardview_viewemail);

            re1=(RecyclerView)itemView.findViewById(R.id.contactus_recycler1) ;

            }

    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source)
        {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source)
            {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }

}


