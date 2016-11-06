package com.example.piso.mymovieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piso.mymovieapp.Movie_Content.Trailer_data;
import com.example.piso.mymovieapp.R;

import java.util.List;

/**
 * Created by Piso on 13/08/2016.
 */
public class Trailer_Adapter extends RecyclerView.Adapter<Trailer_Adapter.HoldView> {

    private HoldView holdView ;
    private RecyclerView recyclerView ;
    private Context context ;
    private List <Trailer_data> list ;
    public Trailer_Adapter(List<Trailer_data> l , Context context) {
        super();
        this.context = context ;
        list= l ;
    }
    @Override
    public HoldView onCreateViewHolder(ViewGroup parent, int viewType) {
        recyclerView = (RecyclerView)parent.findViewById(R.id.trailerlist);
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtrailer, null) ;
        holdView  = new HoldView (item);
        return  holdView ;
    }
    @Override
    public void onBindViewHolder(HoldView holder, final int position) {
        holder.textView.setText(list.get(position).name);
        holder.imageView.setImageResource(R.drawable.youtube1);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str  = list.get(position).key ;
                Uri uri = Uri.parse("https://www.youtube.com/watch?v=" + str.substring(1, str.length() - 1));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return    list.size();
    }

    // class for  card that in list of trailers
    public static  class HoldView extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textView ;
        ImageView imageView ;
        public HoldView(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textView =(TextView)itemView.findViewById(R.id.trailername)  ;
            imageView =(ImageView)itemView.findViewById(R.id.review_image);

        }
    }
}
