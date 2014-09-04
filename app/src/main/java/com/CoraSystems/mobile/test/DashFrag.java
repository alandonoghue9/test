package com.CoraSystems.mobile.test;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.CoraSystems.mobile.test.Graph.PieGraph;
import com.CoraSystems.mobile.test.Graph.PieSlice;

import java.util.ArrayList;
import java.util.List;

public class DashFrag extends Fragment {

    GridView gridView;
    GridViewCustomAdapter_Dash gridViewAdapterDash;
    private Spinner spinner1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dash_frag, container, false);

        gridView=(GridView)view.findViewById(R.id.gridview);
        gridViewAdapterDash = new GridViewCustomAdapter_Dash(this.getActivity());
        gridView.setAdapter(gridViewAdapterDash);

        spinner1 = (Spinner) view.findViewById(R.id.weeks_spinner);
        List<String> list = new ArrayList<String>();
        list.add("21 July - 27 July");
        list.add("28 July - 3 August");
        list.add("4 August - 10 August");
        list.add("11 August - 17 August");
        list.add("1 September - 7 September");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,list);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);

        addListenerOnSpinnerItemSelection();

        int complete = 60;

        PieGraph pg = (PieGraph)view.findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#ffffff"));
        slice.setValue(100-complete);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#1da9e1"));
        slice.setValue(complete);
        pg.addSlice(slice);

        return view;
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
