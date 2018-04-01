package app.com.scrumapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.com.scrumapp.fragments.HUInicialFragment;
import app.com.scrumapp.fragments.HistoryFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

   public PagerAdapter(FragmentManager fm, int numberOfTabs){
        super(fm);
        this.numberOfTabs = numberOfTabs;
   }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new HUInicialFragment();
           case 1:
               return new HistoryFragment();
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return  numberOfTabs;
    }
}
