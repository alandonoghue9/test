package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 8/24/2014.
 */
public class ByDay {
    private String comment;
    private double plannedHours;
    private double hours;
    private String timestamp;
    private String  date;
    private int taskId;


    public ByDay(String commentByDay, double plannedhours, double hoursByDay, String timestampByDay, String dateByDay, int taskIdByDay){
        comment=commentByDay;
        plannedHours = plannedhours;
        hours = hoursByDay;
        timestamp = timestampByDay;
        date  = dateByDay;
        taskId = taskIdByDay;


    }

    public int gettaskId(){return taskId;}
    public double getPlannedHours(){return plannedHours;}
    public double getHours(){return hours;}
    public String getTimestamp(){return timestamp;}
    public String getDate(){return date;}
    public String getComment(){return comment;}

}
