package alap.com.capstack.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.IOException;

import alap.com.capstack.R;
import alap.com.capstack.activity.InformationWindow2;
import alap.com.capstack.activity.LoginActivity;
import alap.com.capstack.activity.ProfilePage;
import alap.com.capstack.netUtills.GlobalElements;
import alap.com.capstack.netUtills.MyPreferences;
import alap.com.capstack.netUtills.RequestInterface;
import alap.com.capstack.netUtills.RetrofitClient;
import alap.com.capstack.netUtills.Validation;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeScreenFragment2 extends Fragment {


    Button btn_login;
    ImageView prevButton;
    EditText edt_email,edt_password;
    public WelcomeScreenFragment2() {
        // Required empty public constructor
    }

    MyPreferences mypre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_welcome_screen_fragment2, container, false);
        btn_login=(Button)v.findViewById(R.id.btn_login);
        prevButton=(ImageView)v.findViewById(R.id.prevButton);
        edt_email=(EditText)v.findViewById(R.id.edt_email);
        mypre=new MyPreferences(getContext());
        edt_password=(EditText)v.findViewById(R.id.edt_password);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrevClicked n=(PrevClicked)getContext();
                n.prevcliked();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Validation.isValid(Validation.BLANK_CHECK,edt_email.getText().toString())){
                    edt_email.setError("Username or email required");
                }
                else if(!Validation.isValid(Validation.BLANK_CHECK,edt_password.getText().toString())){
                    edt_password.setError("Passwod is required");
                }
                else {
                    if (!GlobalElements.username.equals(edt_email.getText().toString())) {
                        if(!Validation.isValid(Validation.EMAIL,edt_email.getText().toString())){
                            edt_email.setError("Email is not valid");
                        }
                        else {
                            if (GlobalElements.isConnectingToInternet(getContext())) {
                                login();
                            } else {
                                GlobalElements.showDialog(getContext());
                            }
                        }
                    }
                    else {
                        if(GlobalElements.pwd.equals(edt_password.getText().toString())){
                            edt_password.setText("");
                            edt_email.setText("");
                            startActivity(new Intent(getContext(),InformationWindow2.class));

                        }
                    }
                }
            }
        });
        return v;
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
                    startActivity(new Intent(getContext(), ProfilePage.class));
                    getActivity().finish();
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

    public interface PrevClicked{
        public void prevcliked();
    }

}
