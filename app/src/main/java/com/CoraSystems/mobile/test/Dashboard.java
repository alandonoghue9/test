package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class Dashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard);

        Fragment headerFragment = new DashFrag();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.dash_frag_header, headerFragment, "Frag_Main_tag");
        transaction.commit();

        Fragment submitFragment = new Submit();
        FragmentManager submitManager = getFragmentManager();
        FragmentTransaction transactionSubmit = submitManager.beginTransaction();
        transactionSubmit.add(R.id.submit, submitFragment, "Frag_Main_tag");
        transactionSubmit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);
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
}
