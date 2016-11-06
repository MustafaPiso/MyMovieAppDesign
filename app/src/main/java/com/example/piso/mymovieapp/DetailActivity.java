package com.example.piso.mymovieapp;

import android.annotation.TargetApi;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.piso.mymovieapp.Home_Fragments.Favourite;
import com.example.piso.mymovieapp.Movie_Content.JsonData;
import com.example.piso.mymovieapp.ViewPagers.Detail_Pager_Adapter;
import com.example.piso.mymovieapp.ViewPagers.PagerAdapter;

public class DetailActivity extends AppCompatActivity {

    JsonData mJsondata ;
    int position ;

    @Override
    protected void onResume() {
        super.onResume();
       /* DetailActivityFragment.backPhoto.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("back_photo",DetailActivityFragment.mJsondata.backdrop_path);
                android.app.FragmentManager manager = getFragmentManager();
                PopPhoto popPhoto = new PopPhoto();
                popPhoto.setArguments(bundle);
                popPhoto.show(manager, null);
            }
        });

        DetailActivityFragment.poster.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("poster",DetailActivityFragment.mJsondata.poster_path);
                android.app.FragmentManager manager = getFragmentManager();
                PopPhoto popPhoto = new PopPhoto();
                popPhoto.setArguments(bundle);
                popPhoto.show(manager, null);
            }
        });*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if(DetailActivityFragment.isIntent) {
            position =   getIntent().getIntExtra("position",0);
             Log.v("My recieve Position " , String.valueOf(position)) ;
         }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.details_pager);

        final Detail_Pager_Adapter adapter;
        if(MainActivity.current_Fragment!=3)
          adapter = new Detail_Pager_Adapter(getSupportFragmentManager(), 20,this);
        else
             adapter= new Detail_Pager_Adapter(getSupportFragmentManager(), Favourite.jsonData.size(),this);
//        Log.v("Detail View Pager ",String.valueOf(Favourite.jsonData.size()));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

        /* mJsondata =  getIntent().getParcelableExtra("MyClass");
        DetailActivityFragment.mJsondata = mJsondata ;
        Log.v("Detail Activity ", " i'm in on create  now ");
        Toast.makeText(this,  mJsondata.id, Toast.LENGTH_SHORT).show();
     /*   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Detail " , " puased");
        DetailActivityFragment.current_Fragment = 0  ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DetailActivityFragment.current_Fragment = 0  ;
    }
}
