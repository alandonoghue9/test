package com.CoraSystems.mobile.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

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

    View grid;
    Boolean click=false;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        //View grid;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.day, parent, false);
            TextView viewtext = (TextView) grid.findViewById(R.id.testhrs);
            TextView textView = (TextView) grid.findViewById(R.id.test);
            ImageView imageView = (ImageView)grid.findViewById(R.id.dots);
            String text = Integer.toString(position+1);
            viewtext.setText("");
            textView.setText(text);
            imageView.setImageResource(R.drawable.overflow);
        } else {
            grid = convertView;
        }

        grid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg1) {
                // TODO Auto-generated method stub
                if (click == false) {
                    click = true;
                    ViewSwitcher switcher = (ViewSwitcher) arg1.findViewById(R.id.my_switcher);
                    switcher.showNext(); //or switcher.showPrevious();
                    TextView myTV = (TextView) switcher.findViewById(R.id.test);
                    myTV.setText("8");

                    TextView viewtext = (TextView) arg1.findViewById(R.id.testhrs);

                    TextView textView = (TextView) arg1.findViewById(R.id.test);
                    TextView day = (TextView) arg1.findViewById(R.id.hour);
                    TextView day1 = (TextView) arg1.findViewById(R.id.hour_label);
                    String text = Integer.toString(2);
                    textView.setText(text);
                    day.setText("");
                    viewtext.setText("hrs");
                    day1.setText("wed");
                    arg1.setBackgroundColor(context.getResources().getColor(R.color.cora_red));
                }
                else if (click==true){
                    click = false;
                    ViewSwitcher switcher = (ViewSwitcher) arg1.findViewById(R.id.my_switcher);
                    switcher.showPrevious();
                    TextView myTV = (TextView) switcher.findViewById(R.id.test);
                    myTV.setText("8");
                    TextView viewtext = (TextView) arg1.findViewById(R.id.testhrs);

                    TextView textView = (TextView) arg1.findViewById(R.id.test);
                    TextView day = (TextView) arg1.findViewById(R.id.hour);
                    TextView day1 = (TextView) arg1.findViewById(R.id.hour_label);
                    String text = Integer.toString(position+1);
                    textView.setText(text);
                    day.setText("8");
                    viewtext.setText("");
                    day1.setText("hrs");
                    arg1.setBackgroundColor(context.getResources().getColor(R.color.cora_blue));
                }

            }
        });

        return grid;

    }

    public View clicked(int position, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = inflater.inflate(R.layout.day, parent, false);
        TextView textView = (TextView) grid.findViewById(R.id.test);
        textView.setText("clicked");

        return grid;
    }

}
