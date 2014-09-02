package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.app.Fragment;
import android.app.FragmentManager;

/**
 * Created by eoghanmartin on 13/08/2014.
 */
public class SectionsPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return timesheetDays.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        // Show 3 weeks days.
        return 3;
    }
}
