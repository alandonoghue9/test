package com.CoraSystems.mobile.test.Objects;

/**
 * Created by Alan on 8/24/2014.
 */
public class Config{
    private  int id;
    private double MAXHOURS;
    private double MINHOURS;
    private double MAXMON;
    //private double MINMON;
    private double MAXTUE;
   // private double MINTUE;
    private double MAXWED;
   // private double MINWED;
    private double MAXTHUR;
   // private double MINTHUR;
    private double MAXFRI;
   // private double MINFRI;
    private double MAXSAT;
   // private double MINSAT;
    private double MAXSUN;
   // private double MINSUN;

    public Config(int dbId, double maxHoursConfig, double minHoursConfig, double maxMonConfig,double maxTueConfig,
                     double maxWedConfig, double maxThuConfig, double maxFriConfig,
                     double maxSatConfig, double maxSunConfig){
        id = dbId;
        MAXHOURS = maxHoursConfig;
        MINHOURS = minHoursConfig;
        MAXMON  = maxMonConfig;
        //MINMON = minMonConfig;
        MAXTUE = maxTueConfig;
        //MINTUE  = minTueConfig;
        MAXWED = maxWedConfig;
        //MINWED  = minWedConfig;
        MAXTHUR = maxThuConfig;
        //MINTHUR = minThuConfig;
        MAXFRI  = maxFriConfig;
        //MINFRI = minFriConfig;
        MAXSAT = maxSatConfig;
        //MINSAT = minSatConfig;
        MAXSUN = maxSunConfig;
        //MINSUN  = minSunConfig;
    }
    public int getConfigId(){ return id;}
    public double getMAXHOURS(){return MAXHOURS;}
    public double getMINHOURS(){return MINHOURS;}
    public double getMAXMON(){return MAXMON;}
    //public double getMINMON(){return MINMON;}
    public double getMAXTUE(){return MAXTUE;}
    //public double getMINTUE(){return MINTUE;}
    public double getMAXWED(){return MAXWED;}
    //public double getMINWED(){return MINWED;}
    public double getMAXTHUR(){return MAXTHUR;}
    //public double getMINTHUR(){return MINTHUR;}
    public double getMAXFRI(){return MAXFRI;}
    //public double getMINFRI(){return MINFRI;}
    public double getMAXSAT(){return MAXSAT;}
    //public double getMINSAT(){return MINSAT;}
    public double getMAXSUN(){return MAXSUN;}
    //public double getMINSUN(){return MINSUN;}
}
