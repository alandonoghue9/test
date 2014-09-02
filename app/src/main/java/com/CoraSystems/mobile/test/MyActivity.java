package com.CoraSystems.mobile.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CoraSystems.mobile.test.Services.JSONparser;
import com.CoraSystems.mobile.test.Services.SoapWebService;

import java.io.IOException;
import java.util.ArrayList;


public class MyActivity extends Activity implements
        OnTextFragmentAnimationEndListener, FragmentManager.OnBackStackChangedListener {

    private int mCurrentTitle=R.string.app_name;
    public String ok;
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
        setTitle("Tasks");
        mDarkHoverView = findViewById(R.id.dark_hover_view);
        mDarkHoverView.setAlpha(0);

 /*       Bundle bundle = new Bundle();
        bundle.putString("edttext", ok);*/

        Fragment baseFragment = new ListGplayCardFragment();
       // baseFragment.setArguments(bundle);
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
        getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        View movingFragmentView = filterFragment.getView();

        ObjectAnimator darkHoverViewAnimator = ObjectAnimator.
                ofFloat(mDarkHoverView, "alpha", 0.0f, 0.0f);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(darkHoverViewAnimator);
        s.addListener(listener);
        s.start();
    }

    public void slideForward(Animator.AnimatorListener listener)
    {
        View movingFragmentView = filterFragment.getView();

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

    public class fetchService extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {

                return null;
            }
            catch(Exception e){}
             /*catch (IOException ) {
                //e.printStackTrace();
}*/
            return null;
        }
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
