package com.example.dbm0204.assignment192;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
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
    private TextView name;
    private ImageView imageView;
    private RatingBar rating;
    public static String key_to_product_name;
    public static HashMap<String, Movie> _hashMap;


    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
        movieModelsList = objects;
        this.resource = resource;
        this.context = context;
        _hashMap= new HashMap<>();

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content, null);
        }
        name = (TextView) convertView.findViewById(R.id.name);
        imageView = (ImageView) convertView.findViewById(R.id.movieIcon);
        rating = (RatingBar) convertView.findViewById(R.id.ratingBar);
        name.setText(movieModelsList.get(position).getName());
        Picasso.with(getContext()).load(movieModelsList.get(position).getImageURL()).into(imageView);
        float myRating = (float) movieModelsList.get(position).getRating();
        rating.setRating(myRating / 2);
        Context context = parent.getContext();
        String itemName= getItem(position).toString();
        convertView.setOnClickListener(new myOnClickListener(itemName,context));
        return convertView;
    }

    private class myOnClickListener implements View.OnClickListener {
        private String _itemName;
        private Context _context;

        public myOnClickListener(String _itemName, Context _context) {
            this._itemName = _itemName;
            this._context = _context;
        }
        @Override
        public void onClick(View view){
            //--listener onCliCk example method Body---
            Toast.makeText(getContext(),"ListView Item Clicked:"+_itemName,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(_context,InflaterClass.class);
            intent.putExtra(key_to_product_name,_itemName);
            _context.startActivity(intent);
        }
    }
}