package com.example.piso.mymovieapp.DataBase;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;


/**
 * Created by Piso on 31/08/2016.
 */
public class MovieLoader{

}/* extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    public MovieLoader() {
        super();
    }
    private  static  final  int loader_id = 0 ;
   private CursorAdapter customAdapter;
    SimpleCursorAdapter myadapter ;
 ;

    @Override
      public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sort = MainActivityFragment.sort;
        Uri  uri = MovieContract.Fav_Movies.specific_Uri ;
        return new CursorLoader(getActivity(),uri,null,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
              //customAdapter.
        myadapter.swapCursor(data);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myadapter.swapCursor(null);
    }
}
*/