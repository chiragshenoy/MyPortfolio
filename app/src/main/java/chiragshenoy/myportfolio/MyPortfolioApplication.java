package chiragshenoy.myportfolio;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.RequestTickle;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.VolleyTickle;

import chiragshenoy.myportfolio.Api.MyPortfolioApis;
import chiragshenoy.myportfolio.Utils.HTTPManager;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class MyPortfolioApplication extends Application {

    private static MyPortfolioApplication myPortfolioApplication;

    private RequestQueue mRequestQueue;
    private RequestTickle mRequestTickle;
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

    public MyPortfolioApis getApi(){
        return myPortfolioApis;
    }

    public static MyPortfolioApplication get() {
        if (myPortfolioApplication == null)
            Log.e("quit","asd");
//            System.exit(0);

        return myPortfolioApplication;
    }

    private void setupNetwork() {
        HTTPManager HTTPManager = new HTTPManager(this);
        HttpStack httpStack = HTTPManager.getHttpStack();

        mRequestQueue = Volley.newRequestQueue(this);
        mRequestTickle = VolleyTickle.newRequestTickle(this, httpStack);
    }

    private void setupApi() {
        myPortfolioApis = new MyPortfolioApis(mRequestQueue);
    }
}
