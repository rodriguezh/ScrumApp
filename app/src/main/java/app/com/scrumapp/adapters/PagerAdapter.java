package app.com.scrumapp.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.com.scrumapp.fragments.huasignada.HUAsignadasFragment;
import app.com.scrumapp.fragments.huinicial.HUInicialFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private Bundle bundle;

   public PagerAdapter(FragmentManager fm, int numberOfTabs, Bundle bundle){
        super(fm);
        this.numberOfTabs = numberOfTabs;
        this.bundle= bundle;
   }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
       switch (position){
           case 0:
               fragment =new HUInicialFragment();
               fragment.setArguments(bundle);
               return fragment;
           case 1:
                fragment =new HUAsignadasFragment();
                fragment.setArguments(bundle);
                return fragment;
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return  numberOfTabs;
    }
}
