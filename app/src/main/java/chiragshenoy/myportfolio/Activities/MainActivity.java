package chiragshenoy.myportfolio.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import chiragshenoy.myportfolio.Adapters.ProjectAdapter;
import chiragshenoy.myportfolio.R;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rvProjects)
    RecyclerView mRecyclerView;

    private ProjectAdapter mProjectAdapter;
    private ArrayList<String> mProjectClasses;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProjectClasses = new ArrayList<>();

        initViews();

        populateClasses();


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }


    private void initViews() {
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProjectAdapter = new ProjectAdapter(mProjectClasses);
        mRecyclerView.setAdapter(mProjectAdapter);
    }

    private void populateClasses() {

        mProjectClasses.add("Spotify Streamer");
        mProjectClasses.add("Scores App");
        mProjectClasses.add("Library App");
        mProjectClasses.add("Build it bigger");
        mProjectClasses.add("XYZ Reader");
        mProjectClasses.add("Capstone Project");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
