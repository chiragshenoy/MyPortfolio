package chiragshenoy.myportfolio.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import chiragshenoy.myportfolio.R;
import chiragshenoy.myportfolio.Utils.AnimationHelperUtils;
import chiragshenoy.myportfolio.Utils.AppConstants;
import chiragshenoy.myportfolio.Utils.PaletteTransformation;

/**
 * Created by Chirag Shenoy on 06-Mar-16.
 */
public class MovieDetailsActivity extends BaseActivity {

    @Bind(R.id.ivMoviePoster)
    ImageView ivMoviePoster;

    @Bind(R.id.ivBackDrop)
    ImageView ivBackDrop;

    @Bind(R.id.tv_original_title)
    TextView tv_original_title;

    @Bind(R.id.tv_rating)
    TextView tv_rating;

    @Bind(R.id.tv_overview)
    TextView tv_overview;

    @Bind(R.id.tv_release_date)
    TextView tv_release_date;

    @Bind(R.id.container)
    RelativeLayout container;

    @Bind(R.id.rating_total)
    TextView rating_total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateViews();

    }

    private void populateViews() {
        Intent intent = getIntent();

        String poster_path = intent.getStringExtra(AppConstants.POSTER_PATH_EXTRA);
        String back_drop_path = intent.getStringExtra(AppConstants.BACKDROP_PATH_EXTRA);
        String original_title = intent.getStringExtra(AppConstants.ORIGINAL_TITLE_EXTRA);
        String rating = intent.getStringExtra(AppConstants.USER_RATING_EXTRA);
        String overview = intent.getStringExtra(AppConstants.OVERVIEW_EXTRA);
        String release_date = intent.getStringExtra(AppConstants.RELEASE_DATE_EXTRA);

        Picasso.with(this).load(AppConstants.TMDB_POSTER_PATH_BASE_URL + poster_path).into(ivMoviePoster);
        Picasso.with(this)
                .load(AppConstants.TMDB_BACKDROP_PATH_BASE_URL + back_drop_path)
                .transform(PaletteTransformation.instance())

                .into(ivBackDrop, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) ivBackDrop.getDrawable()).getBitmap();
                        Palette palette = PaletteTransformation.getPalette(bitmap);
                        // apply palette to text views, backgrounds, etc.

                        setPaletteColors(palette);
                    }
                });

        if (original_title.length() > 15) {
            tv_original_title.setTextSize(22);
        }

        if (original_title.length() > 22) {
            tv_original_title.setTextSize(16);
        }


        Typeface robotocondensedlight = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/robotocondensedlight.ttf");
        Typeface robotocondensedbold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/robotocondensedbold.ttf");


        tv_original_title.setTypeface(robotocondensedbold);
        tv_original_title.setText(original_title);

        tv_rating.setText(rating);

        tv_overview.setTypeface(robotocondensedlight);
        tv_overview.setText(overview);

        tv_release_date.setText(release_date);

    }

    private void setPaletteColors(Palette palette) {
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

        int vibrantDark = palette.getDarkVibrantColor(0xff000000);

        if (vibrantSwatch != null) {
            container.setBackgroundColor(vibrantSwatch.getRgb());
            tv_overview.setTextColor(vibrantSwatch.getBodyTextColor());
            rating_total.setTextColor(vibrantSwatch.getBodyTextColor());
            tv_rating.setTextColor(vibrantSwatch.getBodyTextColor());
            tv_release_date.setTextColor(vibrantSwatch.getBodyTextColor());
        }

        //Not sure why getDarkVibrantColor() returns 0 sometimes.
        if (vibrantDark == 0) {
            tv_original_title.setTextColor(0xff000000);
        } else {
            tv_original_title.setTextColor(vibrantDark);
        }
        AnimationHelperUtils.statusBarColorTransition(vibrantDark, 400, MovieDetailsActivity.this);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_movie_details;
    }

}
