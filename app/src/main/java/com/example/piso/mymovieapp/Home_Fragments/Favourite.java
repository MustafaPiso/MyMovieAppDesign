package com.example.piso.mymovieapp.Home_Fragments;

import android.annotation.TargetApi;


import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piso.mymovieapp.Adapters.CustomAdapter;
import com.example.piso.mymovieapp.DataBase.MovieContract;
import com.example.piso.mymovieapp.Movie_Content.JsonData;
import com.example.piso.mymovieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piso on 16/09/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Favourite extends Fragment {

    public  static  CustomAdapter customAdapter ;
   // public static String sort =  "popular" ;
    private  boolean fab_on = false  ;
    public  static List<JsonData> jsonData ;
    public  static  boolean refresh_list = true ;

     private RecyclerView recyclerView ;
     @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("Favourite" , " hey i'm are created ");
        View root =  inflater.inflate(R.layout.favourite, container, false);
         /*  fab = (FloatingActionButton) root.findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });*/
        jsonData = new ArrayList<JsonData>();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);
        refresh_list= true ;

        GridLayoutManager gridLayoutManager  = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return root ;
    }

    @Override
    public void onResume() {
        super.onResume();
        Update("favourite");
    }
    private void  Update (String sort ) {
        jsonData.clear();
        if (sort.equals("favourite")) {

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            Cursor cursor = getActivity().getContentResolver().query(MovieContract.Fav_Movies.specific_Uri, null, null, null, null);
            Get_List(cursor);

            customAdapter = new CustomAdapter(jsonData, (AppCompatActivity) getActivity());
            recyclerView.setAdapter(customAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }

    public void  Get_List (Cursor cursor) {

        jsonData.clear();
        if(cursor == null)  //// doesn't  work :/ :/
            return ;
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false) {
            JsonData obj = new JsonData();
            obj.id = cursor.getString(0);
            obj.original_title = cursor.getString(1);
            obj.overview = cursor.getString(2);
            obj.release_date = cursor.getString(3);
            obj.vote_average = cursor.getString(4);
            obj.runtime = cursor.getString(5);
            obj.imdb_id = cursor.getString(6);
            obj.tagline = cursor.getString(7);
            obj.backdrop_path = cursor.getString(8);
            obj.poster_path = cursor.getString(9);
            obj.title = cursor.getString(10);
            obj.URL = cursor.getString(11);
            obj.production_year = cursor.getString(12);
            obj.production_day = cursor.getString(13);
            jsonData.add(obj);
            cursor.moveToNext();
        }
    }
}
