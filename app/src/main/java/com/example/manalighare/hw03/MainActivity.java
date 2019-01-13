/*
 ************************ Assignment #HOMEWORK 03 *******************************************
 *********************** File Name- MainActivity.java *************************************
 ************************ Full Name- 1. Manali Ghare 2. Anup Deshpande (Group 19) ***********

 */


package com.example.manalighare.hw03;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Movie> movies=new ArrayList<>();
    private ArrayList<String> movie_names = new ArrayList<>();
    private Button add_movie;
    private Button edit_movie;
    private Button delete_movie;
    private Button show_list_by_year;
    private Button show_list_by_rating;
    private int index_to_update;
    private int index_to_delete;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favourite Movies");

        add_movie=(Button) findViewById(R.id.add_movie);
        edit_movie=(Button) findViewById(R.id.edit_movie);
        delete_movie=(Button) findViewById(R.id.delete_movie);
        show_list_by_year=(Button) findViewById(R.id.show_year);
        show_list_by_rating=(Button)findViewById(R.id.show_rating);

        add_movie.setOnClickListener(this);
        edit_movie.setOnClickListener(this);
        delete_movie.setOnClickListener(this);
        show_list_by_year.setOnClickListener(this);
        show_list_by_rating.setOnClickListener(this);

       /* Movie data1 = new Movie("Rio","abcdabcd","Comedy",3,1996,"mnmn");
        Movie data2 = new Movie("Toystory","defToystoryToystorygdefToystoryToystoryg","",3,2001,"mnmn");
        Movie data3 = new Movie("Toystory1","qwerqwer","Other",5,2001,"lkjlkj");
        Movie data4 = new Movie("Crime Movie","mnbmnb","Crime",1,1998,"oklokl");
        movies.add(data1);
        movies.add(data2);
        movies.add(data3);
        movies.add(data4);*/

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.add_movie:
                final Intent intent=new Intent(this, add_movie.class);
                startActivityForResult(intent,1);
                break;

            case R.id.edit_movie:

                get_Movie_Names();
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Pick a Movie")
                        .setItems((CharSequence[]) movie_names.toArray(new CharSequence[movie_names.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("id is",""+movies.get(which).getName());
                                Intent edit_movie = new Intent(MainActivity.this, com.example.manalighare.hw03.edit_movie.class);
                                edit_movie.putExtra(com.example.manalighare.hw03.edit_movie.NAME_TO_EDIT,movies.get(which));
                                index_to_update=which;
                                startActivityForResult(edit_movie,2);
                            }
                        });

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                break;

            case R.id.delete_movie:
                get_Movie_Names();
                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("Pick a Movie")
                        .setItems((CharSequence[]) movie_names.toArray(new CharSequence[movie_names.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("id is",""+movies.get(which).getName());
                                index_to_delete=which;
                                Log.d("in movie_names: "," "+movie_names.get(index_to_delete));
                                Log.d("in movie list : "," "+movies.get(index_to_delete).getName());
                                Toast.makeText(getBaseContext()," "+movies.get(index_to_delete).getName()+" is deleted",Toast.LENGTH_SHORT).show();
                                movies.remove(index_to_delete);

                            }
                        });

                final AlertDialog alertDialog1 = builder2.create();
                alertDialog1.show();

                break;

            case R.id.show_year:
                Intent show_by_year = new Intent("com.example.manalighare.hw03.intent.action.VIEW");
                show_by_year.addCategory(Intent.CATEGORY_DEFAULT);
                show_by_year.putExtra(movies_by_year.MOVIES_TO_SHOW_BY_YEAR,movies);
                if(movies.size()==0){
                    Toast.makeText(getBaseContext(),"There are no movies to show",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(show_by_year);
                }

                break;

            case R.id.show_rating:
                Intent show_by_rating = new Intent("com.example.manalighare.hw03.intent.action.EDIT");
                show_by_rating.addCategory(Intent.CATEGORY_DEFAULT);
                show_by_rating.putExtra(movies_by_rating.MOVIES_TO_SHOW_BY_RATING,movies);
                if(movies.size()==0){
                    Toast.makeText(getBaseContext(),"There are no movies to show",Toast.LENGTH_LONG).show();
                }
                else{
                    startActivity(show_by_rating);
                }

                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode==1 && resultCode==RESULT_OK){

            Movie movie=(Movie)data.getExtras().getSerializable(com.example.manalighare.hw03.add_movie.MOVIE_KEY);
            movies.add(movie);
            Toast.makeText(getBaseContext()," "+movie.getName()+" is added",Toast.LENGTH_LONG).show();
            Log.d("length of movie array:"," "+movies.size());
        }

        if(requestCode==2 && resultCode==RESULT_OK){
            Movie updated_movie=(Movie)data.getExtras().getSerializable(com.example.manalighare.hw03.edit_movie.UPDATE_MOVIE_OBJECT);
            movies.set(index_to_update,updated_movie);
            Toast.makeText(getBaseContext()," "+movies.get(index_to_update).getName()+" is updated",Toast.LENGTH_SHORT).show();

        }
    }

    public ArrayList<String> get_Movie_Names(){
        movie_names.clear();
        for (int i=0; i<movies.size(); i++){
            movie_names.add(movies.get(i).getName());
        }
        return movie_names;
    }
}
