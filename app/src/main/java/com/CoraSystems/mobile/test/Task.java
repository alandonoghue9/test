package com.CoraSystems.mobile.test;

/**
 * Created by Alan on 7/29/2014.
 */
public class Task {
    private int id;
    private int numTasks;
    private String task;
    private String project;
    private String completion;
    private String start;
    private String finish;
    private String planned;
    private int projectId;
    private int taskId;

    public Task(int dbId, int taskprojectid, String taskProject,int taskID, String taskName,String taskplanned,
    String taskstart, String taskfinish,String taskcompletion){
        id=dbId;
        taskId = dbId;
        task=taskName;
        projectId = taskprojectid;
        project=taskProject;
        planned  =  taskplanned;
        start=taskstart;
        finish=taskfinish;
        completion=taskcompletion;

    }

    public int getID(){return id;}
    public void setId(int id){this.id = id;}
    public void setNumTasks(int numTasks){this.numTasks = numTasks;}
    public String getTask(){return task;}
    public String getProject(){return project;}
    public String getCompletion(){return completion;}
    public String getStart(){return start;}
    public String getFinish(){return finish;}
    public String getPlanned(){return planned;}
    public int getProjectId(){return projectId;}
    public int getTaskId(){return taskId;}
}
