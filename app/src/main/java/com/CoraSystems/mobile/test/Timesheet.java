package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.database.DatabaseReader;

import java.util.ArrayList;
import java.util.List;

public class Timesheet extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    static ViewPager mViewPager;

    ItemSelectionInterface selectionInterface;
    static timesheetDays fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timesheet_main);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        List<timesheetDays> timesheetDays = getFragments();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), timesheetDays);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);//new SectionsPagerAdapter(getFragmentManager()));
        //mViewPager.setOffscreenPageLimit(4);

        Fragment headerFragment = new TimesheetHeader();
        headerFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.timesheet_frag_header, headerFragment, "Frag_Main_tag");
        transaction.commit();

        Fragment saveFragment = new save();
        FragmentManager saveManager = getFragmentManager();
        FragmentTransaction transactionSave = saveManager.beginTransaction();
        transactionSave.add(R.id.save, saveFragment, "Frag_Main_tag");
        transactionSave.commit();
    }

    private List<timesheetDays> getFragments() {
        List<timesheetDays> fList = new ArrayList<>();

        fList.add(timesheetDays.newInstance(0));
        fList.add(timesheetDays.newInstance(1));
        fList.add(timesheetDays.newInstance(2));

        return fList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timesheet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.stats:
                stats();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void stats(){
        Intent stat = new Intent(this, Stats.class);
        startActivity(stat);
    }
}
