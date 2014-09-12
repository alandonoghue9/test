package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import java.util.ArrayList;

public class Timesheet extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    static ViewPager mViewPager;

    static timesheetDays fragment;
    int taskID;

    ByDayGlobal GlobalDay;
    public GlobalSelectTimesheet g;

    ArrayList<timesheetDays> swipe_windows;
    ArrayList<ByDay> Days;

    int planned;
    int completion;
    String project_des;
    public String startDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timesheet_main);

        Bundle bundle = getIntent().getExtras();
        taskID = bundle.getInt("task");
        project_des = bundle.getString("project");
        completion = bundle.getInt("complete");
        planned = bundle.getInt("planned");

        g = GlobalSelectTimesheet.getInstance();
        g.onItemSelectionChanged(-1);
        g.clicked(10);

        GlobalDay = ByDayGlobal.getInstance();
        Days = new ArrayList<>();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        for(int t=0;t<GlobalDay.ByDayConstantsList.size();t++){
            if(GlobalDay.ByDayConstantsList.get(t).gettaskId()==taskID){
                Days.add(GlobalDay.ByDayConstantsList.get(t));
            }
        }
        /*for(int t=0;t<taskGlobal.task.size();t++){
            if(taskID==taskGlobal.task.get(t).getTaskId()){
                startDate=taskGlobal.task.get(t).getStart();
                t=taskGlobal.task.size();
            }
        }*/
       startDate = "2014-09-11";

        swipe_windows = getFragments();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), swipe_windows);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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

    private ArrayList<timesheetDays> getFragments() {
        ArrayList<timesheetDays> fList = new ArrayList<>();

        int i = 21;//Days.size();
        int extra = i%7;
        int week = i/7;
        if(extra>0)week=week++;

        for(int swipes=0;swipes<week;swipes++){
            fList.add(timesheetDays.newInstance(swipes, g, Days, startDate));
        }
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
