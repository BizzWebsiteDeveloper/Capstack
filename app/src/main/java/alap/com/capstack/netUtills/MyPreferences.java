package alap.com.capstack.netUtills;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.GeneralSecurityException;

/**
 * Created by CRAFT BOX on 1/23/2017.
 */

public class MyPreferences {

    Context context;

    public static String PreferenceName="demoFireBase";
    public static String EncraptionKey="demoFireBase@123456";

    public static String id="id";
    public static  String name="name";
    public static String num="num";
    public static   String email="email";
    public static String pass="pass";
    public static  String date="date";
    public static  String status="status";
    public static String soft="soft";
    public static String dashboard_tutorial="dashboard_tutorial";
    public static String batterystate="batterystate";
//            ,refreshedtoken="",TYPE="type",POSITION="position",UDATE="u_date",UImei="u_imei",UInwardFlag="inward_flag",UDispatch_flag="dispatch_flag";


    public MyPreferences(Context context)
    {
        this.context=context;
    }

    public String getPreferences(String key)
    {
        String value= null;
        try {
            SharedPreferences channel=context.getSharedPreferences(""+ PreferenceName, Context.MODE_PRIVATE);
            value = AESCrypt.decrypt(""+EncraptionKey,channel.getString(""+key,"").toString());
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }

        return value;
    }
    public String getTutorialPreferance(String Key){
        String value=null;
        try{
            SharedPreferences channel=context.getSharedPreferences(""+PreferenceName, Context.MODE_PRIVATE);
            value=AESCrypt.decrypt(""+EncraptionKey,channel.getString(""+Key,"").toString());
        }
        catch (Exception e){
            e.printStackTrace();
            value="";
            return value;
        }
        return value;
    }
    public void setTutorialPreferance(String key, String Value){
        try {
            SharedPreferences sharedPreferences=context.getSharedPreferences(""+PreferenceName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed=sharedPreferences.edit();
            ed.putString(""+key,AESCrypt.encrypt(""+EncraptionKey,""+Value));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setPreferences(String key, String value)
    {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences(""+PreferenceName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString(""+key,AESCrypt.encrypt(""+EncraptionKey, ""+value));
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  boolean clearPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
