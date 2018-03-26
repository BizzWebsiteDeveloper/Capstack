package alap.com.capstack.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import alap.com.capstack.R;
import alap.com.capstack.netUtills.MyPreferences;
import alap.com.capstack.netUtills.RuntimePermissionsActivity;

public class SplashActivity extends RuntimePermissionsActivity {
    MyPreferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myPreferences=new MyPreferences(SplashActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.super.requestAppPermissions(new String[]{Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE},R.string.permission,20);
            }
        },1000);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if(requestCode==20){
            Intent i=new Intent(SplashActivity.this,WelcomeScreen.class);
            startActivity(i);
            finish();
        }

    }
}
