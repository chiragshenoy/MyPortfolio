package chiragshenoy.myportfolio.Api;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;

import java.nio.charset.Charset;

import chiragshenoy.myportfolio.Models.Collections.MovieList;
import chiragshenoy.myportfolio.Parsers.MovieListParser;
import chiragshenoy.myportfolio.Utils.AppConstants;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MovieListApi extends AppRequest<MovieList> {

    public MovieListApi(String sort_order, Response.Listener<MovieList> listener, Response.ErrorListener errorListener) {
        super(Method.GET, AppConstants.TMDB_BASE_URL, listener, errorListener);
        setHttpParams("sort_by", sort_order);
        setHttpParams("api_key", AppConstants.TMDB_API_KEY);

        setShouldCache(false);
    }

    @Override
    protected Response<MovieList> parseNetworkResponse(NetworkResponse response) {
        if (response.statusCode == 200) {
            MovieList mMovieList = new MovieListParser(new String(response.data, Charset.forName("UTF-8"))).getParserResponse();
            return Response.success(mMovieList, null);
        } else if (response.statusCode == 401) {
            return Response.error(new AuthFailureError());
        } else {
            return Response.error(new NetworkError());
        }
    }
}
