package com.CoraSystems.mobile.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.view.View;

import com.CoraSystems.mobile.test.Objects.Task;
import com.CoraSystems.mobile.test.Services.JSONparser;
import com.CoraSystems.mobile.test.Services.SoapWebService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);
        mDarkHoverView = findViewById(R.id.dark_hover_view);
        mDarkHoverView.setAlpha(0);

        //filterList();
        Fragment baseFragment = new ListGplayCardFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_main, baseFragment, "Frag_Main_tag");
        transaction.commit();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener () {
        @Override
        public void onClick(View view) {
            switchFragments();
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
            {
                this.finish();
                return false;
            }
            else
            {
                getFragmentManager().popBackStack();
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

    public void filterList(){
        //ArrayList<>
        String startchecker = "08-6-2014";
        String endchecker = "08-9-2014";
        int dayOfWeek;
        boolean checkInt;
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        Time other = new Time(Time.getCurrentTimezone());
        dayOfWeek = today.weekDay;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar startChecker = Calendar.getInstance();

        Calendar endChecker = Calendar.getInstance();

        Calendar startFilterDate = Calendar.getInstance();
        Calendar endFilterDate = Calendar.getInstance();

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


        String startTime = today.monthDay+"-"+(today.month+1)+"-"+today.year;
        try {
            startFilterDate.setTime(sdf.parse(startTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String endTime = today.monthDay+"-"+(today.month+1)+"-"+today.year;
        try {
            startFilterDate.setTime(sdf.parse(endTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayOfWeek =1;
        endFilterDate.add(Calendar.DAY_OF_MONTH, dayOfWeek);
        //
        if((startFilterDate.equals(startChecker) || startFilterDate.equals(endChecker)) || (endFilterDate.equals(startChecker) || endFilterDate.equals(endChecker))){
            Log.i(null, "got in first loop");
        }

        else if(startFilterDate.after(startChecker) && startFilterDate.before(endChecker)) {


        }

        //c.add(Calendar.DAY_OF_MONTH, i);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
/*
        String outputDate = sdf.format(startFilterDate.getTime());
        SimpleDateFormat humanReadableDate = new SimpleDateFormat("MMM dd");
        try{
            outputDate = humanReadableDate.format(sdf.parse(outputDate));}
        catch (ParseException e) {
            e.printStackTrace();
        }
*/


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
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.todaytick);
            tick.setVisibility(v.GONE);
            today=Boolean.FALSE;
        }
    }

    public void projects(View v){

    }

    public void tomorrow(View v){
        if (tomorrow==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.tomorrowtick);
            tick.setVisibility(v.VISIBLE);
            tomorrow=Boolean.TRUE;
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
            tick.setVisibility(v.VISIBLE);
            pickmonth=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.pickmonthtick);
            tick.setVisibility(v.GONE);
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
            tick.setVisibility(v.VISIBLE);
            incomplete=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.incompletetick);
            tick.setVisibility(v.GONE);
            incomplete=Boolean.FALSE;
        }
    }

    public void complete(View v){
        if (complete==Boolean.FALSE) {
            ImageView tick = (ImageView) v.findViewById(R.id.completetick);
            tick.setVisibility(v.VISIBLE);
            complete=Boolean.TRUE;
        }
        else {
            ImageView tick = (ImageView) v.findViewById(R.id.completetick);
            tick.setVisibility(v.GONE);
            complete=Boolean.FALSE;
        }
    }
}
