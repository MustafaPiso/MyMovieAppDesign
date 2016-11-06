package com.example.piso.mymovieapp.ViewPagers;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.example.piso.mymovieapp.Home_Fragments.Favourite;
import com.example.piso.mymovieapp.Home_Fragments.UpComing;
import com.example.piso.mymovieapp.Home_Fragments.popular;
import com.example.piso.mymovieapp.Home_Fragments.toprated;
import com.example.piso.mymovieapp.MainActivity;
import com.example.piso.mymovieapp.R;

/**
 * Created by Piso on 16/09/2016.
 */
public class PagerAdapter  extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public PagerAdapter(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        this.mNumOfTabs = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
               popular tab1 = new popular();
                return tab1;
            case 1:
                toprated tab2 = new toprated();
                return tab2;
            case 2 :
                UpComing upComing = new UpComing();
                return upComing ;
            case 3:
                Favourite tab3 = new Favourite();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }
}