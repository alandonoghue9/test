package com.CoraSystems.mobile.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.CoraSystems.mobile.test.Objects.ObjectConstants.ConfigConstants;
import com.CoraSystems.mobile.test.Objects.ObjectConstants.TaskGlobal;
import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.Services.SoapWebService;
import com.CoraSystems.mobile.test.database.DatabaseConstants;
import com.CoraSystems.mobile.test.database.DatabaseReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MyActivity extends Activity implements
        OnTextFragmentAnimationEndListener, FragmentManager.OnBackStackChangedListener {

    Boolean today=Boolean.FALSE, tomorrow=Boolean.FALSE, thisweek=Boolean.FALSE, projects=Boolean.FALSE,
            pickday=Boolean.FALSE, pickweek=Boolean.FALSE, pickmonth=Boolean.FALSE, all=Boolean.FALSE,
            incomplete=Boolean.FALSE, complete = Boolean.FALSE;


    View mDarkHoverView;
    FilterListFrag listFragment; //text
    FilterFragment filterFragment; //image

    boolean mDidSlideOut = false;
    boolean mIsAnimating = false;

    ArrayList<Task> filterTask;
    Time todayTime = new Time(Time.getCurrentTimezone());

    Boolean selector;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selector=Boolean.FALSE;

        setContentView(R.layout.activity_my);
        mDarkHoverView = findViewById(R.id.dark_hover_view);
        mDarkHoverView.setAlpha(0);

        //filterList();
        filterTask =TaskGlobal.task;
        Fragment baseFragment = new ListGplayCardFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_main, baseFragment, "Frag_Main_tag");
        transaction.commit();

        /*// get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);*/

        filterFragment = (FilterFragment) getFragmentManager().findFragmentById(R.id.move_fragment);

        listFragment = new FilterListFrag();
        getFragmentManager().addOnBackStackChangedListener(this);

        filterFragment.setClickListener(mClickListener);
        listFragment.setOnTextFragmentAnimationEnd(this);
        mDarkHoverView.setOnClickListener(mClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.dashboard:
                dash();
                return true;
            case R.id.action_refresh:
                fetchService fetchService = new fetchService();
                fetchService.execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            switchFragments();
            if (selector == Boolean.FALSE) {
                ImageView tick = (ImageView) filterFragment.view.findViewById(R.id.image);
                tick.setImageResource(R.drawable.arrow_up);
                selector = Boolean.TRUE;
            } else if (selector == Boolean.TRUE) {
                ImageView tick = (ImageView) filterFragment.view.findViewById(R.id.image);
                tick.setImageResource(R.drawable.arrow);
                selector = Boolean.FALSE;
            }
        }
    };

    private void switchFragments () {
        if (mIsAnimating) {
            return;
        }
        mIsAnimating = true;
        if (mDidSlideOut) {
            mDidSlideOut = false;
            getFragmentManager().popBackStack();
        } else {
            mDidSlideOut = true;

            Animator.AnimatorListener listener = new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator arg0) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.animator.slide_fragment_in, R.animator.slide_fragment_out);
                    transaction.add(R.id.move_to_back_container, listFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            };
            slideBack (listener);
        }
    }

    @Override
    public void onBackStackChanged() {
        if (!mDidSlideOut) {
            slideForward(null);
        }
    }

    public void slideBack(Animator.AnimatorListener listener)
    {
        ObjectAnimator darkHoverViewAnimator = ObjectAnimator.
                ofFloat(mDarkHoverView, "alpha", 0.0f, 0.0f);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(darkHoverViewAnimator);
        s.addListener(listener);
        s.start();
    }

    public void slideForward(Animator.AnimatorListener listener)
    {
        ObjectAnimator darkHoverViewAnimator = ObjectAnimator.
                ofFloat(mDarkHoverView, "alpha", 0.0f, 0.0f);

        AnimatorSet t = new AnimatorSet();
        t.playTogether(darkHoverViewAnimator);
        t.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIsAnimating = false;
            }
        });
        t.start();
    }

    public void onAnimationEnd() {
        mIsAnimating = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (getFragmentManager().getBackStackEntryCount() == 0)
            {   this.finish();
                return false;
            }
            else
            {   getFragmentManager().popBackStack();
                switchFragments();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void dash(){
        Intent dash = new Intent(this, Dashboard.class);
        startActivity(dash);
    }

    /*FILTER LIST STUFF*/

    public void filterList(Calendar startFilterDate, Calendar endFilterDate, boolean isToday){
        final ArrayList<Task> delTask = new ArrayList<Task>();

        todayTime = new Time(Time.getCurrentTimezone());
        todayTime.setToNow();
        int dayOfWeek = todayTime.weekDay;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dateComparer = new SimpleDateFormat("ddMMyyyy");
            Calendar startChecker = Calendar.getInstance();
            Calendar endChecker = Calendar.getInstance();

            for (int i = 0; i < TaskGlobal.task.size(); i++){
                String startchecker = "9-9-2014";//taskGlobal.task.get(i).getStart();
                String endchecker = "14-9-2014";//taskGlobal.task.get(i).getFinish();

/*
        Calendar startFilterDate = Calendar.getInstance();
        Calendar endFilterDate = Calendar.getInstance();
*/
                try {
                    startChecker.setTime(sdf.parse(startchecker));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    endChecker.setTime(sdf.parse(endchecker));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
/*
        String startTime = todayTime.monthDay+"-"+(todayTime.month+1)+"-"+todayTime.year;
        try {
            startFilterDate.setTime(sdf.parse(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String endTime = todayTime.monthDay+"-"+(todayTime.month+1)+"-"+todayTime.year;
        try {
            startFilterDate.setTime(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        endFilterDate.add(Calendar.DATE, 7-dayOfWeek);
        String outputDate = sdf.format(endFilterDate.getTime());

        if ((dateComparer.format(startChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))==0) || (dateComparer.format(endChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))==0)){
            String sf ="srasgg";
        }

        else if((dateComparer.format(startChecker.getTime()).compareTo(dateComparer.format(endFilterDate.getTime()))==0) || (dateComparer.format(endChecker.getTime()).compareTo(dateComparer.format(endFilterDate.getTime()))==0)) {

            String sf ="srafawfaffasgg";
            }
        else if((dateComparer.format(startChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))<0) && (dateComparer.format(endChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))>0)) {

        }
        else if((dateComparer.format(startChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))>0) && (dateComparer.format(startChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))<0)){

        }
        else if ((dateComparer.format(endChecker.getTime()).compareTo(dateComparer.format(startFilterDate.getTime()))<0)&& isToday)
        {
            if(TaskGlobal.task.get(i).getCompletion() > 0.99){
                delTask.add(TaskGlobal.task.get(i));}
        }
      }
        if(!delTask.isEmpty()){
        new Thread(new Runnable() {
            public void run() {
                DatabaseReader databaseReader = new DatabaseReader();
                databaseReader.DataSource(MyActivity.this);
                databaseReader.deleteTask(delTask, MyActivity.this);
                databaseReader.deleteByDayTask(delTask, MyActivity.this);
            }
        }).start();}
    }
    public void check(View v){
        if (today==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.todaytick);
            tick.setVisibility(v.VISIBLE);
        }
        if (tomorrow==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.tomorrowtick);
            tick.setVisibility(v.VISIBLE);
        }
        if (thisweek==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.thisweektick);
            tick.setVisibility(v.VISIBLE);
        }
        if (pickday==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickdaytick);
            tick.setVisibility(v.VISIBLE);
        }
        if (pickweek==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickweektick);
            tick.setVisibility(v.VISIBLE);
        }
        if (pickmonth==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickmonthtick);
            tick.setVisibility(v.VISIBLE);
        }
        if (all==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.alltick);
            tick.setVisibility(v.VISIBLE);
        }
        if (incomplete==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.incompletetick);
            tick.setVisibility(v.VISIBLE);
        }
        if (complete==Boolean.TRUE) {
            ImageView tick = (ImageView) v.findViewById(R.id.completetick);
            tick.setVisibility(v.VISIBLE);
        }
    }

    public void today(View v){
        if (today==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.todaytick);
            tick.setVisibility(v.VISIBLE);
            today=Boolean.TRUE;

            todayTime = new Time(Time.getCurrentTimezone());
            todayTime.setToNow();

            Calendar startFilterDate = Calendar.getInstance();
            Calendar endFilterDate = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String startTime = todayTime.monthDay+"-"+(todayTime.month+1)+"-"+todayTime.year;
            try {
                startFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}

            try {
                endFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}
            if (tomorrow==Boolean.TRUE){
                endFilterDate.add(Calendar.DATE, 1);}

            filterList(startFilterDate, endFilterDate, true);
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.todaytick);
            tick.setVisibility(v.GONE);
            today=Boolean.FALSE;
        }
    }

    public void projects(View v){
       /* if (projects==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.projecttick);
            tick.setImageResource(R.drawable.arrow_in);
            projects=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.projecttick);
            tick.setImageResource(R.drawable.arrows);
            projects=Boolean.FALSE;
        }*/

    }

    public void tomorrow(View v){
        if (tomorrow==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.tomorrowtick);
            tick.setVisibility(v.VISIBLE);
            tomorrow=Boolean.TRUE;

            todayTime = new Time(Time.getCurrentTimezone());
            todayTime.setToNow();

            Calendar startFilterDate = Calendar.getInstance();
            Calendar endFilterDate = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String startTime = todayTime.monthDay+"-"+(todayTime.month+1)+"-"+todayTime.year;
            try {
                startFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}

            try {
                endFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}
            startFilterDate.add(Calendar.DATE, 1);
            endFilterDate.add(Calendar.DATE, 1);
            if (today==Boolean.TRUE){
                startFilterDate.add(Calendar.DATE, -1);}

            filterList(startFilterDate, endFilterDate, false);
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.tomorrowtick);
            tick.setVisibility(v.GONE);
            tomorrow=Boolean.FALSE;
        }
    }

    public void thisweek(View v){

        if (thisweek==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.thisweektick);
            tick.setVisibility(v.VISIBLE);
            thisweek=Boolean.TRUE;

            todayTime = new Time(Time.getCurrentTimezone());
            todayTime.setToNow();
            int dayOfWeek = todayTime.weekDay;

            Calendar startFilterDate = Calendar.getInstance();
            Calendar endFilterDate = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String startTime = todayTime.monthDay+"-"+(todayTime.month+1)+"-"+todayTime.year;
            try {
                startFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}

            try {
                endFilterDate.setTime(sdf.parse(startTime));
            } catch (ParseException e) {
                e.printStackTrace();}

            endFilterDate.add(Calendar.DATE, 7-dayOfWeek);
            filterList(startFilterDate, endFilterDate, false);
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.thisweektick);
            tick.setVisibility(v.GONE);
            thisweek=Boolean.FALSE;
        }
    }

    public void pickday(View v){
        if (pickday==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickdaytick);
            tick.setVisibility(v.VISIBLE);
            pickday=Boolean.TRUE;
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.pickdaytick);
            tick.setVisibility(v.GONE);
            pickday=Boolean.FALSE;
        }
    }

    public void pickweek(View v){
        if (pickweek==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickweektick);
            tick.setVisibility(v.VISIBLE);
            pickweek=Boolean.TRUE;
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.pickweektick);
            tick.setVisibility(v.GONE);
            pickweek=Boolean.FALSE;
        }
    }

    public void pickmonth(View v){
        if (pickmonth==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.pickmonthtick);
            //tick.setVisibility(v.VISIBLE);
            pickmonth=Boolean.TRUE;
            //DialogFragment newFragment = new DatePickerFragment();
            //newFragment.show(getFragmentManager(), "datePicker");
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.pickmonthtick);
            //tick.setVisibility(v.GONE);
            pickmonth=Boolean.FALSE;
        }
    }

    public void all(View v){
        if (all==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.alltick);
            tick.setVisibility(v.VISIBLE);
            all=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.alltick);
            tick.setVisibility(v.GONE);
            all=Boolean.FALSE;
        }
    }

    public void incomplete(View v){
        if (incomplete==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.incompletetick);
            //tick.setVisibility(v.VISIBLE);
            incomplete=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.incompletetick);
            //tick.setVisibility(v.GONE);
            incomplete=Boolean.FALSE;
        }
    }

    public void complete(View v){
        if (complete==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.completetick);
            //tick.setVisibility(v.VISIBLE);
            complete=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.completetick);
            //tick.setVisibility(v.GONE);
            complete=Boolean.FALSE;
        }
    }

    public class fetchService extends AsyncTask<Void, Void, Void> {
        String checker="";
        String check="";
        private ProgressBar progressBar;
        LinearLayout linlaHeaderProgress = (LinearLayout)findViewById(R.id.linlaHeaderProgress);
          @Override
          protected void onPreExecute() {
              linlaHeaderProgress.setVisibility(View.VISIBLE);
          }
        @Override
        protected Void doInBackground(Void... params) {
            DatabaseReader databaseReader = new DatabaseReader();
            databaseReader.DataSource(MyActivity.this);
            try{
                databaseReader.open();}
            catch(SQLiteException e){
                Log.e("MyActivity", e.getMessage());
            }
                SoapWebService soapWebService = new SoapWebService(ConfigConstants.user, ConfigConstants.password, MyActivity.this);
                String maxDate = databaseReader.getTimeSheetStatusMaxOrMinDate(DatabaseConstants.TaskConstants.STARTTIMESTAT, "MAX");
            checker = soapWebService.getTimeSheetStatus();
            if (checker.contains("User could not be validated") || checker.contains("No Data Recieved")){
                return null;
            }
            databaseReader.open();
            String minDate = databaseReader.getTimeSheetStatusMaxOrMinDate(DatabaseConstants.TaskConstants.STARTTIMESTAT, "MIN");
                check = soapWebService.getTaskFromServer(minDate, maxDate, "GetWork");
                check = soapWebService.getTaskFromServer(minDate, maxDate, "ByDay");
                //  GetWork  Byday  GetTImesheet  ConfigItems


            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            linlaHeaderProgress.setVisibility(View.GONE);
        }
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        public void showDatePickerDialog(View v) {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getFragmentManager(), "datePicker");
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }
}
