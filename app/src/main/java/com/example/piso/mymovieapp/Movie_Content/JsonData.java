package com.example.piso.mymovieapp.Movie_Content;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mustafa.Piso on 19/08/2016.
 */
// Data  in  one json  :d
public   class  JsonData implements Parcelable {
    public String adult = "adult";
    public String backdrop_path = "backdrop_path";
    public String belongs_to_collection = "belongs_to_collection";
    public String genres_id= "genres_id" ;
    public String genres_name = "genres_name";
    public String homepage = "homepage";
    public String id = "id";
    public String imdb_id = "imdb_id";
    public String original_language ="original_language";
    public String original_title = "original_title";
    public String overview = "overview" ;
    public String popularity = "popularity";
    public String poster_path = "poster_path";
    public String release_date= "release_date" ;
    public String revenue= "revenue" ;
    public String runtime = "runtime";
    public String vote_count = "vote_count";
    public String vote_average= "vote_average" ;
    public String video = "video";
    public String title = "title";
    public String status = "status";
    public String tagline = "tagline";
    public String URL= "URL" ;
    public String production_year="production_year" ;
    public String production_day ="production_day";
    public JsonData(){}
    public JsonData(Parcel in) {
        adult = in.readString();
        backdrop_path = in.readString();
        belongs_to_collection = in.readString();
        genres_id = in.readString();
        genres_name = in.readString();
        homepage = in.readString();
        id = in.readString();
        imdb_id = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        popularity = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        revenue = in.readString();
        runtime = in.readString();
        vote_count = in.readString();
        vote_average = in.readString();
        video = in.readString();
        title = in.readString();
        status = in.readString();
        tagline = in.readString();
        URL = in.readString();
        production_year = in.readString();
        production_day = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adult);
        dest.writeString(backdrop_path);
        dest.writeString(belongs_to_collection);
        dest.writeString(genres_id);
        dest.writeString(genres_name);
        dest.writeString(homepage);
        dest.writeString(id);
        dest.writeString(imdb_id);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(popularity);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(revenue);
        dest.writeString(runtime);
        dest.writeString(vote_count);
        dest.writeString(vote_average);
        dest.writeString(video);
        dest.writeString(title);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(URL);
        dest.writeString(production_year);
        dest.writeString(production_day);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JsonData> CREATOR = new Creator<JsonData>() {
        @Override
        public JsonData createFromParcel(Parcel in) {
            return new JsonData(in);
        }

        @Override
        public JsonData[] newArray(int size) {
            return new JsonData[size];
        }
    };

    public  void  Set_Url(String size )
    {
        URL ="http://image.tmdb.org/t/p/"+size+"/"+poster_path;
    }
    public void clean_All (){
        if(adult.charAt(0)=='\"')
        {
            adult =adult.substring(1,adult.length()-1);
        }
        if(backdrop_path.charAt(0)=='\"')
        {
            backdrop_path =backdrop_path.substring(1,backdrop_path.length()-1);
        }
        if(belongs_to_collection.charAt(0)=='\"')
        {
            belongs_to_collection =belongs_to_collection.substring(1,belongs_to_collection.length()-1);
        }
        if(genres_name.charAt(0)=='\"')
        {
            genres_name =genres_name.substring(1,genres_name.length()-1);
        }
        if(imdb_id.charAt(0)=='\"')
        {
            imdb_id =imdb_id.substring(1,imdb_id.length()-1);
        }
        if(homepage.charAt(0)=='\"')
        {
            homepage =homepage.substring(1,homepage.length()-1);
        }
        if(original_language.charAt(0)=='\"')
        {
            original_language =original_language.substring(1,original_language.length()-1);
        }
        if(original_title.charAt(0)=='\"')
        {
            original_title =original_title.substring(1,original_title.length()-1);
        }
        if(release_date.charAt(0)=='\"')
        {
            release_date =release_date.substring(1,release_date.length()-1);
        }
        if(title.charAt(0)=='\"')
        {
            title =title.substring(1,title.length()-1);
        }
        if(status.charAt(0)=='\"')
        {
            status =status.substring(1,status.length()-1);
        }
        if(poster_path.charAt(0)=='\"')
        {
            poster_path =poster_path.substring(1,poster_path.length()-1);
        }
        if(tagline.charAt(0)=='\"')
        {
            tagline =tagline.substring(1,tagline.length()-1);
        }
    }
    public void detach_release_date ()
    {
        production_year = release_date.substring(0,4);
        production_day = release_date.substring(5,7)+"/"+release_date.substring(8,release_date.length());
    }
}