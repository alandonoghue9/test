package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 8/12/2014.
 */
public class Config{
private int id;
private double plannedHours;
private int noOfTasks;
private double monHours;
private double tueHours;
private double wedHours;
private double thursHours;
private double friHours;
private double satHours;
private double sunHours;
private String PDFurl;
private boolean SUBMITTED;

        public Config(int dbId, double plannedhours, int nofotasks,double monhours, double tuehours,double wedhours,
                    double thurhours, double frihours,double sathours, double sunhourss, String pdfurl, boolean submitted){
            id=dbId;
            plannedHours = plannedhours;
            noOfTasks = nofotasks;
            monHours = monhours;
            tueHours = tuehours;
            wedHours  = wedhours;
            thursHours = thurhours;
            friHours = frihours;
            satHours  = sathours;
            sunHours = sunhourss;
            PDFurl  = pdfurl;
            SUBMITTED = submitted;

        }

        public int getID(){return id;}
        public double getPlannedHours(){return plannedHours;}
        public int getNoOfTasks(){return noOfTasks;}
        public double getMonHours(){return monHours;}
        public double getTueHours(){return tueHours;}
        public double getWedHours(){return wedHours;}
        public double getThursHours(){return thursHours;}
        public double getFriHours(){return friHours;}
        public double getSatHours(){return satHours;}
        public double getSunHours(){return sunHours;}
        public String getPDFurl(){return PDFurl;}
        public boolean getSubmitted(){return SUBMITTED;}
}
