package com.example.piso.mymovieapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.piso.mymovieapp.Movie_Content.Review_data;
import com.example.piso.mymovieapp.R;

import java.util.List;

/**
 * Created by Piso on 30/08/2016.
 */
public class Review_Adapter extends RecyclerView.Adapter<Review_Adapter.HoldView> {
    HoldView holdView ;
    RecyclerView review_list ;

private List <Review_data> list ;

    public Review_Adapter(List<Review_data> l  ) {
        super();
        list  = l ;
    }
    @Override
    public HoldView onCreateViewHolder(ViewGroup parent, int viewType) {
        review_list = (RecyclerView) parent.findViewById(R.id.review_list);
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, null);
        holdView = new HoldView(item);
        return holdView;
    }

    @Override
    public void onBindViewHolder(HoldView holder, final int position){
        holder.author.setText(list.get(position).author);
        holder.content.setText(list.get(position).content);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class HoldView extends RecyclerView.ViewHolder {
        public TextView author ,content ;
         public HoldView(View itemView) {
            super(itemView);
            author =(TextView)itemView.findViewById(R.id.author)  ;
            content =(TextView) itemView.findViewById(R.id.content);

        }
    }
}
