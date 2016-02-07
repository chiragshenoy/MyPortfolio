package chiragshenoy.myportfolio.Activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import chiragshenoy.myportfolio.Adapters.ProjectAdapter;
import chiragshenoy.myportfolio.Models.Project;
import chiragshenoy.myportfolio.R;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rvProjects)
    RecyclerView mRecyclerView;

    private ProjectAdapter mProjectAdapter;

    private ArrayList<Project> mProjects;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProjects = new ArrayList<Project>();

        populateClasses();
        initViews();


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

        mProjectAdapter = new ProjectAdapter(mProjects, getApplication());
        mRecyclerView.setAdapter(mProjectAdapter);
    }

    private void populateClasses() {

        String[] titles = new String[]{"Spotify Streamer", "Scores App", "Library App", "Build it bigger", "XYZ Reader", "Capstone!"};
        String[] subtitles = new String[]{"Project 0", "Project 1", "Project 2", "Project 3", "Project 4", "Project 5"};
        Integer[] icons = new Integer[]{R.drawable.spotify, R.drawable.score, R.drawable.library, R.drawable.builditbigger, R.drawable.xyzreader, R.drawable.capstone};

        for (int i = 0; i < titles.length; i++) {
            mProjects.add(i,new Project(titles[i], subtitles[i], icons[i]));
        }

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
