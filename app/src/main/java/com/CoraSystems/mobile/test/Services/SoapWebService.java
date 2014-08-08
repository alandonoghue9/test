package com.CoraSystems.mobile.test.Services;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.CoraSystems.mobile.test.R;
//import com.CoraSystems.mobile.test.Util;
//import com.CoraSystems.mobile.test.Utility;
import com.CoraSystems.mobile.test.database.DatabaseReader;

/**
 * Created by Alan on 8/5/2014.
 */
public class SoapWebService implements Serializable{
    public static final String TAG = "json";
    private static final long serialVersionUID = 1L;
    private String uName;
    private String Pwd;
    private Context context;
    private String BaseURL;
    private String SerialCloseTag;
    private String SerialOpenTag2;
    public SoapWebService(String uName, String Pwd, Activity context) {
        this.uName = uName;
        this.Pwd = Pwd;
        this.context = context;
        SetIPAddress();
        this.SerialOpenTag2 ="<string xmlns=\"http://tempuri.org/ProjectVision/Project\">"; //User for replace on return data
        this.SerialCloseTag = "</string>";
        //dbHelper = new DatabaseInterface(context);
        //DatabaseReader databaseReader = new DatabaseReader();
        //databaseReader.DataSource(context);

    }
    public SoapWebService(String uName, String Pwd) {
        this.uName = uName;
        this.Pwd = Pwd;
        SetIPAddress();
        this.SerialOpenTag2 ="<string xmlns=\"http://tempuri.org/ProjectVision/Project\">"; //User for replace on return data
        this.SerialCloseTag = "</string>";
        //DatabaseReader databaseReader = new DatabaseReader();
        //databaseReader.DataSource(context);
    }
    private void SetIPAddress()
    {

        try
        {
            //String strAppState = Util.GetSetting("APPSTATE", context);
            //String strAppState = "http://pat-pc/ProjectVision/services/projectapi.asmx?op=GetTaskRequestJSON";
            //if (strAppState.equalsIgnoreCase("TRAINING"))
            //{
                //this.BaseURL = "http://46.51.207.151/wmsservice_training/DataService/"; //Live Site
            //}
            //else
            //{
                //this.BaseURL = "http://46.51.207.151/wmsservice/DataService/"; //Live Site
                this.BaseURL = "http://192.168.1.4/ProjectVision/services/projectapi.asmx/";

            //}
        }
        catch (Exception ex)
        {
            this.BaseURL = "http://192.168.1.4/ProjectVision/services/projectapi.asmx/"; //Live Site
        }
    }

    public String SendThisData(String strTextToSend, int Timeout) throws IOException
    {
        String strRetVal = "";
        strTextToSend =  "GetTaskRequestJSON"/*?SecurityKey=cora&RequestString={\"userName\":\"" + uName + "\",\"password\":\"" +Pwd + "\"}"*/ ;
        String bytessend = "{\"userName\":\"\" + uName + \"\",\"password\":\"\" +Pwd + \"\"}";
        try
        {
            StringBuilder sb = new StringBuilder();

            bytessend = this.SerialOpenTag2 + bytessend + this.SerialCloseTag;
            Log.i(TAG, "this.baseurl"+this.BaseURL);
            URL url = new URL(this.BaseURL+strTextToSend/*+bytessend*/);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setUseCaches (false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setConnectTimeout( Timeout );  // long timeout, but not infinite
            con.setReadTimeout( Timeout );
            con.setRequestProperty ( "Content-Type", "application/soap+xml; charset=utf-8");

            con.connect();
            Log.i(TAG, "successful connection");
            DataOutputStream wr = new DataOutputStream (con.getOutputStream ());
            wr.writeBytes (bytessend);
            wr.flush ();
            wr.close ();
            Log.i(TAG, "sent data "+con.getResponseCode());
            InputStreamReader ISReader = new InputStreamReader(con.getInputStream());
            BufferedReader in = new BufferedReader(ISReader, 8192000);

            String inputLine;
            Log.i(TAG, "read data");
            while ((inputLine = in.readLine()) != null)
            {
                sb.append(inputLine);
            }
            ISReader.close();
            ISReader = null;
            in.close();
            in = null;
            con.disconnect();
            con = null;
            url = null;

            strRetVal = sb.toString();
            Log.i(TAG, strRetVal);
        }
        catch(Exception ex)
        {
            Log.i(TAG, "exception occurred "+ex.toString());

        }
        Log.i(TAG,"final tag: " +strRetVal);
        return strRetVal;
    }
    public String GetUploadString(){
        return "hello";
    }

}
