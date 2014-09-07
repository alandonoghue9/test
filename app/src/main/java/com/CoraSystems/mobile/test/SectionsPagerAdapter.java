package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.app.FragmentManager;

public class SectionsPagerAdapter extends android.support.v13.app.FragmentPagerAdapter implements ItemSelectionInterface {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    int selectedFragment;

    @Override
    public Fragment getItem(int position) {
        return timesheetDays.newInstance(position, this);
    }

    @Override
    public int getCount() {
        // Show 3 weeks
        return 3;
    }

    public void onItemSelectionChanged(int fragmentPosition){
        selectedFragment=fragmentPosition;
    }
    public int getSelectedItemOnFragment(int fragmentPosition){
        if(fragmentPosition!=selectedFragment) {
            return -1;
        };
        return 1;
    }
}
