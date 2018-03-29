package alap.com.capstack.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import alap.com.capstack.R;
import alap.com.capstack.adapter.InfolistAdapter;
import alap.com.capstack.model.ModelSoftware;
import alap.com.capstack.netUtills.GlobalElements;
import alap.com.capstack.netUtills.RequestInterface;
import alap.com.capstack.netUtills.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationWindow2 extends AppCompatActivity {

    RecyclerView rvInfo;
    ArrayList<ModelSoftware>modelSoftwares=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_window2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.setTitle("");
        rvInfo=(RecyclerView)findViewById(R.id.rvInfo);
        if (GlobalElements.isConnectingToInternet(InformationWindow2.this)) {
            getSofts();
        } else {
            GlobalElements.showDialog(InformationWindow2.this);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
    public void getSofts(){
        RequestInterface r= RetrofitClient.getApiService();
        Call<ArrayList<ModelSoftware>>call=r.GetSoftware("getsoft");
        call.enqueue(new Callback<ArrayList<ModelSoftware>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelSoftware>> call, Response<ArrayList<ModelSoftware>> response) {
                modelSoftwares.clear();
                try {
                    if(response.isSuccessful()){
                        modelSoftwares=response.body();rvInfo.setAdapter(new InfolistAdapter(InformationWindow2.this,modelSoftwares));
                        rvInfo.setLayoutManager(new GridLayoutManager(InformationWindow2.this,2,LinearLayoutManager.VERTICAL,false));

                    }
                    else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelSoftware>> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

}
