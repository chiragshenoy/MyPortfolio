package chiragshenoy.myportfolio.Models.Collections;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import chiragshenoy.myportfolio.Models.BaseModel;
import chiragshenoy.myportfolio.Models.MovieModel;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MovieList extends BaseModel {

    @SerializedName("results")
    ArrayList<MovieModel> mMovieModels;

    public MovieList(ArrayList<MovieModel> movieModels) {
        this.mMovieModels = movieModels;
    }

    public ArrayList<MovieModel> getmMovieModels() {
        return this.mMovieModels;
    }

    public void setmMovieModels(ArrayList<MovieModel> mMovieModels) {
        this.mMovieModels = mMovieModels;
    }
}
