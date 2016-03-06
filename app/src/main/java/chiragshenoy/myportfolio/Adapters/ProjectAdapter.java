package chiragshenoy.myportfolio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import chiragshenoy.myportfolio.Activities.SpotifyStreamerMovieListingActivity;
import chiragshenoy.myportfolio.Models.Project;
import chiragshenoy.myportfolio.R;

/**
 * Created by Chirag Shenoy on 04-Feb-16.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    ArrayList<Project> mProjects = new ArrayList<Project>();

    Context mContext;
    int[] colors;
    Typeface robotoRegular;
    Typeface robotoMedium;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        @Bind(R.id.tvProjectTitle)
        public TextView mProjectTitle;

        @Bind(R.id.tvProjectSubtitle)
        public TextView mProjectSubTitle;

        @Bind(R.id.ivProjectIcon)
        public ImageView mProjectIcon;

        @Bind(R.id.card_view)
        public CardView mCardView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProjectAdapter(ArrayList<Project> projects, Context context) {


        this.mContext = context;
        this.mProjects = projects;

        robotoRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/robotoregular.ttf");
        robotoMedium = Typeface.createFromAsset(mContext.getAssets(), "fonts/robotomedium.ttf");

        colors = context.getResources().getIntArray(R.array.primary_colors);


    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mProjectTitle.setTypeface(robotoRegular);
        holder.mProjectSubTitle.setTypeface(robotoMedium);
        holder.mProjectTitle.setText(mProjects.get(position).getProjectTitle());
        holder.mCardView.setBackgroundColor(colors[position]);
        holder.mProjectSubTitle.setText(mProjects.get(position).getProjectSubTitle());
        Picasso.with(mContext).load(mProjects.get(position).getProjectIcon()).resize(64, 64).into(holder.mProjectIcon);


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext.getApplicationContext(), SpotifyStreamerMovieListingActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mProjects.size();
    }
}
