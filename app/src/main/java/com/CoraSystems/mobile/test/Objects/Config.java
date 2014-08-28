package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 8/24/2014.
 */
public class Config{
    private double MAXHOURS;
    private double MINHOURS;
    private double MAXMON;
    private double MINMON;
    private double MAXTUE;
    private double MINTUE;
    private double MAXWED;
    private double MINWED;
    private double MAXTHUR;
    private double MINTHUR;
    private double MAXFRI;
    private double MINFRI;
    private double MAXSAT;
    private double MINSAT;
    private double MAXSUN;
    private double MINSUN;
    private String submission;

    public Config(double maxHoursConfig, double minHoursConfig, double maxMonConfig, double minMonConfig, double maxTueConfig,
                     double minTueConfig, double maxWedConfig, double minWedConfig, double maxThuConfig,double minThuConfig, double maxFriConfig,
                     double minFriConfig, double maxSatConfig, double minSatConfig,  double maxSunConfig, double minSunConfig, String submissionByDay){

        MAXHOURS = maxHoursConfig;
        MINHOURS = minHoursConfig;
        MAXMON  = maxMonConfig;
        MINMON = minMonConfig;
        MAXTUE = maxTueConfig;
        MINTUE  = minTueConfig;
        MAXWED = maxWedConfig;
        MINWED  = minWedConfig;
        MAXTHUR = maxThuConfig;
        MINTHUR = minThuConfig;
        MAXFRI  = maxFriConfig;
        MINFRI = minFriConfig;
        MAXSAT = maxSatConfig;
        MINSAT = minSatConfig;
        MAXSUN = maxSunConfig;
        MINSUN  = minSunConfig;
        submission = submissionByDay;

    }

    public double getMAXHOURS(){return MAXHOURS;}
    public double getMINHOURS(){return MINHOURS;}
    public double getMAXMON(){return MAXMON;}
    public double getMINMON(){return MINMON;}
    public double getMAXTUE(){return MAXTUE;}
    public double getMINTUE(){return MINTUE;}
    public double getMAXWED(){return MAXWED;}
    public double getMINWED(){return MINWED;}
    public double getMAXTHUR(){return MAXTHUR;}
    public double getMINTHUR(){return MINTHUR;}
    public double getMAXFRI(){return MAXFRI;}
    public double getMINFRI(){return MINFRI;}
    public double getMAXSAT(){return MAXSAT;}
    public double getMINSAT(){return MINSAT;}
    public double getMAXSUN(){return MAXSUN;}
    public double getMINSUN(){return MINSUN;}
    public String getSubmission(){return submission;}
}
