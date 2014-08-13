package com.CoraSystems.mobile.test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eoghanmartin on 13/08/2014.
 */
public class GridViewCustomAdapter extends ArrayAdapter
{
    Context context;



    public GridViewCustomAdapter(Context context)
    {
        super(context, 0);
        this.context=context;

    }

    public int getCount()
    {
        return 7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.day, parent, false);


            TextView textViewTitle = (TextView) row.findViewById(R.id.test);
            String text = Integer.toString(position+1);
            textViewTitle.setText(text);
        }



        return row;

    }

}
