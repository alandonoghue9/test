package com.CoraSystems.mobile.test.TaskList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.CoraSystems.mobile.test.R;

import java.util.ArrayList;

public class FilterFragment extends Fragment {

    /** FILTER FRAGMENT DISPLAYED ON TOP OF TASKLIST **/

    View.OnClickListener clickListener;
    View view;

    Boolean selector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //selector = Boolean.FALSE;

        view = inflater.inflate(R.layout.filter, container, false);
        view.setOnClickListener(clickListener);

        /** ARROWS ON 'PROJECTS' SELECTED **/
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector == Boolean.FALSE) {
                    ImageView tick = (ImageView) v.findViewById(R.id.image);
                    tick.setImageResource(R.drawable.arrow_up);
                    selector = Boolean.TRUE;
                } else if (selector == Boolean.TRUE) {
                    ImageView tick = (ImageView) v.findViewById(R.id.image);
                    tick.setImageResource(R.drawable.arrow);
                    selector = Boolean.FALSE;
                }
            }
        });*/

        return view;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }


}