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
import android.view.Menu;
import android.view.MenuItem;
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
        listFragment.setClickListener(mClickListener);
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
}
