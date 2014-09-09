package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 9/8/2014.
 */
public class LocalSave {
    private String comment;
    private double plannedHours;
    private double hoursWorked;
    private String timestamp;
    private String  date;
    private int taskId;


    public LocalSave(String commentByDay, double plannedhours, double hoursByDay, String timestampByDay, String dateByDay, int taskIdByDay){
        comment=commentByDay;
        plannedHours = plannedhours;
        hoursWorked = hoursByDay;
        timestamp = timestampByDay;
        date  = dateByDay;
        taskId = taskIdByDay;


    }

    public int gettaskId(){return taskId;}
    public double getPlannedHours(){return plannedHours;}
    public double getHours(){return hoursWorked  ;}
    public String getTimestamp(){return timestamp;}
    public String getDate(){return date;}
    public String getComment(){return comment;}

}
