package com.example.piso.mymovieapp.ViewPagers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.piso.mymovieapp.DetailActivityFragment;
import com.example.piso.mymovieapp.Trash_Fragment.DetailFragment;
import com.example.piso.mymovieapp.Trash_Fragment.DetailFragment2;


/**
 * Created by Piso on 16/09/2016.
 */
public class Detail_Pager_Adapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;

   Context context  ;
    public Detail_Pager_Adapter(FragmentManager supportFragmentManager, int tabCount , Context context) {
        super(supportFragmentManager);
        this.mNumOfTabs = tabCount;
        this.context = context ;
    }


    @Override
    public Fragment getItem(int position) {
        Log.v("movie positoin", String.valueOf(position));
        if(position>=0&&position<=mNumOfTabs)
        {
            if(DetailActivityFragment.current_Fragment==0) {
                DetailActivityFragment.current_Fragment = 1;
                return (new DetailFragment().newInstance(context, position));

            }
            else  if(DetailActivityFragment.current_Fragment==1) {
                     DetailActivityFragment.current_Fragment = 2 ;
                return (new DetailFragment2().newInstance(context, position));
            }
            else
            {
                return (new DetailActivityFragment().newInstance(context, position));


            }

        }


        return null;

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}