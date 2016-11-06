package com.example.piso.mymovieapp.Trash_Fragment;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piso.mymovieapp.DataBase.MovieContract;
import com.example.piso.mymovieapp.Home_Fragments.Favourite;
import com.example.piso.mymovieapp.Home_Fragments.UpComing;
import com.example.piso.mymovieapp.Home_Fragments.popular;
import com.example.piso.mymovieapp.Home_Fragments.toprated;
import com.example.piso.mymovieapp.MainActivity;
import com.example.piso.mymovieapp.Movie_Content.JsonData;
import com.example.piso.mymovieapp.Movie_Content.Review_data;
import com.example.piso.mymovieapp.Movie_Content.Trailer_data;
import com.example.piso.mymovieapp.R;
import com.example.piso.mymovieapp.Reviews_Moive;
import com.example.piso.mymovieapp.Trailers_Moive;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment2 extends Fragment {
    public static ImageView poster,  backPhoto ;

    private boolean fab_on ;
    private FloatingActionButton fab ;
    private String id , Url ;
    private TextView overview_txt , movie_name , year , runtime_txt , date, rate_txt , tagline ;
    CardView trailer_buton ;
    private RecyclerView trailer_list  , review_list  ;
    private List<Trailer_data> list ;
    private List <Review_data> Review_list ;
    private Button Imdb   , reviews_button ;
    private JsonData Re_remove_Moive =null ;
    public static JsonData mJsondata;
    public static  boolean isIntent  ;
    public static int position  , current_Fragment = 0 ;
    CollapsingToolbarLayout collapsingToolbarLayout ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isIntent) {
            mJsondata = getActivity().getIntent().getParcelableExtra("MyClass");
            position = getActivity().getIntent().getIntExtra("position",0);
            Url = "http://image.tmdb.org/t/p/w500//" + mJsondata.poster_path;
        }
        else
        {
            if (getArguments().containsKey("MyClass")) {
                mJsondata = getArguments().getParcelable("MyClass");
                Url = "http://image.tmdb.org/t/p/w500//" + mJsondata.poster_path;
                Toast.makeText(getActivity(), mJsondata.id, Toast.LENGTH_SHORT).show();
                Log.v("Created Movie " ,  mJsondata.id);
            }

        }

    }
    public DetailFragment2(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        Match_and_set_Views(root);
        list = new ArrayList<Trailer_data>();
        id = mJsondata.id;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_Fab (v);
            }
        });
        get_missing_data();
        get_trailers();
        get_reviews();

        Imdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJsondata.clean_All();
                Uri uri = Uri.parse("http://m.imdb.com/title/" + mJsondata.imdb_id + "/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        trailer_buton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                // bundle.putString("poster",DetailActivityFragment.mJsondata.id);
                Intent intent = new Intent(getActivity(),Trailers_Moive.class);
                intent.putExtra("id",mJsondata.id);
                startActivity(intent);
                Toast.makeText(getActivity(), "pressed", Toast.LENGTH_SHORT).show();

            }
        });
        reviews_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Reviews_Moive.class);
                intent.putExtra("id",mJsondata.id);
                startActivity(intent);

            }
        });
        collapsingToolbarLayout.setTitle(mJsondata.original_title);


        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        fab_on = false ;
        if(getContext().getContentResolver().query(MovieContract.Fav_Movies.Uri_For_Id, null, id, null, null) != null) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));
            fab_on = true;
        }
        else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.empty_heart));
            fab_on = false;
        }
    }
    @Override
    public  void onPause() {
        super.onPause();
        DetailFragment2.current_Fragment = 0  ;
    }
    public ContentValues get_Movie (){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",mJsondata.id);
        contentValues.put("original_title",mJsondata.original_title);
        contentValues.put("overview",mJsondata.overview);
        contentValues.put("release_date",mJsondata.release_date);
        contentValues.put("vote_average",mJsondata.vote_average);
        contentValues.put("runtime",mJsondata.runtime);
        contentValues.put("imdb_id",mJsondata.imdb_id);
        contentValues.put("tagline",mJsondata.tagline);
        contentValues.put("backdrop_path",mJsondata.backdrop_path);
        contentValues.put("poster_path",mJsondata.poster_path);
        contentValues.put("title",mJsondata.title);
        contentValues.put("URL",mJsondata.URL);
        contentValues.put("production_year",mJsondata.production_year);
        contentValues.put("production_day", mJsondata.production_day);
        return contentValues ;
    }
    private void get_trailers(){
        /*String Trailer_Url = "http://api.themoviedb.org/3/movie/"
                + mJsondata.id + "/videos?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(getActivity())
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
                            }
                            Trailer_Adapter adapter = new Trailer_Adapter(list, getContext());
                            trailer_list.setLayoutManager(new LinearLayoutManager(getContext()));
                            trailer_list.setAdapter(adapter);
                            trailer_list.setItemAnimator(new DefaultItemAnimator());
                        }
                    }
                });*/
    }
    private void  get_reviews (){
     /*   String Review_Url = "http://api.themoviedb.org/3/movie/"
                +mJsondata.id + "/reviews?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(getActivity())
                .load(Review_Url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e==null&&result.has("results")) {
                            JsonArray Review_arr = result.get("results").getAsJsonArray();
                            for (int i = 0; i < Review_arr.size(); i++) {
                                String   content  =    Review_arr.get(i).getAsJsonObject().get("content").toString();
                                String   author =   Review_arr.get(i).getAsJsonObject().get("author").toString();
                                Review_list.add(new Review_data(author,content));
                            }
                            Review_Adapter review_adapter = new Review_Adapter(Review_list);
                            review_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            review_list.setAdapter(review_adapter);
                            review_list.setItemAnimator(new DefaultItemAnimator());
                        }
                    }
                });*/

    }
    private void Check_Fab (View v){

        if (!fab_on) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.full_heart));
            fab_on = true;

            if (getContext().getContentResolver().query(MovieContract.Fav_Movies.Uri_For_Id,null, id, null, null) == null)
            {


                Favourite.jsonData.add(mJsondata);
                Favourite.customAdapter.notifyDataSetChanged();

                getContext().getContentResolver().insert(MovieContract.Fav_Movies.specific_Uri, get_Movie());
                Snackbar.make(v, "Added To My Favourites", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


        }
        else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.empty_heart));
            fab_on = false;

            if (getContext().getContentResolver().query(MovieContract.Fav_Movies.Uri_For_Id, null, id, null, null) != null) {

                getContext().getContentResolver().delete(MovieContract.Fav_Movies.specific_Uri, mJsondata.id, null);
            }
            Favourite.jsonData.add(mJsondata);
            Favourite.jsonData.remove(Favourite.jsonData.indexOf(mJsondata));
            Favourite.customAdapter.notifyDataSetChanged();
            Snackbar.make(v, "Removed From My Favourites", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }





    }
    private void Match_and_set_Views (View root) {
        collapsingToolbarLayout  = (CollapsingToolbarLayout) root.findViewById(R.id.toolbar_layout);
        //  trailer_list = (RecyclerView) root.findViewById(R.id.trailer_list);
        trailer_buton = (CardView) root.findViewById(R.id.trailers_button);
        poster = (ImageView) root.findViewById(R.id.poster);
        overview_txt = (TextView) root.findViewById(R.id.overview_txt);
        year = (TextView) root.findViewById(R.id.year);
        runtime_txt = (TextView) root.findViewById(R.id.runtime_txt);
        date = (TextView) root.findViewById(R.id.date);
        rate_txt = (TextView) root.findViewById(R.id.rate_txt);
        backPhoto = (ImageView) root.findViewById(R.id.backPhoto);
        reviews_button = (Button) root.findViewById(R.id.review_bu);
        // review_list = (RecyclerView) root.findViewById(R.id.review_list);
        tagline = (TextView) root.findViewById(R.id.tagline_text);
        Imdb = (Button) root.findViewById(R.id.imdb);
        overview_txt.setText(mJsondata.overview);
        makeTextViewResizable(overview_txt,4, "See More", true);
        year.setText(mJsondata.production_year);
        date.setText(mJsondata.production_day);
        rate_txt.setText(mJsondata.vote_average + " /10");
        fab = (FloatingActionButton) root.findViewById(R.id.fab);
        String str = "http://image.tmdb.org/t/p/w500//" +mJsondata.backdrop_path;
        Picasso.with(getActivity()).load(str).into(backPhoto);
        Picasso.with(getActivity()).load(Url).into(poster);


    }
    private void get_missing_data(){
        String missed_Data ="https://api.themoviedb.org/3/movie/"+mJsondata.id+"?api_key=fffc11bad42e01fa3032fb760b319219";
        Ion.with(getActivity())
                .load(missed_Data)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if(result!=null) {
                            if (result.getAsJsonObject().has("runtime") && result.getAsJsonObject().has("imdb_id")) {
                                mJsondata.runtime = result.getAsJsonObject().get("runtime").toString();
                                runtime_txt.setText(mJsondata.runtime);
                                mJsondata.imdb_id = result.getAsJsonObject().get("imdb_id").toString();
                                mJsondata.tagline = result.getAsJsonObject().get("tagline").toString();
                                tagline.setText(mJsondata.tagline);
                            }
                        }

                    }
                });

    }
    public static Fragment newInstance(Context context , int pos) {
        DetailFragment2 fragment = new DetailFragment2();
        Bundle arguments = new Bundle();
        //arguments.putInt("MyClass", );
        switch (MainActivity.current_Fragment) {
            case 0:
                arguments.putParcelable("MyClass", popular.jsonData.get(pos));
                break;
            case 1:
                arguments.putParcelable("MyClass", toprated.jsonData.get(pos));
                break;
            case 2:
                arguments.putParcelable("MyClass", UpComing.jsonData.get(pos));
                break;
            case 3:
                arguments.putParcelable("MyClass", Favourite.jsonData.get(pos));
                break;
            default:

        }
        fragment.setArguments(arguments);
        DetailFragment2.isIntent = false  ;

        return fragment;
    }
    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                    Log.v("Not seemore " , " i got it ");
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);

                    String text =  maxLine == -1 ?tv.getText().subSequence(0, lineEndIndex)+ " " + expandText :
                            tv.getText().subSequence(0, lineEndIndex).toString();
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, "See More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }

}
