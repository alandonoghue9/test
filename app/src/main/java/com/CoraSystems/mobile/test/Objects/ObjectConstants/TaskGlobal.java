package com.CoraSystems.mobile.test.Objects.ObjectConstants;

import com.CoraSystems.mobile.test.Objects.Task;

import java.util.ArrayList;

/**
 * Created by Alan on 9/4/2014.
 */
public class taskGlobal {
    public static ArrayList<Task> task = null;
    public static ArrayList<Task> filterTask = null;
    public static ArrayList<Task> delTask = null;

    /*private taskGlobal(){}
    private static taskGlobal instance;

    public void setData(ByDay d){
        ByDayConstantsList.add(d);
    }
    public ByDay getData(int i){
        return ByDayConstantsList.get(i);
    }

    public static synchronized ByDayGlobal getInstance(){
        if(instance==null){
            instance=new ByDayGlobal();
            instance.ByDayConstantsList = new ArrayList<>();
        }
        return instance;
    }*/

}
