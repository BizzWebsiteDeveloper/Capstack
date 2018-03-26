package alap.com.capstack.netUtills;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

//import com.digitalmarketingtool.object.GeneralModel;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "DigitalCeramic.db";
    private SQLiteDatabase db;
    private final Context context;
    private String DB_PATH;
    public static String user = "user";
    public static String inquiry = "inquiry";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    public void exportDB(Context con) {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh;mm a");
        String Datetime = sdf.format(c.getTime());

        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source = null;
        FileChannel destination = null;

        String currentDBPath = "/data/" + context.getPackageName() + "/databases/" + DB_NAME;
        String backupDBPath = "/Sagar/database/" + DB_NAME + " " + Datetime;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            try {
                encryptfile("" + backupDB, "154", con);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            Toast.makeText(con, "DB Exported in Sdcard or Inter Storage!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean addinquiry(String uid, String name, String email, String message,String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("uid", "" + uid);
            values.put("name", "" + name);
            values.put("email", "" + email);
            values.put("message", "" + message);
            values.put("mobile", "" + mobile);
            long id = db.insert(inquiry, null, values);
            if (id == -1) {
                Log.d("error", "" + id);
                db.close();
                return false;
            } else {
                Log.d("success", "" + id);
                db.close();
                return true;
            }
        } catch (
                Exception e)

        {
            e.printStackTrace();
            Log.e("cartdetail", e + "");
            return false;
        }

    }
    public boolean addUser(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor c=getData("select * from user where email = '"+email+"'");
            if (c!=null) {
                if(c.getCount()>0)
                {
                    Log.d("update","update in to cart");
                    while(c.moveToNext())
                    {
                        ContentValues values = new ContentValues();
                        values.put("email",""+email);
                        values.put("name",""+name);
                        db.update(user,values,"email="+email,new String[]{});
                    }
                    db.close();
                    return true;
                }
                else
                {
                    ContentValues values = new ContentValues();
                    values.put("email",""+email);
                    values.put("name",""+name);
                    long id = db.insert(user, null, values);
                    if(id==-1)
                    {
                        Log.d("error",""+id);
                        db.close();
                        return false;
                    }
                    else
                    {
                        Log.d("success",""+id);
                        db.close();
                        return true;
                    }
                }
            } else {

                ContentValues values = new ContentValues();
                values.put("email",""+email);
                values.put("name",""+name);
                long id = db.insert(user, null, values);
                if(id==-1)
                {
                    Log.d("error",""+id);
                    db.close();
                    return false;
                }
                else
                {
                    Log.d("success",""+id);
                    db.close();
                    return true;
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Log.e("cartdetail", e + "");
            return false;
        }

    }



    @SuppressWarnings("resource")
    public void importDB(File currentDB, String db_name) {
        // TODO Auto-generated method stub
        try {
            decrypt("" + currentDB, "154", Environment.getExternalStorageDirectory().getAbsolutePath() + "/Sagar/database/" + db_name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "//data//" + context.getPackageName() + "//databases//" + DB_NAME;
                String backupDBPath = "/Sagar/database/" + db_name;
                File backupDB = new File(data, currentDBPath);
                File currentDB1 = new File(sd, backupDBPath);
                FileChannel src = new FileInputStream(currentDB1).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
                /*   */
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Sagar/database/" + db_name);
                boolean deleted = file.delete();
                Toast.makeText(context, "Import successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public static void encryptfile(String path, String Pass, Context con) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            String attachment = "" + path.concat(".crypt");
            String salt = "t784";
            FileInputStream fis = new FileInputStream(path);
            FileOutputStream fos = new FileOutputStream(path.concat(".crypt"));
            byte[] key = (salt + Pass).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec sks = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            int b;
            byte[] d = new byte[8];
            while ((b = fis.read(d)) != -1) {
                cos.write(d, 0, b);
            }
            cos.flush();
            cos.close();
            fis.close();
            File file = new File(path);
            boolean deleted = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decrypt(String path, String password, String outPath) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        String salt = "t784";
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(outPath);
        byte[] key = (salt + password).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec sks = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sks);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        int b;
        byte[] d = new byte[8];
        while ((b = cis.read(d)) != -1) {
            fos.write(d, 0, b);
        }
        fos.flush();
        fos.close();
        cis.close();
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        //------------------------------------------------------------
        PackageInfo pinfo = null;
        if (!dbExist) {
            getReadableDatabase();
            copyDataBase();
        }

    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public Cursor getData(String Query) {
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        try {
            Cursor c = db.rawQuery(Query, null);
            return c;
        } catch (Exception e) {
            return null;
        }


    }

    //UPDATE temp_dquot SET age='20',name1='--',rdt='11/08/2014',basic_sa='100000',plno='814',pterm='20',mterm='20',mat_date='11/08/2034',mode='YLY',dab_sa='100000',tr_sa='0',cir_sa='',bonus_rate='42',prem='5276',basic_prem='5118',dab_prem='100.0',step_rate='for Life',loyal_rate='0',bonus_rate='42',act_mat='1,88,000',mly_b_pr='448',qly_b_pr='1345',hly_b_pr='2664',yly_b_pr='5276'  WHERE uniqid=1
    public void dml(String Query) {
        String myPath = DB_PATH + DB_NAME;
        if (db == null)
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        try {
            db.execSQL(Query);
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub


    }

//	public void addCountry(ArrayList<GeneralModel> data) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		try {
//			ContentValues values = new ContentValues();
//			for(int i=0;i<data.size();i++) {
//				GeneralModel c=data.get(i);
//				values.put("id", c.getId());
//				values.put("name", c.getName());
//				db.insert(COUNTRY, null, values);
//			}
//			db.close();
//		} catch (Exception e) {
//			Log.e("country", e + "");
//		}
//	}


}