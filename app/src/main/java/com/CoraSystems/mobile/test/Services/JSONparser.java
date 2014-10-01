package com.CoraSystems.mobile.test.Services;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.CoraSystems.mobile.test.database.DatabaseReader;

/**
 * Created by Alan on 8/5/2014.
 */

public class JSONparser {
    private String data;
    private Context Parent;
    public static final String TaskList = "TaskList";
    public static final String ByDay = "ByDay";
    public static final String TimeSheetStatus = "TimeSheetStatus";
    public static final String ConfigItems = "ConfigItems";

    public JSONparser(String data, Context Parent) {
        this.data = data;
        this.Parent = Parent;
    }
    public int parsedData() throws JSONException {

        int parsedReturn = 0;

        Iterator<String> iter;
        String key;
        JSONArray jsonArray;
        DatabaseReader databaseReader = new DatabaseReader();
        databaseReader.DataSource(this.Parent);
        databaseReader.open();

        if (data != null) {
            try {
                JSONObject jsonObject =  new JSONObject(data);
                iter = jsonObject.keys();
                key = iter.next();
                switch(key) {
                    case TaskList:
                        jsonArray = jsonObject.optJSONArray(TaskList);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.projectIdArrayList.add("" + jsonobject.getInt("project id"));
                            databaseReader.projectDescArrayList.add(jsonobject.getString("project description"));
                            databaseReader.taskIdArraylist.add("" + jsonobject.getInt("task id"));
                            databaseReader.taskDescArrayList.add(jsonobject.getString("task description"));
                            databaseReader.plannedHoursArrayList.add(jsonobject.getString("planned hours"));
                            databaseReader.startDateArrayList.add(jsonobject.getString("start date"));
                            databaseReader.finishDateArrayList.add(jsonobject.getString("finish date"));
                            databaseReader.completeArrayList.add("" + jsonobject.getDouble("complete")); }

                        databaseReader.addTask(Parent);
                        break;
                    case ByDay:
                        jsonArray = jsonObject.optJSONArray(ByDay);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.commentArraylist.add(jsonobject.getString("comment"));
                            databaseReader.plannedHoursByDayArrayList.add("" + jsonobject.getDouble("planned hours"));
                            databaseReader.hoursArrayList.add("" + jsonobject.getDouble("hours"));
                            databaseReader.dateArrayList.add(jsonobject.getString("date"));
                            databaseReader.timestampArrayList.add(jsonobject.getString("timestamp"));
                            databaseReader.taskIdArraylist1.add("" + jsonobject.getInt("task id"));
                            databaseReader.actualIdArraylist.add("" + jsonobject.getInt("actual id"));}

                        databaseReader.addByDay(Parent);
                        break;
                    case TimeSheetStatus:
                        jsonArray = jsonObject.optJSONArray(TimeSheetStatus);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);
                            databaseReader.timeSheetIdArraylist.add("" + jsonobject.getInt("timesheetid"));
                            databaseReader.startTimeSheetArraylist.add(jsonobject.getString("start_date"));
                            databaseReader.finishTimeSheetArrayList.add(jsonobject.getString("finish_date"));
                            databaseReader.statusArrayList.add(jsonobject.getString("status"));
                            databaseReader.statusIDArrayList.add("" + jsonobject.getInt("statusid"));
                            databaseReader.statusDescriptionArraylist.add(jsonobject.getString("statusdescription"));}

                        databaseReader.addTimesheetStatus(Parent);
                        break;
                    case ConfigItems:
                        jsonArray = jsonObject.optJSONArray(ConfigItems);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonobject = jsonArray.getJSONObject(i);

                            databaseReader.MAXHOURS= jsonobject.getDouble("maxhours");
                            databaseReader.MINHOURS= jsonobject.getDouble("minhours");
                            databaseReader.MAXMON= jsonobject.getDouble("maxmon");
                            databaseReader.MAXTUE= jsonobject.getDouble("maxtue");
                            databaseReader.MAXWED= jsonobject.getDouble("maxwed");
                            databaseReader.MAXTHUR= jsonobject.getDouble("maxthu");
                            databaseReader.MAXFRI= jsonobject.getDouble("maxfri");
                            databaseReader.MAXSAT= jsonobject.getDouble("maxsat");
                            databaseReader.MAXSUN= jsonobject.getDouble("maxsun");}

                        databaseReader.addConfig(Parent);
                        break;
                }
            } catch (JSONException e) {
                Log.e("System out", "Error "+e.getMessage());
                parsedReturn = -1;}
        }
        return parsedReturn;
    }
}
