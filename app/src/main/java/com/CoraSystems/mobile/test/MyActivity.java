package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.CoraSystems.mobile.test.Services.SoapWebService;

import java.io.IOException;


public class MyActivity extends Activity {
    private int mCurrentTitle=R.string.app_name;
    public String ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        Toast toast = Toast.makeText(this, "test", 400);
        toast.show();

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("edttext", ok);
        BaseFragment baseFragment = new ListExpandCardFragment();
        openFragment(baseFragment);
        baseFragment.setArguments(bundle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void openFragment(BaseFragment baseFragment) {
        if (baseFragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fragment_main, baseFragment);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            if (baseFragment.getTitleResourceId()>0)
                mCurrentTitle = baseFragment.getTitleResourceId();

        }
    }
}
