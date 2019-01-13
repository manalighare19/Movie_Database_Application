/*
 ************************ Assignment #HOMEWORK 03 *******************************************
 *********************** File Name- add_movie.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */

package com.example.manalighare.hw03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class add_movie extends AppCompatActivity {

    private SeekBar seekBar;

    private TextView name;
    private TextView description;
    private TextView show_rating;
    private TextView year;
    private TextView imdb;

    private Button Add_Movie;

    private Spinner genre;

    private String Name;
    private String Description;
    private String Show_rating;
    private String Genre;
    private int Year;
    private int Rating;
    private String IMDB;

    static String MOVIE_KEY="Movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        seekBar = (SeekBar) findViewById(R.id.rating_seekBar);

        show_rating = (TextView) findViewById(R.id.show_rating);
        name = (TextView) findViewById(R.id.name);
        description = (TextView) findViewById(R.id.desc);
        year = (TextView) findViewById(R.id.year);
        imdb = (TextView) findViewById(R.id.imdb);

        Add_Movie = (Button) findViewById(R.id.add_movie);

        genre = (Spinner) findViewById(R.id.genre);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show_rating.setText(String.valueOf(progress));
                Rating = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Add_Movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Name = name.getText().toString();
                    Description = description.getText().toString();
                    Genre = genre.getSelectedItem().toString();
                    try{
                        Year = Integer.parseInt(year.getText().toString());
                    }catch (Exception e){

                    }

                    IMDB = imdb.getText().toString();


                    if(validate_entries()==0){

                        Movie obj = new Movie(Name, Description, Genre, Rating, Year, IMDB);
                        Log.d("name", "" + obj.toString());
                        Intent return_intent = new Intent();
                        return_intent.putExtra(MOVIE_KEY, obj);
                        setResult(RESULT_OK, return_intent);
                        finish();
                    }


                }





        });



    }

    public int validate_entries(){

        int i=0;

        if(Year<1000 || Year>2018){

            year.setError("Please enter year between 1000 to 2018");
            i=1;
        }
        if(Name.length()==0){
            name.setError("Please enter name");
            i=1;
        }
        if(Description.length()==0){
            description.setError("Please enter description");
            i=1;
        }
        if(Genre.equals("None Selected")){
            ((TextView)genre.getSelectedView()).setError("Please select genre");
            i=1;
        }
        if(IMDB.length()==0){
            imdb.setError("Please enter imdb link");
            i=1;
        }


        return i;

    }

    }



