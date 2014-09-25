package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 7/29/2014.
 */
public class Task {
    private int id;
    private int numTasks;
    private String task;
    private String project;
    private double completion;
    private String start;
    private String finish;
    private double planned;
    private int projectId;
    private int taskId;

    public Task(int dbId,int taskprojectid, String taskProject,int taskID , String taskName, String taskstart, String taskfinish, double taskplanned,double taskcompletion){
        id=dbId;
        taskId = taskID;
        task=taskName;
        projectId = taskprojectid;
        project=taskProject;
        planned = taskplanned;
        start=taskstart;
        finish=taskfinish;
        completion=taskcompletion;
    }

    public int getID(){return id;}
    public void setId(int id){this.id = id;}
    public void setNumTasks(int numTasks){this.numTasks = numTasks;}
    public String getTask(){return task;}
    public String getProject(){return project;}
    public double getCompletion(){return completion;}
    public String getStart(){return start;}
    public String getFinish(){return finish;}
    public double getPlanned(){return planned;}
    public int getProjectId(){return projectId;}
    public int getTaskId(){return taskId;}
}
