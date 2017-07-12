package com.example.dbm0204.assignment192;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dbm0204 on 7/12/17.
 * Movie adapter extends ArrayAdapter
 *
 */

public class MovieAdapter extends ArrayAdapter {
    private List<Movie> movieModelsList;
    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource,objects);
        movieModelsList = objects;
        this.resource = resource;
        this.context = context;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content,null);

        }

        TextView name;
        ImageView imageView;
        RatingBar rating;

        name = (TextView)convertView.findViewById(R.id.name);
        imageView = (ImageView)convertView.findViewById(R.id.movieIcon);
        rating = (RatingBar)convertView.findViewById(R.id.ratingBar);

        name.setText(movieModelsList.get(position).getName());
        Picasso.with(getContext()).load(movieModelsList.get(position).getImageURL()).into(imageView);

        float myRating = (float) movieModelsList.get(position).getRating();
        rating.setRating(myRating/2);

        return convertView;
    }

}
