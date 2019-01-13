/*
 ************************ Assignment #HOMEWORK 03 *******************************************
 *********************** File Name- movie_by_rating.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */
package com.example.manalighare.hw03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class movies_by_rating extends AppCompatActivity implements View.OnClickListener {

    private ImageView first;
    private ImageView previous;
    private ImageView next;
    private ImageView last;


    private TextView name;
    private TextView description;
    private TextView show_rating;
    private TextView year;
    private TextView imdb;
    private TextView genre;


    private Button finish;

    private int current_index=0;

    static String MOVIES_TO_SHOW_BY_RATING="Movie";

    private ArrayList<Movie> movies=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_by_rating);
        setTitle("Movies by Rating");

        first = (ImageView) findViewById(R.id.first_image);
        previous = (ImageView) findViewById(R.id.previous_image);
        next = (ImageView) findViewById(R.id.next_image);
        last = (ImageView) findViewById(R.id.last_image);

        finish = (Button) findViewById(R.id.finish_btn);

        name=(TextView)findViewById(R.id.name);
        description=(TextView)findViewById(R.id.desc);
        show_rating=(TextView)findViewById(R.id.show_rating);
        year=(TextView)findViewById(R.id.year);
        imdb=(TextView)findViewById(R.id.imdb);
        genre=(TextView)findViewById(R.id.genre);



        movies = (ArrayList<Movie>) getIntent().getSerializableExtra(MOVIES_TO_SHOW_BY_RATING);

        Log.d("obtained arraylist size"," "+movies.size());

        Collections.sort(movies, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if(o1.getRating()<o2.getRating()){
                    return 1;
                }else if(o1.getRating()>o2.getRating()){
                    return -1;
                }
                return 0;
            }
        });

        load_values();

        finish.setOnClickListener(this);
        first.setOnClickListener(this);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
        last.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.finish_btn:
                finish();
                break;

            case R.id.first_image:
                current_index=0;
                load_values();
                break;


            case R.id.last_image:
                current_index=movies.size()-1;
                load_values();
                break;


            case R.id.next_image:

                if(current_index==movies.size()-1){
                    Toast.makeText(getBaseContext(),"End of movies",Toast.LENGTH_LONG).show();
                }else {
                    current_index+=1;
                    load_values();

                }
                break;

            case R.id.previous_image:

                if(current_index==0){
                    Toast.makeText(getBaseContext(),"This is the highest rated movie",Toast.LENGTH_LONG).show();
                }else {
                    current_index-=1;
                    load_values();

                }
                break;


        }


    }


    private void load_values() {
        name.setText(movies.get(current_index).getName());
        description.setText(movies.get(current_index).getDescription());
        genre.setText(movies.get(current_index).getGenre());
        show_rating.setText(String.valueOf(movies.get(current_index).getRating())+"/5");
        year.setText(String.valueOf(movies.get(current_index).getYear()));
        imdb.setText(movies.get(current_index).getImdb());
    }
}
