package chiragshenoy.myportfolio.Parsers;

import com.google.gson.Gson;

import chiragshenoy.myportfolio.Models.Collections.MovieList;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MovieListParser extends BaseApiParser {
    public MovieListParser(String response) {
        super(response);
    }

    @Override
    public MovieList getParserResponse() {

        Gson gson = new Gson();
        return gson.fromJson(mResponse, MovieList.class);
    }
}
