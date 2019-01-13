/*
 ************************ Assignment #HOMEWORK 03 *******************************************
 *********************** File Name- edit_movie.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */
package com.example.manalighare.hw03;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.Year;

public class edit_movie extends AppCompatActivity {

    static String NAME_TO_EDIT="Movie";
    static String UPDATE_MOVIE_OBJECT="Movie";

    private EditText name;
    private EditText desc;
    private EditText year;
    private EditText imdb;

    private TextView show_rating;


    private Spinner genre;

    private SeekBar rating;

    private Button Save_btn;


    private int Rating;
    private int Year=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        setTitle("Edit Movie");

        name=(EditText)findViewById(R.id.name);
        desc=(EditText)findViewById(R.id.desc);
        year=(EditText)findViewById(R.id.year);
        imdb=(EditText)findViewById(R.id.imdb);
        genre=(Spinner)findViewById(R.id.genre);
        rating=(SeekBar)findViewById(R.id.rating_seekBar);
        show_rating=(TextView)findViewById(R.id.show_rating);
        Save_btn=(Button)findViewById(R.id.save_button);




        Movie obj_to_edit=(Movie) getIntent().getExtras().getSerializable(NAME_TO_EDIT);
        Log.d("data is : "," "+obj_to_edit.toString());
        name.setText(obj_to_edit.getName());
        desc.setText(obj_to_edit.getDescription());
        year.setText(String.valueOf(obj_to_edit.getYear()));
        rating.setProgress(obj_to_edit.getRating());
        Rating=obj_to_edit.getRating();
        imdb.setText(obj_to_edit.getImdb());
        show_rating.setText(String.valueOf(obj_to_edit.getRating()));

        switch (obj_to_edit.getGenre()){

            case "Action":
                genre.setSelection(1);
                break;

            case "Animation":
                genre.setSelection(2);
                break;

            case "Comedy":
                genre.setSelection(3);
                break;

            case "Documentry":
                genre.setSelection(4);
                break;

            case "Family":
                genre.setSelection(5);
                break;

            case "Horror":
                genre.setSelection(6);
                break;

            case "Crime":
                genre.setSelection(7);
                break;

            case "Others":
                genre.setSelection(8);
                break;

        }

        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                show_rating.setText(String.valueOf(progress));
                Rating=progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    try {
                        Year=Integer.parseInt(year.getText().toString());
                    }catch (Exception e){

                    }


                    if(validate_entries()==0) {
                        Movie updated_movie = new Movie(name.getText().toString()
                                , desc.getText().toString()
                                , genre.getSelectedItem().toString()
                                , Rating
                                , Year
                                , imdb.getText().toString());

                    Log.d("object to return is : "," "+updated_movie.toString());
                    Intent return_intent=new Intent();
                    return_intent.putExtra(UPDATE_MOVIE_OBJECT,updated_movie);
                    setResult(RESULT_OK,return_intent);
                    finish();

                    }


            }
        });

    }


    public int validate_entries(){

        int i=0;

        if(Year<1000 ||Year>2018){

            year.setError("Please enter year between 1000 to 2018");
            i=1;
        }
        if(name.getText().length()==0){
            name.setError("Please enter name");
            i=1;
        }
        if(desc.getText().length()==0){
            desc.setError("Please enter description");
            i=1;
        }
        if(genre.getSelectedItem().toString().equals("None Selected")){
            ((TextView)genre.getSelectedView()).setError("Please select genre");
            i=1;
        }
        if(imdb.getText().length()==0){
            imdb.setError("Please enter imdb link");
            i=1;
        }


        return i;

    }
}
