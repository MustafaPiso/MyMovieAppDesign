package com.example.piso.mymovieapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Piso on 24/08/2016.
 */
public class MovieHelper extends SQLiteOpenHelper {

    private static final String Type  = " TEXT";
    private static final  int Version = 1 ;
    public  static final String DataBase_Name = "Favourite_Movies.db";
    private static final String SQL_CREATE_DATABASE = "CREATE TABLE "+ "Favourite"+ " ( "+
                  MovieContract.Fav_Movies.favorite.id+ Type +
            "," + MovieContract.Fav_Movies.favorite.original_title + Type+
            "," + MovieContract.Fav_Movies.favorite.overview + Type +
            "," + MovieContract.Fav_Movies.favorite.release_date + Type +
            "," + MovieContract.Fav_Movies.favorite.vote_average + Type +
            "," + MovieContract.Fav_Movies.favorite.runtime + Type +
            "," + MovieContract.Fav_Movies.favorite.imdb_id + Type +
            "," + MovieContract.Fav_Movies.favorite.tagline + Type +
            "," + MovieContract.Fav_Movies.favorite.backdrop_path+ Type +
            "," + MovieContract.Fav_Movies.favorite.poster_path + Type +
            "," + MovieContract.Fav_Movies.favorite.title + Type +
            "," + MovieContract.Fav_Movies.favorite.URL + Type +
            "," + MovieContract.Fav_Movies.favorite.production_year + Type +
            "," + MovieContract.Fav_Movies.favorite.production_day + Type +" ); ";
    private static final String SQL_DELETE_DATABASE = "DROP TABLE IF EXIST " + "Favourite" ;

    public MovieHelper(Context context ) {
        super(context, DataBase_Name, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }
}
