package com.CoraSystems.mobile.test;

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

        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.day, parent, false);
            TextView textView = (TextView) grid.findViewById(R.id.test);
            ImageView imageView = (ImageView)grid.findViewById(R.id.dots);
            String text = Integer.toString(position+1);
            textView.setText(text);
            imageView.setImageResource(R.drawable.overflow);
        } else {
            grid = convertView;
        }
        return grid;

    }

}
