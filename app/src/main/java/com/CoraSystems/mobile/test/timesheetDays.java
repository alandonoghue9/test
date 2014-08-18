package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class timesheetDays extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static timesheetDays newInstance(int sectionNumber) {
        timesheetDays fragment = new timesheetDays();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public timesheetDays() {
    }

    Context context;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        GridViewCustomAdapter grisViewCustomeAdapter;
        View rootView = inflater.inflate(R.layout.gridview_layout, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridview);
        grisViewCustomeAdapter = new GridViewCustomAdapter(this.getActivity());
        gridView.setAdapter(grisViewCustomeAdapter);

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String TAG = "string";
                Log.i(TAG, "works");

                View grid;
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (v == null) {
                    grid = inflater.inflate(R.layout.day, parent, false);
                    TextView textView = (TextView) grid.findViewById(R.id.test);
                    ImageView imageView = (ImageView)grid.findViewById(R.id.dots);
                    String text = Integer.toString(position+1);
                    textView.setText(text);
                    imageView.setImageResource(R.drawable.overflow);
                } else {
                    grid = v;
                }
                gridView = grid;

            }
        });*/

        return rootView;
    }
}
