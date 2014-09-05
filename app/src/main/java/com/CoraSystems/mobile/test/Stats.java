package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;

public class Stats extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.stats);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        int complete = 60;

        PieGraph pg = (PieGraph)this.findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#f26757"));
        slice.setValue(100-complete);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#1da9e1"));
        slice.setValue(complete);
        pg.addSlice(slice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.tasks:
                tasks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void tasks(){
        Intent tasks = new Intent(this, MyActivity.class);
        startActivity(tasks);
    }
}