package chiragshenoy.myportfolio.Api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import chiragshenoy.myportfolio.Models.Collections.MovieList;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MyPortfolioApis {

    private static RequestQueue mRequestQueue;

    public MyPortfolioApis(RequestQueue requestQueue) {
        this.mRequestQueue = requestQueue;
    }

    public static Request<?> requestMovieList(String sortOrder, Response.Listener<MovieList> listener, Response.ErrorListener errorListener) {
        return mRequestQueue.add(new MovieListApi(sortOrder, listener, errorListener));
    }


}
