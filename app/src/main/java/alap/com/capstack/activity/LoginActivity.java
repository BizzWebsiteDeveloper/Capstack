package alap.com.capstack.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import alap.com.capstack.R;
import alap.com.capstack.netUtills.GlobalElements;
import alap.com.capstack.netUtills.MyPreferences;
import alap.com.capstack.netUtills.RequestInterface;
import alap.com.capstack.netUtills.RetrofitClient;
import alap.com.capstack.netUtills.Validation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText edt_email,edt_password;
    MyPreferences mypre;
    String soft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setTitle("Login");
        Bundle b=getIntent().getExtras();
        if(b!=null){
            soft=b.getString("soft");
        }
        mypre=new MyPreferences(LoginActivity.this);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_password=(EditText)findViewById(R.id.edt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(!Validation.isValid(Validation.BLANK_CHECK,edt_email.getText().toString())){
                        edt_email.setError("Email is required");
                    }
                    else if(!Validation.isValid(Validation.EMAIL,edt_email.getText().toString())){
                        edt_email.setError("Email is not valid");
                    }
                    else if(!Validation.isValid(Validation.BLANK_CHECK,edt_password.getText().toString())){
                        edt_password.setError("Passwod is required");
                    }
                    else {
                        if (GlobalElements.isConnectingToInternet(LoginActivity.this)) {
                            login();
                        } else {
                            GlobalElements.showDialog(LoginActivity.this);
                        }
                    }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void login() {
        RequestInterface r= RetrofitClient.getApiService();
        Call<ResponseBody>call=r.LoginUser("userlogin",edt_email.getText().toString(),edt_password.getText().toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responsestring=response.body().string();
                    edt_password.setText("");
                    edt_email.setText("");
                    JSONObject json=new JSONObject(responsestring);
                    String id=json.getString("id");
                    String name=json.getString("name");
                    String num=json.getString("num");
                    String email=json.getString("email");
                    String pass=json.getString("pass");
                    String date=json.getString("date");
                    String status=json.getString("status");
                    String soft=json.getString("soft");
                    mypre.setPreferences(MyPreferences.id,id);
                    mypre.setPreferences(MyPreferences.name,name);
                    mypre.setPreferences(MyPreferences.num,num);
                    mypre.setPreferences(MyPreferences.email,email);
                    mypre.setPreferences(MyPreferences.pass,pass);
                    mypre.setPreferences(MyPreferences.date,date);
                    mypre.setPreferences(MyPreferences.status,status);
                    mypre.setPreferences(MyPreferences.soft,soft);
                    Intent i=new Intent(LoginActivity.this, ProfilePage.class);
                    i.putExtra("soft",soft);
                    startActivity(i);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
