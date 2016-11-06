package com.example.piso.mymovieapp.Adapters;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.piso.mymovieapp.DetailActivity;
import com.example.piso.mymovieapp.DetailActivityFragment;
import com.example.piso.mymovieapp.Home_Fragments.Favourite;
import com.example.piso.mymovieapp.Home_Fragments.UpComing;
import com.example.piso.mymovieapp.Home_Fragments.toprated;
import com.example.piso.mymovieapp.MainActivity;
import com.example.piso.mymovieapp.Movie_Content.JsonData;
import com.example.piso.mymovieapp.R;
import com.example.piso.mymovieapp.Home_Fragments.popular;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Piso on 10/08/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Hold>{

     private AppCompatActivity appCompatActivity ;
     private List<JsonData> card_data  ;

       public CustomAdapter(List< JsonData> cards , AppCompatActivity appCompatActivity ){
        card_data = cards ;
         this.appCompatActivity=appCompatActivity;
    }
    @Override
    public int getItemCount() {
        return card_data.size();
     }

    @Override
    public void onBindViewHolder(Hold holder, final int position ) {
        Picasso.with(appCompatActivity).load(card_data.get(position).URL).into(holder.imageView);
         holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Toast.makeText(appCompatActivity,String.valueOf(position),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(appCompatActivity, DetailActivity.class);

                switch (MainActivity.current_Fragment) {
                    case 0:
                        intent.putExtra("MyClass", popular.jsonData.get(position));
                        break;
                    case 1:
                        intent.putExtra("MyClass", toprated.jsonData.get(position));
                        break;

                    case 2:
                        intent.putExtra("MyClass", UpComing.jsonData.get(position));
                        break;
                    case 3:
                        intent.putExtra("MyClass", Favourite.jsonData.get(position));
                        break;
                    default:

                }
                intent.putExtra("position", position) ;
                DetailActivityFragment.isIntent  = true ;
                appCompatActivity.startActivity(intent);

               /* if(MainActivity.twoPane)
                {
                    if(position!=DetailActivityFragment.position) {
                        Bundle arguments = new Bundle();
                        arguments.putInt("position", position);
                        DetailActivityFragment fragment = new DetailActivityFragment();
                        fragment.setArguments(arguments);
                        appCompatActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_recyclerview, fragment)/////////////?????
                                .commit();
                    }
                }
                else {
                    Intent intent = new Intent(appCompatActivity, DetailActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("Url", card_data.get(position).URL);
                     appCompatActivity.startActivity(intent);
                }*/
            }
        });
    }
    @Override
    public  CustomAdapter.Hold onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, null) ;
        Hold hold  = new Hold(item);
        return  hold ;
    }

    public static  class Hold extends RecyclerView.ViewHolder {
          public ImageView imageView ;
        public CardView cardView;

        public Hold(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            cardView = (CardView) itemView.findViewById(R.id.item_view);
        }
    }

}
