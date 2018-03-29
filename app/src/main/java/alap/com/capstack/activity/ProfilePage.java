package alap.com.capstack.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import alap.com.capstack.R;
import alap.com.capstack.adapter.UserlistAdapter;
import alap.com.capstack.model.ModelMember;
import alap.com.capstack.netUtills.MyPreferences;
import alap.com.capstack.netUtills.RequestInterface;
import alap.com.capstack.netUtills.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {

    LinearLayout linear_software_detail, linear_concept;
    MyPreferences myPreferences;
    TextView username, email, txt_intersted, txt_paid_member, txt_Investment, txt_minimum;
    String ustatus,soft,name,prsn,status,mob_descr,cost,flow,timeline,prsn_cost,min_prsn;

    ArrayList<ModelMember>model=new ArrayList<>();
    RecyclerView table_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle("");

        InitUI();
        Bundle b=getIntent().getExtras();
        if(b!=null){
            soft=b.getString("soft");
        }
        else {
            soft=myPreferences.getPreferences(MyPreferences.soft);
            ustatus=myPreferences.getPreferences(MyPreferences.status);
        }
        username.setText(myPreferences.getPreferences(MyPreferences.name));
        email.setText(myPreferences.getPreferences(MyPreferences.email));
        getSoftwareDetail();
        getIntrested();
        getPaid();
        getMembers();

        linear_software_detail = (LinearLayout) findViewById(R.id.linear_software_detail);
        linear_concept = (LinearLayout) findViewById(R.id.linear_concept);
        FloatingActionButton fab_profile = (FloatingActionButton) findViewById(R.id.fab_profile);
        fab_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profiledialog();
            }
        });

        linear_software_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePage.this, SoftwareDetail.class));
                finish();
            }
        });
        linear_concept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePage.this, ConceptActivity.class));
                finish();
            }
        });
//        toolbar.setTitle("Profile Page");
    }

    private void getPaid() {
        RequestInterface r= RetrofitClient.getApiService();
        Call<ResponseBody>call=r.GetSoftwareDetail("getcount","soft = '"+soft+"'" +" AND status = '"+"2"+"'"  );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    JSONObject json=new JSONObject(res);
                    txt_paid_member.setText(json.getString("count")+"/"+prsn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getIntrested() {
        RequestInterface r= RetrofitClient.getApiService();
        Call<ResponseBody>call=r.GetSoftwareDetail("getcount","soft = '"+soft+"'" +" AND status = '"+"1"+"'"  );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    JSONObject json=new JSONObject(res);
                    txt_intersted.setText(json.getString("count"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }private void getMembers() {
        RequestInterface r= RetrofitClient.getApiService();
        Call<ArrayList<ModelMember>>call=r.GetUserDetail("getuser","soft = '"+soft+"'" +" AND status != '"+"0"+"'"  );
        call.enqueue(new Callback<ArrayList<ModelMember>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelMember>> call, Response<ArrayList<ModelMember>> response) {
                if (response.isSuccessful()) {
                    model=response.body();
                    table_recycler_view.setAdapter(new UserlistAdapter(ProfilePage.this,model));
                    table_recycler_view.setLayoutManager(new LinearLayoutManager(ProfilePage.this,LinearLayoutManager.VERTICAL,false));
                }
                else {

                }

            }

            @Override
            public void onFailure(Call<ArrayList<ModelMember>> call, Throwable t) {

            }
        });
    }

    private void getSoftwareDetail() {
        RequestInterface r= RetrofitClient.getApiService();
        Call<ResponseBody>call=r.GetSoftwareDetail("getsoft","id = '"+soft+"'");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string();
                    JSONArray json=new JSONArray(res);
                    for (int i = 0; i <json.length() ; i++) {
                        JSONObject jsonob=json.getJSONObject(i);
                        name=jsonob.getString("name");
                        prsn=jsonob.getString("prsn");
                        status=jsonob.getString("status");
                        mob_descr=jsonob.getString("mob_descr");
                        cost=jsonob.getString("cost");
                        flow=jsonob.getString("flow");
                        timeline=jsonob.getString("timeline");
                        prsn_cost=jsonob.getString("prsn_cost");
                        min_prsn=jsonob.getString("min_prsn");
                        txt_Investment.setText(prsn_cost+"/"+cost);
                        txt_minimum.setText(min_prsn);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void InitUI() {
        myPreferences = new MyPreferences(ProfilePage.this);
        username = (TextView) findViewById(R.id.username);
        email = (TextView) findViewById(R.id.email);
        txt_intersted = (TextView) findViewById(R.id.txt_intersted);
        txt_Investment = (TextView) findViewById(R.id.txt_Investment);
        txt_minimum = (TextView) findViewById(R.id.txt_minimum);
        txt_paid_member = (TextView) findViewById(R.id.txt_paid_member);
        table_recycler_view=(RecyclerView)findViewById(R.id.table_recycler_view);
    }

    private void profiledialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(ProfilePage.this);
        final View customView = layoutInflater.inflate(R.layout.user_detail_dialog, null);
        AlertDialog.Builder editCustomerPopup = new AlertDialog.Builder(ProfilePage.this);
        editCustomerPopup.setCancelable(false);
        editCustomerPopup.setView(customView);
        editCustomerPopup.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        TextView user_text=(TextView)customView.findViewById(R.id.user_text);
        TextView sw_comp_text=(TextView)customView.findViewById(R.id.sw_comp_text);
        TextView phone_text=(TextView)customView.findViewById(R.id.phone_text);
        TextView mail_text=(TextView)customView.findViewById(R.id.mail_text);
        TextView date_text=(TextView)customView.findViewById(R.id.date_text);
        TextView status_text=(TextView)customView.findViewById(R.id.status_text);
        user_text.setText(myPreferences.getPreferences(MyPreferences.name));
        sw_comp_text.setText(myPreferences.getPreferences(MyPreferences.soft));
        phone_text.setText(myPreferences.getPreferences(MyPreferences.num));
        mail_text.setText(myPreferences.getPreferences(MyPreferences.email));
        date_text.setText(myPreferences.getPreferences(MyPreferences.date));
        status_text.setText(myPreferences.getPreferences(MyPreferences.status));
        final AlertDialog alertDialog = editCustomerPopup.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
