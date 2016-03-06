package chiragshenoy.myportfolio.Activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.error.VolleyError;

import java.util.ArrayList;

import butterknife.Bind;
import chiragshenoy.myportfolio.Adapters.MoviesListingAdapter;
import chiragshenoy.myportfolio.Api.MyPortfolioApis;
import chiragshenoy.myportfolio.Models.Collections.MovieList;
import chiragshenoy.myportfolio.Models.MovieModel;
import chiragshenoy.myportfolio.R;
import chiragshenoy.myportfolio.Utils.NetworkHelperUtils;

/**
 * Created by Chirag Shenoy on 12-Feb-16.
 */
public class SpotifyStreamerMovieListingActivity extends BaseActivity {

    GridLayoutManager gridLayoutManager;

    @Bind(R.id.rvMovies)
    RecyclerView rvMovies;

    private ArrayList<MovieModel> mMovieModels = new ArrayList<>();

    private MoviesListingAdapter mMovieListingAdapter;
    String sort_order = "popularity.desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpViewAndAdapter();
        getMoviesFromNetworkCall(sort_order);


    }

    private void setUpViewAndAdapter() {

        mMovieListingAdapter = new MoviesListingAdapter(getApplicationContext(), new MoviesListingAdapter.CardTapListener() {
            @Override
            public void onTap(View v, MovieModel movieModel) {

            }
        });

        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(gridLayoutManager);
        rvMovies.setAdapter(mMovieListingAdapter);

    }

    private void getMoviesFromNetworkCall(String sort_order) {

        MyPortfolioApis.requestMovieList(sort_order, new Response.Listener<MovieList>() {
            @Override
            public void onResponse(MovieList response) {
                processAndDisplay(response.getmMovieModels());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), R.string.generic_error, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void processAndDisplay(ArrayList<MovieModel> response) {

        mMovieModels.addAll(response);
        mMovieListingAdapter.setDataSet(mMovieModels);
        mMovieListingAdapter.notifyDataSetChanged();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_spotify_streamer;
    }
}
