package com.CoraSystems.mobile.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GridViewCustomAdapter_Dash extends ArrayAdapter
{
    Context context;
    String text[]=new String[] {"M","T","W","T","F","S","S"};

    View grid;

    public GridViewCustomAdapter_Dash(Context context)
    {
        super(context, 0);
        this.context=context;
    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.submit_day, parent, false);
        } else {
            grid = convertView;
        }

        TextView textView = (TextView) grid.findViewById(R.id.day);

        textView.setText(text[i]);

        return grid;
    }


}
