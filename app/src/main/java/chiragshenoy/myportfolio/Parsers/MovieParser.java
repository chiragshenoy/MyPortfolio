package chiragshenoy.myportfolio.Parsers;

import com.google.gson.Gson;

import chiragshenoy.myportfolio.Models.MovieModel;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MovieParser extends BaseApiParser {

    public MovieParser(String response) {
        super(response);
    }

    @Override
    public MovieModel getParserResponse() {

        Gson gson = new Gson();
        return gson.fromJson(mResponse, MovieModel.class);
    }
}
