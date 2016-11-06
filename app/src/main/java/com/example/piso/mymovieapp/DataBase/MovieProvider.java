package com.example.piso.mymovieapp.DataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Piso on 24/08/2016.
 */
public class MovieProvider extends ContentProvider {

    private static final UriMatcher uriMatcher = buildUriMatcher();
    private MovieHelper  movieHelper ;
    private SQLiteDatabase database  ;
     public  MovieProvider (){
    }
    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        final String authority = MovieContract.CONTENT_AUTHORITY;
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, MovieContract.Fav_Movies.Table_name,10);
        matcher.addURI(authority, MovieContract.Fav_Movies.Table_name+"/id",15);
         return matcher;
    }
    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
         return true ;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

         Cursor cursor ;
        database = movieHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)) {
            case 10 : {
                 cursor  = database.rawQuery("select * from Favourite ", null);
                break;
            }
            case 15 : {
                cursor  = database.rawQuery("select * from Favourite where id="+selection, null);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        if(cursor.isAfterLast())
            return  null;

        database.close();
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values){

          database = movieHelper.getWritableDatabase();
        long  row_ID = database.insert("Favourite", null, values);
        if(row_ID>0)
        {
            Uri uri_id = ContentUris.withAppendedId(MovieContract.Fav_Movies.specific_Uri,row_ID);
            getContext().getContentResolver().notifyChange(uri_id,null);
            return  uri_id  ;
        }
        database.close();
        throw new SQLException("Failed to insert new Movie ");

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        database = movieHelper.getWritableDatabase();
        int c =0;

         switch (uriMatcher.match(uri)) {
            case 10 : {
                 c = database.delete("Favourite","id="+selection,selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return c ;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
