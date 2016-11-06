package com.example.piso.mymovieapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.piso.mymovieapp.Adapters.Review_Adapter;
import com.example.piso.mymovieapp.Movie_Content.Review_data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class Reviews_Moive extends AppCompatActivity {
    String id  ;
    RecyclerView review_list  ;
    Context mcontext  ;
     List<Review_data> Review_list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reviews__moive);
        mcontext = this  ;
        Review_list = new ArrayList<Review_data>();
        review_list = (RecyclerView) findViewById(R.id.review_list);
        if(getIntent().getExtras().containsKey("id") )
        {
            id =  getIntent().getStringExtra("id")  ;
            Log.v("Get it " , id);

        }
        else{

            return ;

        }
        String Review_Url = "http://api.themoviedb.org/3/movie/"
                +id + "/reviews?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(this)
                .load(Review_Url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e==null&&result.has("results")) {
                            JsonArray Review_arr = result.get("results").getAsJsonArray();
                            System.getProperty("line.separator");
                            for (int i = 0; i < Review_arr.size(); i++) {
                                String   content  =    Review_arr.get(i).getAsJsonObject().get("content").toString();
                                Log.v("Before " , content);
                                content = content.replaceAll("\r", "   ");
                                content = content.replaceAll("\n", "\n");
                                content = content.replaceAll("(\\r|\\n)", "");
                                 for(int t =0 , n = content.length()-2 ; t<n ; t++)
                                {
                                    if(content.charAt(t)=='\\'&&content.charAt(t+1)=='r')
                                    {
                                        Log.v("i catched it ", "yes");
                                    }
                                }

                                 Log.v("After ", content) ;
                                 String   author =   Review_arr.get(i).getAsJsonObject().get("author").toString();
                                  Review_list.add(new Review_data(author,content));
                            }
                            Review_Adapter review_adapter = new Review_Adapter(Review_list);
                            review_list.setLayoutManager(new LinearLayoutManager(mcontext));
                            review_list.setAdapter(review_adapter);
                            review_list.setItemAnimator(new DefaultItemAnimator());
                        }
                    }
                });




    }

}
