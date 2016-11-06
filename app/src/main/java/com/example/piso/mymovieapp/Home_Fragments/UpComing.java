package com.example.piso.mymovieapp.Home_Fragments;

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
import android.widget.Button;

import com.example.piso.mymovieapp.Adapters.CustomAdapter;
import com.example.piso.mymovieapp.Movie_Content.JsonData;
import com.example.piso.mymovieapp.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piso on 16/09/2016.
 */

public class UpComing extends Fragment{
    public CustomAdapter customAdapter ;
    // public static String sort =  "popular" ;
    public  static List<JsonData> jsonData ;
    public  static  boolean refresh_list = true ;
    private RecyclerView recyclerView ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("toprated " , " hey i'm are created ");
        View root =  inflater.inflate(R.layout.toprated, container, false);
        jsonData = new ArrayList<JsonData>();
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerview);
        refresh_list= true ;

        GridLayoutManager gridLayoutManager  = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return  root ;
    }

    @Override
    public void onResume() {
        super.onResume();
        Update("upcoming");

    }
    private void  Update (String sort ) {
        jsonData.clear();
        /*if (sort.equals("favourite")) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
            Cursor cursor = getActivity().getContentResolver().query(MovieContract.Fav_Movies.specific_Uri, null, null, null, null);
            Get_List(cursor);
            CustomAdapter.Clear();
            customAdapter =CustomAdapter.getInstance(jsonData,(AppCompatActivity) getActivity());
            recyclerView.setAdapter(customAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
           /* if (MainActivity.twoPane && customAdapter.getItemCount() > 0) {
                Bundle arguments = new Bundle();
                arguments.putInt("position", 0);
                DetailActivityFragment fragment = new DetailActivityFragment();
                fragment.setArguments(arguments);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_recyclerview, fragment)
                        .commit();
            }
        } else {*/
        String str = "http://api.themoviedb.org/3/movie/" + sort + "?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(getActivity())
                .load(str)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (e == null) {
                            JsonArray arr = result.get("results").getAsJsonArray();
                            for (int i = 0; i < arr.size(); i++) {
                                JsonData obj = new JsonData(); // My class
                                obj.adult = arr.get(i).getAsJsonObject().get("adult").toString();
                                obj.overview = arr.get(i).getAsJsonObject().get("overview").toString();
                                obj.poster_path = arr.get(i).getAsJsonObject().get("poster_path").toString();
                                obj.backdrop_path = arr.get(i).getAsJsonObject().get("backdrop_path").toString();
                                obj.original_title = arr.get(i).getAsJsonObject().get("original_title").toString();
                                obj.release_date = arr.get(i).getAsJsonObject().get("release_date").toString();
                                obj.vote_average = arr.get(i).getAsJsonObject().get("vote_average").toString();
                                obj.id = arr.get(i).getAsJsonObject().get("id").toString();
                                // obj.runtime = arr.get(i).getAsJsonObject().get("runtime").toString();
//                                    obj.imdb_id = arr.get(i).getAsJsonObject().get("imdb_id").toString();
                                obj.clean_All();
                                obj.detach_release_date();
                                obj.Set_Url("w342");
                                jsonData.add(obj);
                            }

                            customAdapter = new CustomAdapter(jsonData,(AppCompatActivity) getActivity());
                            recyclerView.setAdapter(customAdapter);
                                /*if (MainActivity.twoPane && customAdapter.getItemCount() > 0) {
                                    Bundle arguments = new Bundle();
                                    arguments.putInt("position", 0);
                                    DetailActivityFragment fragment = new DetailActivityFragment();
                                    fragment.setArguments(arguments);
                                    fragment.setArguments(arguments);
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.fragment_recyclerview, fragment)
                                            .commit();
                                }*/
                        }
                    }

                });
        // }
    }
}
