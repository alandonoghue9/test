package com.CoraSystems.mobile.test;

import android.util.Log;
import android.view.View;

/**
 * Created by eoghanmartin on 18/08/2014.
 */
class MyOnClickListener implements View.OnClickListener
{
    private final int position;

    public MyOnClickListener(int position)
    {
        this.position = position;
    }

    public void onClick(View v)
    {
        // Preform a function based on the position
        String TAG = "string";
        Log.i(TAG, "it works on " + position);
    }
}
