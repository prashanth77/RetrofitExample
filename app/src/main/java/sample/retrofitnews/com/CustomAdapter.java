package sample.retrofitnews.com;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<ArticlesItem> dataSet;
    String imgUrl;
    private int mExpandedPosition = -1;

    private RecyclerView recyclerView = null;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView heading;
        TextView date;
        TextView discription;
        ImageView ivBasicImage;
        ToggleButton tg_button;
        Context context;
        Button bt_reamore;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.heading = (TextView) itemView.findViewById(R.id.heading);
            this.date = (TextView) itemView.findViewById(R.id.date);
            this.discription = (TextView) itemView.findViewById(R.id.description);
            this.ivBasicImage = (ImageView) itemView.findViewById(R.id.img1);
            this.tg_button = (ToggleButton) itemView.findViewById(R.id.tgplay);
            this.bt_reamore = (Button) itemView.findViewById(R.id.bt_readmore);

            this.context = itemView.getContext();


        }


    }

    public CustomAdapter(ArrayList<ArticlesItem> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_views, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;


    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView title = holder.heading;
        TextView date = holder.date;
        TextView discription = holder.discription;
        ImageView ivBasicImage = holder.ivBasicImage;
/*
        ToggleButton toggleButton = holder.tg_button;
        Button Button = holder.bt_reamore;
*/

        Uri uri = Uri.parse(dataSet.get(listPosition).getUrlToImage());
        final Context context = holder.ivBasicImage.getContext();
//            Context context1 = holder.discription.getContext();

        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
//        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Picasso.with(context).load(uri)
                .placeholder((R.drawable.image_not_found))
                .error((R.drawable.image_not_found))
                .resize(width,300)
                .into(holder.ivBasicImage)

        ;


        final boolean isExpanded = listPosition == mExpandedPosition;

        holder.discription.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.bt_reamore.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.itemView.setActivated(isExpanded);


        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                mExpandedPosition = isExpanded ? -1 : listPosition;

//                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();

                return false;

            }
        });



        holder.bt_reamore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String news_url=dataSet.get(listPosition).getUrl();
                Intent intent = new Intent(context, News_Activity.class);
                intent.putExtra("news", news_url);
                context.startActivity(intent);


            }            });

        imgUrl = (dataSet.get(listPosition).getUrlToImage());
        title.setText(dataSet.get(listPosition).getTitle());
        date.setText(dataSet.get(listPosition).getPublishedAt());
        discription.setText(dataSet.get(listPosition).getDescription());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        this.recyclerView = recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

}


