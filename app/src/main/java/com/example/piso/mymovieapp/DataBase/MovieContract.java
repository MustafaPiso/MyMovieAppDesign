package com.example.piso.mymovieapp.DataBase;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.piso.mymovieapp.Movie_Content.JsonData;


/**
 * Created by Piso on 24/08/2016.
 */
public class MovieContract {

    public static final String CONTENT_AUTHORITY = "com.example.piso.mymovieapp"; // my root to make Uri
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    // my base uri  that  use to make another URI

    public static final class Fav_Movies implements BaseColumns
    {
        public static String Table_name = "Movies" ;
        // to connect my table
        public static final Uri specific_Uri = BASE_CONTENT_URI.buildUpon().appendPath(Table_name).build();
        public static final Uri Uri_For_Id = specific_Uri.buildUpon().appendPath("id").build();
        public static JsonData favorite = new JsonData();
        // this Object have All Colunms in my table
    }


}
