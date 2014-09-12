package com.CoraSystems.mobile.test;

/**
 * Created by eoghanmartin on 11/09/2014.
 */
public class GlobalSelectTimesheet {
    public int selected;
    public int prev;

    public void onItemSelectionChanged(int fragmentPosition){
        selected = fragmentPosition;
    }
    public int getSelectedItemOnFragment(int fragmentPosition){
        if(fragmentPosition!=selected) {
            return 10;
        }
        else if((fragmentPosition == prev)&&(fragmentPosition==selected)){
            return 10;
        }
        else if((fragmentPosition != prev)&&(fragmentPosition==selected)){
            prev = fragmentPosition;
            return selected;
        }
        return 10;
    }

    public void clicked(int click){
        prev = click;
    }

    private GlobalSelectTimesheet(){}
    private static GlobalSelectTimesheet instance;

    public static synchronized GlobalSelectTimesheet getInstance(){
        if(instance==null){
            instance=new GlobalSelectTimesheet();
        }
        return instance;
    }
}
