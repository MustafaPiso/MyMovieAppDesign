package com.example.piso.mymovieapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.piso.mymovieapp.Adapters.Trailer_Adapter;
import com.example.piso.mymovieapp.Movie_Content.Trailer_data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class Trailers_Moive extends AppCompatActivity {
    RecyclerView trailer_list ;
    List<Trailer_data> list ;
  Context mcontext = this  ;
    String id  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers__moive);
        if(getIntent().getExtras().containsKey("id") )
        {
            id =  getIntent().getStringExtra("id")  ;
            Log.v("Get it " , id);

        }
        else{

            return ;

        }
        list  = new ArrayList<Trailer_data>();
       //   Log.v("IDD" , id);
        trailer_list = (RecyclerView) findViewById(R.id.trailerlist);
        String Trailer_Url = "http://api.themoviedb.org/3/movie/" +  id + "/videos?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(this)
                .load(Trailer_Url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        list.clear();
                        if (e == null && result.has("results")) {
                            JsonArray Trailer_arr = result.get("results").getAsJsonArray();
                            for (int i = 0; i < Trailer_arr.size(); i++) {

                                Trailer_data obj = new Trailer_data(Trailer_arr.get(i).getAsJsonObject().get("key").toString()
                                        , Trailer_arr.get(i).getAsJsonObject().get("name").toString());

                                list.add(obj);
                                Log.v("Item " , String.valueOf(i));
                            }
                            Trailer_Adapter adapter = new Trailer_Adapter(list, mcontext);
                            trailer_list.setLayoutManager(new LinearLayoutManager(mcontext));
                            trailer_list.setAdapter(adapter);
                            trailer_list.setItemAnimator(new DefaultItemAnimator());
                        }
                    }
                });
    }
}
