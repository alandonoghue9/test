package com.CoraSystems.mobile.test.Timesheet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.CoraSystems.mobile.test.Objects.ByDay;
import com.CoraSystems.mobile.test.Objects.ByDayInArray;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.ByDayGlobal;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.R;
import com.CoraSystems.mobile.test.Stats;

import java.util.ArrayList;

public class Timesheet extends Activity {

    /*ACTIVITY FOR OVERALL TIMESHEET WINDOW (swiping section of window set up using SectionsPagerAdapter)*/

    SectionsPagerAdapter mSectionsPagerAdapter;
    static ViewPager mViewPager;

    static timesheetDays fragment;
    int taskID;

    ArrayList<timesheetDays> swipe_windows;
    public ArrayList<ByDay> Days;

    double planned;
    double completion;
    String project_des;
    public String startDate;

    int selected;

    public Fragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timesheet_main);

        Bundle bundle = getIntent().getExtras();
        taskID = bundle.getInt("task");
        project_des = bundle.getString("project");
        completion = bundle.getDouble("complete");
        planned = bundle.getDouble("planned");

        selected = 20;
        startDate = "2014-09-11";

        Days = new ArrayList<>();

        getActionBar().setDisplayHomeAsUpEnabled(true);

        for(int t=0;t<ByDayGlobal.ByDayConstantsList.size();t++){
            if(ByDayGlobal.ByDayConstantsList.get(t).gettaskId()==taskID){
                //Log.i("ByDayInternalList", "IN!");
                Days.add(ByDayGlobal.ByDayConstantsList.get(t));
            }
        }
        Log.i("ByDayInternalList", Integer.toString(Days.size()));
        for(int t=0;t<TaskGlobal.task.size();t++){
            if(taskID==TaskGlobal.task.get(t).getTaskId()){
                startDate=TaskGlobal.task.get(t).getStart();
                startDate = startDate.substring(0,10);
                t=TaskGlobal.task.size();
            }
        }

        swipe_windows = getFragments();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), swipe_windows);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //mViewPager.setOffscreenPageLimit(4);

        headerFragment = new TimesheetHeader();
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

        int i = 21;//Days.size(); Length of the task in days
        //Finding how many swipe windows are required
        if(i==0){
            fList.add(timesheetDays.newInstance(0, Days, "0"));
            return fList;
        }
        int extra = i%7;
        int week = i/7;
        if(extra>0)week=week++;

        for(int swipes=0;swipes<week;swipes++){
            fList.add(timesheetDays.newInstance(swipes, Days, startDate));
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
    Intent stat;
    public void stats(){
        stat = new Intent(this, Stats.class);

        stat.putExtra("task", taskID);

        if(ByDayInArray.ByDayIn.size()>0) {
            new AlertDialog.Builder(this)
                    .setTitle("Save Changes")
                    .setMessage("Would you like to save changes?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(stat);
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(stat);
                        }
                    })
                    .show();
        }
        else{
            startActivity(stat);
        }
    }

    @Override
    public void onBackPressed() {
        if(ByDayInArray.ByDayIn.size()>0) {
            new AlertDialog.Builder(this)
                    .setTitle("Save Changes")
                    .setMessage("Would you like to save changes?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .show();
        }
        else {
            finish();
        }
    }
}
