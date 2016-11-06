package com.example.piso.mymovieapp;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.piso.mymovieapp.Adapters.Trailer_Adapter;
import com.example.piso.mymovieapp.Movie_Content.Trailer_data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.List;

/**
 * Created by Piso on 29/09/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Trailers extends DialogFragment  {
    RecyclerView trailer_list ;
    List<Trailer_data> list ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root  = inflater.inflate(R.layout.trailers_dailog,container,false );

        return root;
    }
    public  static  Trailers instance  ()
    {
        return new Trailers();
    }
}
