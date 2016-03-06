package chiragshenoy.myportfolio;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import chiragshenoy.myportfolio.Api.MyPortfolioApis;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MyPortfolioApplication extends Application {

    private static MyPortfolioApplication myPortfolioApplication;

    private RequestQueue mRequestQueue;
    private Context mContext;
    private MyPortfolioApis myPortfolioApis;

    @Override
    public void onCreate() {
        super.onCreate();
        myPortfolioApplication = this;

        mContext = this;
        setupNetwork();
        setupApi();


    }

    public MyPortfolioApis getApi() {
        return myPortfolioApis;
    }

    public static MyPortfolioApplication get() {
        if (myPortfolioApplication == null)
            System.exit(0);

        return myPortfolioApplication;
    }

    private void setupNetwork() {
        mRequestQueue = Volley.newRequestQueue(this);
    }

    private void setupApi() {
        myPortfolioApis = new MyPortfolioApis(mRequestQueue);
    }
}
