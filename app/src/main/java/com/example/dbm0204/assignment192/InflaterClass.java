package com.example.dbm0204.assignment192;

import android.app.Activity;
import android.os.Bundle;

import static com.example.dbm0204.assignment192.MovieAdapter._hashMap;
import static com.example.dbm0204.assignment192.MovieAdapter.key_to_product_name;

/**
 * Created by dbm0204 on 7/15/17.
 */

public class InflaterClass extends Activity {

    private String MovieName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_profile);
        String movieName = getIntent().getExtras().toString();
        Movie movieItem = _hashMap.get(MovieName);




    }
}
