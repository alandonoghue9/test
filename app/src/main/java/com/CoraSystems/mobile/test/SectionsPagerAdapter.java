package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import java.util.List;

public class SectionsPagerAdapter extends android.support.v13.app.FragmentStatePagerAdapter implements ItemSelectionInterface {

    private List<timesheetDays> fragments;

    public SectionsPagerAdapter(FragmentManager fm, List<timesheetDays> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    int selectedFragment;

    @Override
    public timesheetDays getItem(int position) {
        switch (position) {
            case 0:
                return fragments.get(position);
            case 1:
                return fragments.get(position);
            case 2:
                return fragments.get(position);
            default:
                return null;
        }
    }
        //return this.fragments.get(position);//timesheetDays.newInstance(position, this);
    //}

    @Override
    public int getCount() {
        // Show 3 weeks
        return 3;
    }

    public void onItemSelectionChanged(int fragmentPosition){
        getItem(fragmentPosition).select = fragmentPosition;
    }
    public int getSelectedItemOnFragment(int fragmentPosition){
        if(fragmentPosition!=getItem(fragmentPosition).select) {
            return -1;
        };
        return getItem(fragmentPosition).select;
    }
}
