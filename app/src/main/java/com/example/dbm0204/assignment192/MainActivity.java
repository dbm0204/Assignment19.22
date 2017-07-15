package com.example.dbm0204.assignment192;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainActivity extends AppCompactivity and Instantiates the Views
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    ListView listView;
    List<Movie> movieModelsList;
    private static Toolbar toolbar;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        textView = (TextView) findViewById(R.id.name);
        listView = (ListView) findViewById(R.id.listView);
        new doIt().execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.popular:
                //TODO: add activity
                    return true;
            case R.id.upcoming:
                //TODO: add activity
                    return true;
            case R.id.latest:
                //TODO: add activity
                    return true;
            //TODO: add activity
            case R.id.now:
                //TODO: add activity
                    return true;
            case R.id.top:
                //TODO: add activity
                    return true;
            default:
                    return super.onOptionsItemSelected(item);
        }
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("IMDB TOP 250");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Toast.makeText(getApplicationContext(), "clicking the toolbar!", Toast.LENGTH_SHORT).show();
            }}
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public void onBackPressed(){
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    public class doIt extends AsyncTask<String, String, List<Movie>> {

        String words = "", mName = "";
        double rate = 0;

        protected List<Movie> doInBackground(String... params) {

            movieModelsList = new ArrayList<>();
            String urls[] = new String[255];
            double rating[] = new double[255];
            try {

                Document doc = Jsoup.connect("http://www.imdb.com/chart/top").get();
                Elements link = doc.select("img[src$=.jpg]");
                Elements content = doc.select("a[href^=/title/][title]");
                Elements ratings = doc.select("strong[title]");
                int i = 0;
                for (Element e : link) {
                    words = e.attr("src");
                    urls[i] = words;
                    i++;
                }
                i = 0;
                for (Element e : ratings) {

                    rate = Double.parseDouble(e.text());
                    rating[i] = rate;
                    i++;
                }
                i = 0;
                for (Element e : content) {

                    mName = e.text();

                    Movie movieModels = new Movie();

                    movieModels.setName(mName);
                    movieModels.setImageURL(urls[i]);
                    movieModels.setRating(rating[i]);

                    movieModelsList.add(movieModels);
                    i++;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }


            return movieModelsList;
        }

        @Override
        protected void onPostExecute(List<Movie> result) {
            super.onPostExecute(result);
            MovieAdapter adapter = new MovieAdapter(MainActivity.this, R.layout.content, result);
            listView.setAdapter(adapter);
        }
    }
}
