package com.example.piso.mymovieapp;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

 import com.squareup.picasso.Picasso;

/**
 * Created by Mustafa.Piso on 13/08/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PopPhoto extends DialogFragment implements View.OnClickListener{
     View view ;
    ImageView imageView ;
     public static  boolean poster  ;
     String poster_path , back_photo_path ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.pop_photo,container,false);
        imageView = (ImageView)view.findViewById(R.id.photo);

         if(getArguments().containsKey("poster")) {
            Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780//" + poster_path).into(imageView);
         }
        else if (getArguments().containsKey("back_photo"))
        {
             Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780//" + back_photo_path).into(imageView);
        }

        return  view;
    }
    @Override
    public void onClick(View v) {
           this.dismiss();
    }
    public  static  PopPhoto  instance (){
        return  new PopPhoto() ;
    }

}
